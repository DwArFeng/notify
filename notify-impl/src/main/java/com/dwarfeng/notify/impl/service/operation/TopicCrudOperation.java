package com.dwarfeng.notify.impl.service.operation;

import com.dwarfeng.notify.stack.bean.entity.*;
import com.dwarfeng.notify.stack.bean.entity.key.*;
import com.dwarfeng.notify.stack.cache.*;
import com.dwarfeng.notify.stack.dao.*;
import com.dwarfeng.notify.stack.service.*;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TopicCrudOperation implements BatchCrudOperation<StringIdKey, Topic> {

    private final TopicDao topicDao;
    private final TopicCache topicCache;

    private final DispatcherInfoDao dispatcherInfoDao;
    private final DispatcherInfoCache dispatcherInfoCache;

    private final SenderInfoDao senderInfoDao;
    private final SenderInfoCache senderInfoCache;

    private final MetaDao metaDao;
    private final MetaCache metaCache;

    private final MetaIndicatorDao metaIndicatorDao;
    private final MetaIndicatorCache metaIndicatorCache;

    private final VariableDao variableDao;
    private final VariableCache variableCache;

    private final VariableIndicatorDao variableIndicatorDao;
    private final VariableIndicatorCache variableIndicatorCache;

    private final SendHistoryDao sendHistoryDao;
    private final SendHistoryCache sendHistoryCache;

    @Value("${cache.timeout.entity.topic}")
    private long topicTimeout;

    public TopicCrudOperation(
            TopicDao topicDao, TopicCache topicCache,
            DispatcherInfoDao dispatcherInfoDao, DispatcherInfoCache dispatcherInfoCache,
            SenderInfoDao senderInfoDao, SenderInfoCache senderInfoCache,
            MetaDao metaDao, MetaCache metaCache,
            MetaIndicatorDao metaIndicatorDao, MetaIndicatorCache metaIndicatorCache,
            VariableDao variableDao, VariableCache variableCache,
            VariableIndicatorDao variableIndicatorDao, VariableIndicatorCache variableIndicatorCache,
            SendHistoryDao sendHistoryDao, SendHistoryCache sendHistoryCache
    ) {
        this.topicDao = topicDao;
        this.topicCache = topicCache;
        this.dispatcherInfoDao = dispatcherInfoDao;
        this.dispatcherInfoCache = dispatcherInfoCache;
        this.senderInfoDao = senderInfoDao;
        this.senderInfoCache = senderInfoCache;
        this.metaDao = metaDao;
        this.metaCache = metaCache;
        this.metaIndicatorDao = metaIndicatorDao;
        this.metaIndicatorCache = metaIndicatorCache;
        this.variableDao = variableDao;
        this.variableCache = variableCache;
        this.variableIndicatorDao = variableIndicatorDao;
        this.variableIndicatorCache = variableIndicatorCache;
        this.sendHistoryDao = sendHistoryDao;
        this.sendHistoryCache = sendHistoryCache;
    }

    @Override
    public boolean exists(StringIdKey key) throws Exception {
        return topicCache.exists(key) || topicDao.exists(key);
    }

    @Override
    public Topic get(StringIdKey key) throws Exception {
        if (topicCache.exists(key)) {
            return topicCache.get(key);
        } else {
            if (!topicDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            Topic topic = topicDao.get(key);
            topicCache.push(topic, topicTimeout);
            return topic;
        }
    }

    @Override
    public StringIdKey insert(Topic topic) throws Exception {
        topicCache.push(topic, topicTimeout);
        return topicDao.insert(topic);
    }

    @Override
    public void update(Topic topic) throws Exception {
        topicCache.push(topic, topicTimeout);
        topicDao.update(topic);
    }

    @Override
    public void delete(StringIdKey key) throws Exception {
        // 删除与通知设置相关的调度器信息。
        if (dispatcherInfoDao.exists(key)) {
            dispatcherInfoCache.delete(key);
            dispatcherInfoDao.delete(key);
        }

        // 删除与通知设置相关的发送器信息。
        List<SenderInfoKey> senderInfoKeys = senderInfoDao.lookup(
                SenderInfoMaintainService.CHILD_FOR_TOPIC, new Object[]{key}
        ).stream().map(SenderInfo::getKey).collect(Collectors.toList());
        senderInfoDao.batchDelete(senderInfoKeys);
        senderInfoCache.batchDelete(senderInfoKeys);

        // 删除与通知设置相关的元数据。
        List<MetaKey> metaKeys = metaDao.lookup(
                MetaMaintainService.CHILD_FOR_TOPIC, new Object[]{key}
        ).stream().map(Meta::getKey).collect(Collectors.toList());
        metaDao.batchDelete(metaKeys);
        metaCache.batchDelete(metaKeys);

        // 删除与通知设置相关的元数据指示器。
        List<MetaIndicatorKey> metaIndicatorKeys = metaIndicatorDao.lookup(
                MetaIndicatorMaintainService.CHILD_FOR_TOPIC, new Object[]{key}
        ).stream().map(MetaIndicator::getKey).collect(Collectors.toList());
        metaIndicatorDao.batchDelete(metaIndicatorKeys);
        metaIndicatorCache.batchDelete(metaIndicatorKeys);

        // 删除与通知设置相关的变量。
        List<VariableKey> variableKeys = variableDao.lookup(
                VariableMaintainService.CHILD_FOR_TOPIC, new Object[]{key}
        ).stream().map(Variable::getKey).collect(Collectors.toList());
        variableDao.batchDelete(variableKeys);
        variableCache.batchDelete(variableKeys);

        // 删除与变量设置相关的元数据指示器。
        List<VariableIndicatorKey> variableIndicatorKeys = variableIndicatorDao.lookup(
                VariableIndicatorMaintainService.CHILD_FOR_TOPIC, new Object[]{key}
        ).stream().map(VariableIndicator::getKey).collect(Collectors.toList());
        variableIndicatorDao.batchDelete(variableIndicatorKeys);
        variableIndicatorCache.batchDelete(variableIndicatorKeys);

        // 删除与通知设置相关的发送历史的关联。
        List<SendHistory> sendHistories = sendHistoryDao.lookup(
                SendHistoryMaintainService.CHILD_FOR_TOPIC, new Object[]{key}
        );
        sendHistories.forEach(history -> history.setTopicKey(null));
        sendHistoryCache.batchDelete(sendHistories.stream().map(SendHistory::getKey).collect(Collectors.toList()));
        sendHistoryDao.batchUpdate(sendHistories);

        // 删除通知设置实体本身。
        topicDao.delete(key);
        topicCache.delete(key);
    }

    @Override
    public boolean allExists(List<StringIdKey> keys) throws Exception {
        return topicCache.allExists(keys) || topicDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<StringIdKey> keys) throws Exception {
        return topicCache.nonExists(keys) && topicCache.nonExists(keys);
    }

    @Override
    public List<Topic> batchGet(List<StringIdKey> keys) throws Exception {
        if (topicCache.allExists(keys)) {
            return topicCache.batchGet(keys);
        } else {
            if (!topicDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<Topic> topics = topicDao.batchGet(keys);
            topicCache.batchPush(topics, topicTimeout);
            return topics;
        }
    }

    @Override
    public List<StringIdKey> batchInsert(List<Topic> topics) throws Exception {
        topicCache.batchPush(topics, topicTimeout);
        return topicDao.batchInsert(topics);
    }

    @Override
    public void batchUpdate(List<Topic> topics) throws Exception {
        topicCache.batchPush(topics, topicTimeout);
        topicDao.batchUpdate(topics);
    }

    @Override
    public void batchDelete(List<StringIdKey> keys) throws Exception {
        for (StringIdKey key : keys) {
            delete(key);
        }
    }
}

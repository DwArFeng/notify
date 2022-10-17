package com.dwarfeng.notify.impl.service.operation;

import com.dwarfeng.notify.stack.bean.entity.*;
import com.dwarfeng.notify.stack.bean.entity.key.PreferenceIndicatorKey;
import com.dwarfeng.notify.stack.bean.entity.key.PreferenceKey;
import com.dwarfeng.notify.stack.bean.entity.key.SenderRelationKey;
import com.dwarfeng.notify.stack.bean.entity.key.VariableKey;
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

    private final SenderRelationDao senderRelationDao;
    private final SenderRelationCache senderRelationCache;

    private final PreferenceDao preferenceDao;
    private final PreferenceCache preferenceCache;

    private final PreferenceIndicatorDao preferenceIndicatorDao;
    private final PreferenceIndicatorCache preferenceIndicatorCache;

    private final VariableDao variableDao;
    private final VariableCache variableCache;

    private final SendHistoryDao sendHistoryDao;
    private final SendHistoryCache sendHistoryCache;

    @Value("${cache.timeout.entity.topic}")
    private long topicTimeout;

    public TopicCrudOperation(
            TopicDao topicDao, TopicCache topicCache,
            DispatcherInfoDao dispatcherInfoDao, DispatcherInfoCache dispatcherInfoCache,
            SenderRelationDao senderRelationDao, SenderRelationCache senderRelationCache,
            PreferenceDao preferenceDao, PreferenceCache preferenceCache,
            PreferenceIndicatorDao preferenceIndicatorDao, PreferenceIndicatorCache preferenceIndicatorCache,
            VariableDao variableDao, VariableCache variableCache,
            SendHistoryDao sendHistoryDao, SendHistoryCache sendHistoryCache
    ) {
        this.topicDao = topicDao;
        this.topicCache = topicCache;
        this.dispatcherInfoDao = dispatcherInfoDao;
        this.dispatcherInfoCache = dispatcherInfoCache;
        this.senderRelationDao = senderRelationDao;
        this.senderRelationCache = senderRelationCache;
        this.preferenceDao = preferenceDao;
        this.preferenceCache = preferenceCache;
        this.preferenceIndicatorDao = preferenceIndicatorDao;
        this.preferenceIndicatorCache = preferenceIndicatorCache;
        this.variableDao = variableDao;
        this.variableCache = variableCache;
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

        // 删除与通知设置相关的发送器关系。
        List<SenderRelationKey> senderRelationKeys = senderRelationDao.lookup(
                SenderRelationMaintainService.CHILD_FOR_TOPIC, new Object[]{key}
        ).stream().map(SenderRelation::getKey).collect(Collectors.toList());
        senderRelationDao.batchDelete(senderRelationKeys);
        senderRelationCache.batchDelete(senderRelationKeys);

        // 删除与通知设置相关的偏好。
        List<PreferenceKey> preferenceKeys = preferenceDao.lookup(
                PreferenceMaintainService.CHILD_FOR_TOPIC, new Object[]{key}
        ).stream().map(Preference::getKey).collect(Collectors.toList());
        preferenceDao.batchDelete(preferenceKeys);
        preferenceCache.batchDelete(preferenceKeys);

        // 删除与通知设置相关的偏好指示器。
        List<PreferenceIndicatorKey> preferenceIndicatorKeys = preferenceIndicatorDao.lookup(
                PreferenceIndicatorMaintainService.CHILD_FOR_TOPIC, new Object[]{key}
        ).stream().map(PreferenceIndicator::getKey).collect(Collectors.toList());
        preferenceIndicatorDao.batchDelete(preferenceIndicatorKeys);
        preferenceIndicatorCache.batchDelete(preferenceIndicatorKeys);

        // 删除与通知设置相关的变量。
        List<VariableKey> variableKeys = variableDao.lookup(
                VariableMaintainService.CHILD_FOR_TOPIC, new Object[]{key}
        ).stream().map(Variable::getKey).collect(Collectors.toList());
        variableDao.batchDelete(variableKeys);
        variableCache.batchDelete(variableKeys);

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

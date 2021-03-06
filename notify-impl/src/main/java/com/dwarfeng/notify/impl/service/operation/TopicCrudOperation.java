package com.dwarfeng.notify.impl.service.operation;

import com.dwarfeng.notify.stack.bean.entity.SenderRelation;
import com.dwarfeng.notify.stack.bean.entity.Topic;
import com.dwarfeng.notify.stack.bean.entity.key.SenderRelationKey;
import com.dwarfeng.notify.stack.cache.SenderRelationCache;
import com.dwarfeng.notify.stack.cache.TopicCache;
import com.dwarfeng.notify.stack.dao.SenderRelationDao;
import com.dwarfeng.notify.stack.dao.TopicDao;
import com.dwarfeng.notify.stack.service.SenderRelationMaintainService;
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

    private final SenderRelationDao senderRelationDao;
    private final SenderRelationCache senderRelationCache;

    @Value("${cache.timeout.entity.topic}")
    private long topicTimeout;

    public TopicCrudOperation(
            TopicDao topicDao, TopicCache topicCache,
            SenderRelationDao senderRelationDao, SenderRelationCache senderRelationCache
    ) {
        this.topicDao = topicDao;
        this.topicCache = topicCache;
        this.senderRelationDao = senderRelationDao;
        this.senderRelationCache = senderRelationCache;
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
        // ???????????????????????????????????????
        List<SenderRelationKey> senderRelationKeys = senderRelationDao.lookup(
                SenderRelationMaintainService.CHILD_FOR_TOPIC, new Object[]{key}
        ).stream().map(SenderRelation::getKey).collect(Collectors.toList());
        senderRelationDao.batchDelete(senderRelationKeys);
        senderRelationCache.batchDelete(senderRelationKeys);

        // ?????????????????????????????????
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

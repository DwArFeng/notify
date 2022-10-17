package com.dwarfeng.notify.impl.cache;

import com.dwarfeng.notify.sdk.bean.entity.FastJsonSenderRelation;
import com.dwarfeng.notify.stack.bean.entity.SenderRelation;
import com.dwarfeng.notify.stack.bean.entity.key.SenderRelationKey;
import com.dwarfeng.notify.stack.cache.SenderRelationCache;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.SkipRecord;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class SenderRelationCacheImpl implements SenderRelationCache {

    private final RedisBatchBaseCache<SenderRelationKey, SenderRelation, FastJsonSenderRelation>
            senderRelationBatchBaseDelegate;

    public SenderRelationCacheImpl(
            RedisBatchBaseCache<SenderRelationKey, SenderRelation, FastJsonSenderRelation>
                    senderRelationBatchBaseDelegate
    ) {
        this.senderRelationBatchBaseDelegate = senderRelationBatchBaseDelegate;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean exists(SenderRelationKey key) throws CacheException {
        return senderRelationBatchBaseDelegate.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public SenderRelation get(SenderRelationKey key) throws CacheException {
        return senderRelationBatchBaseDelegate.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void push(SenderRelation value, long timeout) throws CacheException {
        senderRelationBatchBaseDelegate.push(value, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void delete(SenderRelationKey key) throws CacheException {
        senderRelationBatchBaseDelegate.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void clear() throws CacheException {
        senderRelationBatchBaseDelegate.clear();
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean allExists(@SkipRecord List<SenderRelationKey> keys) throws CacheException {
        return senderRelationBatchBaseDelegate.allExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean nonExists(@SkipRecord List<SenderRelationKey> keys) throws CacheException {
        return senderRelationBatchBaseDelegate.nonExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<SenderRelation> batchGet(@SkipRecord List<SenderRelationKey> keys) throws CacheException {
        return senderRelationBatchBaseDelegate.batchGet(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchPush(@SkipRecord List<SenderRelation> entities, long timeout) throws CacheException {
        senderRelationBatchBaseDelegate.batchPush(entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchDelete(@SkipRecord List<SenderRelationKey> keys) throws CacheException {
        senderRelationBatchBaseDelegate.batchDelete(keys);
    }
}

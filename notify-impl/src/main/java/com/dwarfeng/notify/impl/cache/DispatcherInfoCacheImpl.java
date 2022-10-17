package com.dwarfeng.notify.impl.cache;

import com.dwarfeng.notify.sdk.bean.entity.FastJsonDispatcherInfo;
import com.dwarfeng.notify.stack.bean.entity.DispatcherInfo;
import com.dwarfeng.notify.stack.cache.DispatcherInfoCache;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.SkipRecord;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DispatcherInfoCacheImpl implements DispatcherInfoCache {

    private final RedisBatchBaseCache<StringIdKey, DispatcherInfo, FastJsonDispatcherInfo>
            dispatcherInfoBatchBaseDelegate;

    public DispatcherInfoCacheImpl(
            RedisBatchBaseCache<StringIdKey, DispatcherInfo, FastJsonDispatcherInfo> dispatcherInfoBatchBaseDelegate
    ) {
        this.dispatcherInfoBatchBaseDelegate = dispatcherInfoBatchBaseDelegate;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean exists(StringIdKey key) throws CacheException {
        return dispatcherInfoBatchBaseDelegate.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public DispatcherInfo get(StringIdKey key) throws CacheException {
        return dispatcherInfoBatchBaseDelegate.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void push(DispatcherInfo value, long timeout) throws CacheException {
        dispatcherInfoBatchBaseDelegate.push(value, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void delete(StringIdKey key) throws CacheException {
        dispatcherInfoBatchBaseDelegate.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void clear() throws CacheException {
        dispatcherInfoBatchBaseDelegate.clear();
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean allExists(@SkipRecord List<StringIdKey> keys) throws CacheException {
        return dispatcherInfoBatchBaseDelegate.allExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean nonExists(@SkipRecord List<StringIdKey> keys) throws CacheException {
        return dispatcherInfoBatchBaseDelegate.nonExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<DispatcherInfo> batchGet(@SkipRecord List<StringIdKey> keys) throws CacheException {
        return dispatcherInfoBatchBaseDelegate.batchGet(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchPush(@SkipRecord List<DispatcherInfo> entities, long timeout) throws CacheException {
        dispatcherInfoBatchBaseDelegate.batchPush(entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchDelete(@SkipRecord List<StringIdKey> keys) throws CacheException {
        dispatcherInfoBatchBaseDelegate.batchDelete(keys);
    }
}

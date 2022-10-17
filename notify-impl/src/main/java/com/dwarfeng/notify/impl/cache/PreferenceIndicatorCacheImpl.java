package com.dwarfeng.notify.impl.cache;

import com.dwarfeng.notify.sdk.bean.entity.FastJsonPreferenceIndicator;
import com.dwarfeng.notify.stack.bean.entity.PreferenceIndicator;
import com.dwarfeng.notify.stack.bean.entity.key.PreferenceIndicatorKey;
import com.dwarfeng.notify.stack.cache.PreferenceIndicatorCache;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.SkipRecord;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PreferenceIndicatorCacheImpl implements PreferenceIndicatorCache {

    private final RedisBatchBaseCache<PreferenceIndicatorKey, PreferenceIndicator, FastJsonPreferenceIndicator>
            preferenceIndicatorBatchBaseDelegate;

    public PreferenceIndicatorCacheImpl(
            RedisBatchBaseCache<PreferenceIndicatorKey, PreferenceIndicator, FastJsonPreferenceIndicator>
                    preferenceIndicatorBatchBaseDelegate
    ) {
        this.preferenceIndicatorBatchBaseDelegate = preferenceIndicatorBatchBaseDelegate;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean exists(PreferenceIndicatorKey key) throws CacheException {
        return preferenceIndicatorBatchBaseDelegate.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public PreferenceIndicator get(PreferenceIndicatorKey key) throws CacheException {
        return preferenceIndicatorBatchBaseDelegate.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void push(PreferenceIndicator value, long timeout) throws CacheException {
        preferenceIndicatorBatchBaseDelegate.push(value, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void delete(PreferenceIndicatorKey key) throws CacheException {
        preferenceIndicatorBatchBaseDelegate.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void clear() throws CacheException {
        preferenceIndicatorBatchBaseDelegate.clear();
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean allExists(@SkipRecord List<PreferenceIndicatorKey> keys) throws CacheException {
        return preferenceIndicatorBatchBaseDelegate.allExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean nonExists(@SkipRecord List<PreferenceIndicatorKey> keys) throws CacheException {
        return preferenceIndicatorBatchBaseDelegate.nonExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<PreferenceIndicator> batchGet(@SkipRecord List<PreferenceIndicatorKey> keys) throws CacheException {
        return preferenceIndicatorBatchBaseDelegate.batchGet(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchPush(@SkipRecord List<PreferenceIndicator> entities, long timeout) throws CacheException {
        preferenceIndicatorBatchBaseDelegate.batchPush(entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchDelete(@SkipRecord List<PreferenceIndicatorKey> keys) throws CacheException {
        preferenceIndicatorBatchBaseDelegate.batchDelete(keys);
    }
}

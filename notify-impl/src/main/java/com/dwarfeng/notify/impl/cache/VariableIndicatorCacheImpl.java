package com.dwarfeng.notify.impl.cache;

import com.dwarfeng.notify.sdk.bean.entity.FastJsonVariableIndicator;
import com.dwarfeng.notify.stack.bean.entity.VariableIndicator;
import com.dwarfeng.notify.stack.bean.entity.key.VariableIndicatorKey;
import com.dwarfeng.notify.stack.cache.VariableIndicatorCache;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.SkipRecord;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class VariableIndicatorCacheImpl implements VariableIndicatorCache {

    private final RedisBatchBaseCache<VariableIndicatorKey, VariableIndicator, FastJsonVariableIndicator>
            variableIndicatorBatchBaseDelegate;

    public VariableIndicatorCacheImpl(
            RedisBatchBaseCache<VariableIndicatorKey, VariableIndicator, FastJsonVariableIndicator>
                    variableIndicatorBatchBaseDelegate
    ) {
        this.variableIndicatorBatchBaseDelegate = variableIndicatorBatchBaseDelegate;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean exists(VariableIndicatorKey key) throws CacheException {
        return variableIndicatorBatchBaseDelegate.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public VariableIndicator get(VariableIndicatorKey key) throws CacheException {
        return variableIndicatorBatchBaseDelegate.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void push(VariableIndicator value, long timeout) throws CacheException {
        variableIndicatorBatchBaseDelegate.push(value, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void delete(VariableIndicatorKey key) throws CacheException {
        variableIndicatorBatchBaseDelegate.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void clear() throws CacheException {
        variableIndicatorBatchBaseDelegate.clear();
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean allExists(@SkipRecord List<VariableIndicatorKey> keys) throws CacheException {
        return variableIndicatorBatchBaseDelegate.allExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean nonExists(@SkipRecord List<VariableIndicatorKey> keys) throws CacheException {
        return variableIndicatorBatchBaseDelegate.nonExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<VariableIndicator> batchGet(@SkipRecord List<VariableIndicatorKey> keys) throws CacheException {
        return variableIndicatorBatchBaseDelegate.batchGet(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchPush(@SkipRecord List<VariableIndicator> entities, long timeout) throws CacheException {
        variableIndicatorBatchBaseDelegate.batchPush(entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchDelete(@SkipRecord List<VariableIndicatorKey> keys) throws CacheException {
        variableIndicatorBatchBaseDelegate.batchDelete(keys);
    }
}

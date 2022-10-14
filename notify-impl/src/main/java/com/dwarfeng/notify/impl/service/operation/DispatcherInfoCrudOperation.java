package com.dwarfeng.notify.impl.service.operation;

import com.dwarfeng.notify.stack.bean.entity.DispatcherInfo;
import com.dwarfeng.notify.stack.bean.entity.Relation;
import com.dwarfeng.notify.stack.bean.entity.key.RelationKey;
import com.dwarfeng.notify.stack.cache.DispatcherInfoCache;
import com.dwarfeng.notify.stack.cache.RelationCache;
import com.dwarfeng.notify.stack.dao.DispatcherInfoDao;
import com.dwarfeng.notify.stack.dao.RelationDao;
import com.dwarfeng.notify.stack.service.RelationMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DispatcherInfoCrudOperation implements BatchCrudOperation<LongIdKey, DispatcherInfo> {

    private final DispatcherInfoDao dispatcherInfoDao;
    private final DispatcherInfoCache dispatcherInfoCache;

    private final RelationDao relationDao;
    private final RelationCache relationCache;

    @Value("${cache.timeout.entity.dispatcher_info}")
    private long dispatcherInfoTimeout;

    public DispatcherInfoCrudOperation(
            DispatcherInfoDao dispatcherInfoDao, DispatcherInfoCache dispatcherInfoCache,
            RelationDao relationDao, RelationCache relationCache
    ) {
        this.dispatcherInfoDao = dispatcherInfoDao;
        this.dispatcherInfoCache = dispatcherInfoCache;
        this.relationDao = relationDao;
        this.relationCache = relationCache;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return dispatcherInfoCache.exists(key) || dispatcherInfoDao.exists(key);
    }

    @Override
    public DispatcherInfo get(LongIdKey key) throws Exception {
        if (dispatcherInfoCache.exists(key)) {
            return dispatcherInfoCache.get(key);
        } else {
            if (!dispatcherInfoDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            DispatcherInfo dispatcherInfo = dispatcherInfoDao.get(key);
            dispatcherInfoCache.push(dispatcherInfo, dispatcherInfoTimeout);
            return dispatcherInfo;
        }
    }

    @Override
    public LongIdKey insert(DispatcherInfo dispatcherInfo) throws Exception {
        dispatcherInfoCache.push(dispatcherInfo, dispatcherInfoTimeout);
        return dispatcherInfoDao.insert(dispatcherInfo);
    }

    @Override
    public void update(DispatcherInfo dispatcherInfo) throws Exception {
        dispatcherInfoCache.push(dispatcherInfo, dispatcherInfoTimeout);
        dispatcherInfoDao.update(dispatcherInfo);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 删除与通知设置相关的关系。
        List<RelationKey> relationKeys = relationDao.lookup(
                RelationMaintainService.CHILD_FOR_DISPATCHER_INFO, new Object[]{key}
        ).stream().map(Relation::getKey).collect(Collectors.toList());
        relationDao.batchDelete(relationKeys);
        relationCache.batchDelete(relationKeys);

        // 删除通知设置实体本身。
        dispatcherInfoDao.delete(key);
        dispatcherInfoCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return dispatcherInfoCache.allExists(keys) || dispatcherInfoDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return dispatcherInfoCache.nonExists(keys) && dispatcherInfoCache.nonExists(keys);
    }

    @Override
    public List<DispatcherInfo> batchGet(List<LongIdKey> keys) throws Exception {
        if (dispatcherInfoCache.allExists(keys)) {
            return dispatcherInfoCache.batchGet(keys);
        } else {
            if (!dispatcherInfoDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<DispatcherInfo> dispatcherInfos = dispatcherInfoDao.batchGet(keys);
            dispatcherInfoCache.batchPush(dispatcherInfos, dispatcherInfoTimeout);
            return dispatcherInfos;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<DispatcherInfo> dispatcherInfos) throws Exception {
        dispatcherInfoCache.batchPush(dispatcherInfos, dispatcherInfoTimeout);
        return dispatcherInfoDao.batchInsert(dispatcherInfos);
    }

    @Override
    public void batchUpdate(List<DispatcherInfo> dispatcherInfos) throws Exception {
        dispatcherInfoCache.batchPush(dispatcherInfos, dispatcherInfoTimeout);
        dispatcherInfoDao.batchUpdate(dispatcherInfos);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}

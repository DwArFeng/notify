package com.dwarfeng.notify.impl.service.operation;

import com.dwarfeng.notify.stack.bean.entity.SenderInfo;
import com.dwarfeng.notify.stack.bean.entity.SenderRelation;
import com.dwarfeng.notify.stack.bean.entity.key.SenderRelationKey;
import com.dwarfeng.notify.stack.cache.SenderInfoCache;
import com.dwarfeng.notify.stack.cache.SenderRelationCache;
import com.dwarfeng.notify.stack.dao.SenderInfoDao;
import com.dwarfeng.notify.stack.dao.SenderRelationDao;
import com.dwarfeng.notify.stack.service.SenderRelationMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SenderInfoCrudOperation implements BatchCrudOperation<LongIdKey, SenderInfo> {

    private final SenderInfoDao senderInfoDao;
    private final SenderInfoCache senderInfoCache;

    private final SenderRelationDao senderRelationDao;
    private final SenderRelationCache senderRelationCache;

    @Value("${cache.timeout.entity.sender_info}")
    private long senderInfoTimeout;

    public SenderInfoCrudOperation(
            SenderInfoDao senderInfoDao, SenderInfoCache senderInfoCache,
            SenderRelationDao senderRelationDao, SenderRelationCache senderRelationCache
    ) {
        this.senderInfoDao = senderInfoDao;
        this.senderInfoCache = senderInfoCache;
        this.senderRelationDao = senderRelationDao;
        this.senderRelationCache = senderRelationCache;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return senderInfoCache.exists(key) || senderInfoDao.exists(key);
    }

    @Override
    public SenderInfo get(LongIdKey key) throws Exception {
        if (senderInfoCache.exists(key)) {
            return senderInfoCache.get(key);
        } else {
            if (!senderInfoDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            SenderInfo senderInfo = senderInfoDao.get(key);
            senderInfoCache.push(senderInfo, senderInfoTimeout);
            return senderInfo;
        }
    }

    @Override
    public LongIdKey insert(SenderInfo senderInfo) throws Exception {
        senderInfoCache.push(senderInfo, senderInfoTimeout);
        return senderInfoDao.insert(senderInfo);
    }

    @Override
    public void update(SenderInfo senderInfo) throws Exception {
        senderInfoCache.push(senderInfo, senderInfoTimeout);
        senderInfoDao.update(senderInfo);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 删除与通知设置相关的发送器关系。
        List<SenderRelationKey> senderRelationKeys = senderRelationDao.lookup(
                SenderRelationMaintainService.CHILD_FOR_SENDER_INFO, new Object[]{key}
        ).stream().map(SenderRelation::getKey).collect(Collectors.toList());
        senderRelationDao.batchDelete(senderRelationKeys);
        senderRelationCache.batchDelete(senderRelationKeys);

        // 删除通知设置实体本身。
        senderInfoDao.delete(key);
        senderInfoCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return senderInfoCache.allExists(keys) || senderInfoDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return senderInfoCache.nonExists(keys) && senderInfoCache.nonExists(keys);
    }

    @Override
    public List<SenderInfo> batchGet(List<LongIdKey> keys) throws Exception {
        if (senderInfoCache.allExists(keys)) {
            return senderInfoCache.batchGet(keys);
        } else {
            if (!senderInfoDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<SenderInfo> senderInfos = senderInfoDao.batchGet(keys);
            senderInfoCache.batchPush(senderInfos, senderInfoTimeout);
            return senderInfos;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<SenderInfo> senderInfos) throws Exception {
        senderInfoCache.batchPush(senderInfos, senderInfoTimeout);
        return senderInfoDao.batchInsert(senderInfos);
    }

    @Override
    public void batchUpdate(List<SenderInfo> senderInfos) throws Exception {
        senderInfoCache.batchPush(senderInfos, senderInfoTimeout);
        senderInfoDao.batchUpdate(senderInfos);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}

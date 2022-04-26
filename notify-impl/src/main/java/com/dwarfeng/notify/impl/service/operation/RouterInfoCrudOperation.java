package com.dwarfeng.notify.impl.service.operation;

import com.dwarfeng.notify.stack.bean.entity.RouterInfo;
import com.dwarfeng.notify.stack.cache.EnabledRouterInfoCache;
import com.dwarfeng.notify.stack.cache.RouterInfoCache;
import com.dwarfeng.notify.stack.dao.RouterInfoDao;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class RouterInfoCrudOperation implements BatchCrudOperation<LongIdKey, RouterInfo> {

    private final RouterInfoDao routerInfoDao;
    private final RouterInfoCache routerInfoCache;

    private final EnabledRouterInfoCache enabledRouterInfoCache;

    @Value("${cache.timeout.entity.router_info}")
    private long routerInfoTimeout;

    public RouterInfoCrudOperation(
            RouterInfoDao routerInfoDao, RouterInfoCache routerInfoCache,
            EnabledRouterInfoCache enabledRouterInfoCache
    ) {
        this.routerInfoDao = routerInfoDao;
        this.routerInfoCache = routerInfoCache;
        this.enabledRouterInfoCache = enabledRouterInfoCache;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return routerInfoCache.exists(key) || routerInfoDao.exists(key);
    }

    @Override
    public RouterInfo get(LongIdKey key) throws Exception {
        if (routerInfoCache.exists(key)) {
            return routerInfoCache.get(key);
        } else {
            if (!routerInfoDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            RouterInfo routerInfo = routerInfoDao.get(key);
            routerInfoCache.push(routerInfo, routerInfoTimeout);
            return routerInfo;
        }
    }

    @Override
    public LongIdKey insert(RouterInfo routerInfo) throws Exception {
        if (Objects.nonNull(routerInfo.getNotifySettingKey())) {
            enabledRouterInfoCache.delete(routerInfo.getNotifySettingKey());
        }

        routerInfoCache.push(routerInfo, routerInfoTimeout);
        return routerInfoDao.insert(routerInfo);
    }

    @Override
    public void update(RouterInfo routerInfo) throws Exception {
        RouterInfo oldRouterInfo = get(routerInfo.getKey());

        if (Objects.nonNull(oldRouterInfo.getNotifySettingKey())) {
            enabledRouterInfoCache.delete(oldRouterInfo.getNotifySettingKey());
        }
        if (Objects.nonNull(routerInfo.getNotifySettingKey())) {
            enabledRouterInfoCache.delete(routerInfo.getNotifySettingKey());
        }

        routerInfoCache.push(routerInfo, routerInfoTimeout);
        routerInfoDao.update(routerInfo);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        RouterInfo oldRouterInfo = get(key);
        if (Objects.nonNull(oldRouterInfo.getNotifySettingKey())) {
            enabledRouterInfoCache.delete(oldRouterInfo.getNotifySettingKey());
        }

        routerInfoDao.delete(key);
        routerInfoCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return routerInfoCache.allExists(keys) || routerInfoDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return routerInfoCache.nonExists(keys) && routerInfoCache.nonExists(keys);
    }

    @Override
    public List<RouterInfo> batchGet(List<LongIdKey> keys) throws Exception {
        if (routerInfoCache.allExists(keys)) {
            return routerInfoCache.batchGet(keys);
        } else {
            if (!routerInfoDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<RouterInfo> routerInfos = routerInfoDao.batchGet(keys);
            routerInfoCache.batchPush(routerInfos, routerInfoTimeout);
            return routerInfos;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<RouterInfo> routerInfos) throws Exception {
        List<LongIdKey> keys = new ArrayList<>();
        for (RouterInfo routerInfo : routerInfos) {
            keys.add(insert(routerInfo));
        }
        return keys;
    }

    @Override
    public void batchUpdate(List<RouterInfo> routerInfos) throws Exception {
        for (RouterInfo routerInfo : routerInfos) {
            update(routerInfo);
        }
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}

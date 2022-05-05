package com.dwarfeng.notify.impl.service.operation;

import com.dwarfeng.notify.stack.bean.entity.NotifySetting;
import com.dwarfeng.notify.stack.bean.entity.RouterInfo;
import com.dwarfeng.notify.stack.cache.NotifySettingCache;
import com.dwarfeng.notify.stack.cache.RouterInfoCache;
import com.dwarfeng.notify.stack.dao.NotifySettingDao;
import com.dwarfeng.notify.stack.dao.RouterInfoDao;
import com.dwarfeng.notify.stack.service.RouterInfoMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NotifySettingCrudOperation implements BatchCrudOperation<LongIdKey, NotifySetting> {

    private final NotifySettingDao notifySettingDao;
    private final NotifySettingCache notifySettingCache;

    private final RouterInfoDao routerInfoDao;
    private final RouterInfoCache routerInfoCache;

    @Value("${cache.timeout.entity.notify_setting}")
    private long notifySettingTimeout;

    public NotifySettingCrudOperation(
            NotifySettingDao notifySettingDao, NotifySettingCache notifySettingCache,
            RouterInfoDao routerInfoDao, RouterInfoCache routerInfoCache
    ) {
        this.notifySettingDao = notifySettingDao;
        this.notifySettingCache = notifySettingCache;
        this.routerInfoDao = routerInfoDao;
        this.routerInfoCache = routerInfoCache;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return notifySettingCache.exists(key) || notifySettingDao.exists(key);
    }

    @Override
    public NotifySetting get(LongIdKey key) throws Exception {
        if (notifySettingCache.exists(key)) {
            return notifySettingCache.get(key);
        } else {
            if (!notifySettingDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            NotifySetting notifySetting = notifySettingDao.get(key);
            notifySettingCache.push(notifySetting, notifySettingTimeout);
            return notifySetting;
        }
    }

    @Override
    public LongIdKey insert(NotifySetting notifySetting) throws Exception {
        notifySettingCache.push(notifySetting, notifySettingTimeout);
        return notifySettingDao.insert(notifySetting);
    }

    @Override
    public void update(NotifySetting notifySetting) throws Exception {
        notifySettingCache.push(notifySetting, notifySettingTimeout);
        notifySettingDao.update(notifySetting);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 删除与通知设置相关的路由器。
        List<LongIdKey> routerInfoKeys = routerInfoDao.lookup(
                RouterInfoMaintainService.CHILD_FOR_NOTIFY_SETTING, new Object[]{key}
        ).stream().map(RouterInfo::getKey).collect(Collectors.toList());
        routerInfoDao.batchDelete(routerInfoKeys);
        routerInfoCache.batchDelete(routerInfoKeys);

        // 删除通知设置实体本身。
        notifySettingDao.delete(key);
        notifySettingCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return notifySettingCache.allExists(keys) || notifySettingDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return notifySettingCache.nonExists(keys) && notifySettingCache.nonExists(keys);
    }

    @Override
    public List<NotifySetting> batchGet(List<LongIdKey> keys) throws Exception {
        if (notifySettingCache.allExists(keys)) {
            return notifySettingCache.batchGet(keys);
        } else {
            if (!notifySettingDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<NotifySetting> notifySettings = notifySettingDao.batchGet(keys);
            notifySettingCache.batchPush(notifySettings, notifySettingTimeout);
            return notifySettings;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<NotifySetting> notifySettings) throws Exception {
        notifySettingCache.batchPush(notifySettings, notifySettingTimeout);
        return notifySettingDao.batchInsert(notifySettings);
    }

    @Override
    public void batchUpdate(List<NotifySetting> notifySettings) throws Exception {
        notifySettingCache.batchPush(notifySettings, notifySettingTimeout);
        notifySettingDao.batchUpdate(notifySettings);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}

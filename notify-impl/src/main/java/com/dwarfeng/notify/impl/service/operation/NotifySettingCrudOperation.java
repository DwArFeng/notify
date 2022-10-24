package com.dwarfeng.notify.impl.service.operation;

import com.dwarfeng.notify.stack.bean.entity.*;
import com.dwarfeng.notify.stack.bean.entity.key.MetaKey;
import com.dwarfeng.notify.stack.bean.entity.key.SenderInfoKey;
import com.dwarfeng.notify.stack.bean.entity.key.VariableKey;
import com.dwarfeng.notify.stack.cache.*;
import com.dwarfeng.notify.stack.dao.*;
import com.dwarfeng.notify.stack.service.MetaMaintainService;
import com.dwarfeng.notify.stack.service.SendHistoryMaintainService;
import com.dwarfeng.notify.stack.service.SenderInfoMaintainService;
import com.dwarfeng.notify.stack.service.VariableMaintainService;
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

    private final SenderInfoDao senderInfoDao;
    private final SenderInfoCache senderInfoCache;

    private final MetaDao metaDao;
    private final MetaCache metaCache;

    private final VariableDao variableDao;
    private final VariableCache variableCache;

    private final SendHistoryDao sendHistoryDao;
    private final SendHistoryCache sendHistoryCache;

    @Value("${cache.timeout.entity.notify_setting}")
    private long notifySettingTimeout;

    public NotifySettingCrudOperation(
            NotifySettingDao notifySettingDao, NotifySettingCache notifySettingCache,
            RouterInfoDao routerInfoDao, RouterInfoCache routerInfoCache,
            SenderInfoDao senderInfoDao, SenderInfoCache senderInfoCache,
            MetaDao metaDao, MetaCache metaCache,
            VariableDao variableDao, VariableCache variableCache,
            SendHistoryDao sendHistoryDao, SendHistoryCache sendHistoryCache
    ) {
        this.notifySettingDao = notifySettingDao;
        this.notifySettingCache = notifySettingCache;
        this.routerInfoDao = routerInfoDao;
        this.routerInfoCache = routerInfoCache;
        this.senderInfoDao = senderInfoDao;
        this.senderInfoCache = senderInfoCache;
        this.metaDao = metaDao;
        this.metaCache = metaCache;
        this.variableDao = variableDao;
        this.variableCache = variableCache;
        this.sendHistoryDao = sendHistoryDao;
        this.sendHistoryCache = sendHistoryCache;
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
        // 删除与通知设置相关的路由器信息。
        if (routerInfoDao.exists(key)) {
            routerInfoCache.delete(key);
            routerInfoDao.delete(key);
        }

        // 删除与通知设置相关的发送器信息。
        List<SenderInfoKey> senderInfoKeys = senderInfoDao.lookup(
                SenderInfoMaintainService.CHILD_FOR_NOTIFY_SETTING, new Object[]{key}
        ).stream().map(SenderInfo::getKey).collect(Collectors.toList());
        senderInfoDao.batchDelete(senderInfoKeys);
        senderInfoCache.batchDelete(senderInfoKeys);

        // 删除与通知设置相关的元数据。
        List<MetaKey> metaKeys = metaDao.lookup(
                MetaMaintainService.CHILD_FOR_NOTIFY_SETTING, new Object[]{key}
        ).stream().map(Meta::getKey).collect(Collectors.toList());
        metaDao.batchDelete(metaKeys);
        metaCache.batchDelete(metaKeys);

        // 删除与通知设置相关的变量。
        List<VariableKey> variableKeys = variableDao.lookup(
                VariableMaintainService.CHILD_FOR_NOTIFY_SETTING, new Object[]{key}
        ).stream().map(Variable::getKey).collect(Collectors.toList());
        variableDao.batchDelete(variableKeys);
        variableCache.batchDelete(variableKeys);

        // 删除与通知设置相关的发送历史的关联。
        List<SendHistory> sendHistories = sendHistoryDao.lookup(
                SendHistoryMaintainService.CHILD_FOR_NOTIFY_SETTING, new Object[]{key}
        );
        sendHistories.forEach(history -> history.setNotifySettingKey(null));
        sendHistoryCache.batchDelete(sendHistories.stream().map(SendHistory::getKey).collect(Collectors.toList()));
        sendHistoryDao.batchUpdate(sendHistories);

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

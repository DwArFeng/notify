package com.dwarfeng.notify.impl.service.operation;

import com.dwarfeng.notify.stack.bean.entity.NotifyHistory;
import com.dwarfeng.notify.stack.bean.entity.NotifyInfoRecord;
import com.dwarfeng.notify.stack.bean.entity.NotifySendRecord;
import com.dwarfeng.notify.stack.bean.key.RecordKey;
import com.dwarfeng.notify.stack.cache.NotifyHistoryCache;
import com.dwarfeng.notify.stack.cache.NotifyInfoRecordCache;
import com.dwarfeng.notify.stack.cache.NotifySendRecordCache;
import com.dwarfeng.notify.stack.dao.NotifyHistoryDao;
import com.dwarfeng.notify.stack.dao.NotifyInfoRecordDao;
import com.dwarfeng.notify.stack.dao.NotifySendRecordDao;
import com.dwarfeng.notify.stack.service.NotifyInfoRecordMaintainService;
import com.dwarfeng.notify.stack.service.NotifySendRecordMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NotifyHistoryCrudOperation implements BatchCrudOperation<LongIdKey, NotifyHistory> {

    private final NotifyHistoryDao notifyHistoryDao;
    private final NotifyHistoryCache notifyHistoryCache;

    private final NotifyInfoRecordDao notifyInfoRecordDao;
    private final NotifyInfoRecordCache notifyInfoRecordCache;

    private final NotifySendRecordDao notifySendRecordDao;
    private final NotifySendRecordCache notifySendRecordCache;

    @Value("${cache.timeout.entity.notify_history}")
    private long notifyHistoryTimeout;

    public NotifyHistoryCrudOperation(
            NotifyHistoryDao notifyHistoryDao, NotifyHistoryCache notifyHistoryCache,
            NotifyInfoRecordDao notifyInfoRecordDao, NotifyInfoRecordCache notifyInfoRecordCache,
            NotifySendRecordDao notifySendRecordDao, NotifySendRecordCache notifySendRecordCache
    ) {
        this.notifyHistoryDao = notifyHistoryDao;
        this.notifyHistoryCache = notifyHistoryCache;
        this.notifyInfoRecordDao = notifyInfoRecordDao;
        this.notifyInfoRecordCache = notifyInfoRecordCache;
        this.notifySendRecordDao = notifySendRecordDao;
        this.notifySendRecordCache = notifySendRecordCache;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return notifyHistoryCache.exists(key) || notifyHistoryDao.exists(key);
    }

    @Override
    public NotifyHistory get(LongIdKey key) throws Exception {
        if (notifyHistoryCache.exists(key)) {
            return notifyHistoryCache.get(key);
        } else {
            if (!notifyHistoryDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            NotifyHistory notifyHistory = notifyHistoryDao.get(key);
            notifyHistoryCache.push(notifyHistory, notifyHistoryTimeout);
            return notifyHistory;
        }
    }

    @Override
    public LongIdKey insert(NotifyHistory notifyHistory) throws Exception {
        notifyHistoryCache.push(notifyHistory, notifyHistoryTimeout);
        return notifyHistoryDao.insert(notifyHistory);
    }

    @Override
    public void update(NotifyHistory notifyHistory) throws Exception {
        notifyHistoryCache.push(notifyHistory, notifyHistoryTimeout);
        notifyHistoryDao.update(notifyHistory);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 删除与账户相关的通知信息记录。
        List<RecordKey> notifyInfoRecordKeys = notifyInfoRecordDao.lookup(
                NotifyInfoRecordMaintainService.CHILD_FOR_NOTIFY_HISTORY, new Object[]{key}
        ).stream().map(NotifyInfoRecord::getKey).collect(Collectors.toList());
        notifyInfoRecordCache.batchDelete(notifyInfoRecordKeys);
        notifyInfoRecordDao.batchDelete(notifyInfoRecordKeys);

        // 删除与账户相关的通知发送信息记录。
        List<LongIdKey> notifySendRecordKeys = notifySendRecordDao.lookup(
                NotifySendRecordMaintainService.CHILD_FOR_NOTIFY_HISTORY, new Object[]{key}
        ).stream().map(NotifySendRecord::getKey).collect(Collectors.toList());
        notifySendRecordCache.batchDelete(notifySendRecordKeys);
        notifySendRecordDao.batchDelete(notifySendRecordKeys);

        // 删除报警设置本身。
        notifyHistoryDao.delete(key);
        notifyHistoryCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return notifyHistoryCache.allExists(keys) || notifyHistoryDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return notifyHistoryCache.nonExists(keys) && notifyHistoryCache.nonExists(keys);
    }

    @Override
    public List<NotifyHistory> batchGet(List<LongIdKey> keys) throws Exception {
        if (notifyHistoryCache.allExists(keys)) {
            return notifyHistoryCache.batchGet(keys);
        } else {
            if (!notifyHistoryDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<NotifyHistory> notifyHistories = notifyHistoryDao.batchGet(keys);
            notifyHistoryCache.batchPush(notifyHistories, notifyHistoryTimeout);
            return notifyHistories;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<NotifyHistory> notifyHistorys) throws Exception {
        notifyHistoryCache.batchPush(notifyHistorys, notifyHistoryTimeout);
        return notifyHistoryDao.batchInsert(notifyHistorys);
    }

    @Override
    public void batchUpdate(List<NotifyHistory> notifyHistorys) throws Exception {
        notifyHistoryCache.batchPush(notifyHistorys, notifyHistoryTimeout);
        notifyHistoryDao.batchUpdate(notifyHistorys);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}

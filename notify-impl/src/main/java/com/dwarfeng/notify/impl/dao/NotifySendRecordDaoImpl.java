package com.dwarfeng.notify.impl.dao;

import com.dwarfeng.notify.impl.bean.entity.HibernateNotifySendRecord;
import com.dwarfeng.notify.impl.bean.key.HibernateNotifySendRecordKey;
import com.dwarfeng.notify.stack.bean.entity.NotifySendRecord;
import com.dwarfeng.notify.stack.bean.key.NotifySendRecordKey;
import com.dwarfeng.notify.stack.dao.NotifySendRecordDao;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchBaseDao;
import com.dwarfeng.subgrade.impl.dao.HibernateEntireLookupDao;
import com.dwarfeng.subgrade.impl.dao.HibernatePresetLookupDao;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.SkipRecord;
import com.dwarfeng.subgrade.stack.bean.dto.PagingInfo;
import com.dwarfeng.subgrade.stack.exception.DaoException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class NotifySendRecordDaoImpl implements NotifySendRecordDao {

    private final HibernateBatchBaseDao<NotifySendRecordKey, HibernateNotifySendRecordKey, NotifySendRecord,
            HibernateNotifySendRecord> batchDelegate;
    private final HibernateEntireLookupDao<NotifySendRecord, HibernateNotifySendRecord> entireLookupDelegate;
    private final HibernatePresetLookupDao<NotifySendRecord, HibernateNotifySendRecord> presetLookupDelegate;

    public NotifySendRecordDaoImpl(
            HibernateBatchBaseDao<NotifySendRecordKey, HibernateNotifySendRecordKey, NotifySendRecord,
                    HibernateNotifySendRecord> batchDelegate,
            HibernateEntireLookupDao<NotifySendRecord, HibernateNotifySendRecord> entireLookupDelegate,
            HibernatePresetLookupDao<NotifySendRecord, HibernateNotifySendRecord> presetLookupDelegate
    ) {
        this.batchDelegate = batchDelegate;
        this.entireLookupDelegate = entireLookupDelegate;
        this.presetLookupDelegate = presetLookupDelegate;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public NotifySendRecordKey insert(NotifySendRecord element) throws DaoException {
        return batchDelegate.insert(element);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void update(NotifySendRecord element) throws DaoException {
        batchDelegate.update(element);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void delete(NotifySendRecordKey key) throws DaoException {
        batchDelegate.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean exists(NotifySendRecordKey key) throws DaoException {
        return batchDelegate.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public NotifySendRecord get(NotifySendRecordKey key) throws DaoException {
        return batchDelegate.get(key);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public List<NotifySendRecordKey> batchInsert(@SkipRecord List<NotifySendRecord> elements) throws DaoException {
        return batchDelegate.batchInsert(elements);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchUpdate(@SkipRecord List<NotifySendRecord> elements) throws DaoException {
        batchDelegate.batchUpdate(elements);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchDelete(@SkipRecord List<NotifySendRecordKey> keys) throws DaoException {
        batchDelegate.batchDelete(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean allExists(@SkipRecord List<NotifySendRecordKey> keys) throws DaoException {
        return batchDelegate.allExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean nonExists(@SkipRecord List<NotifySendRecordKey> keys) throws DaoException {
        return batchDelegate.nonExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<NotifySendRecord> batchGet(@SkipRecord List<NotifySendRecordKey> keys) throws DaoException {
        return batchDelegate.batchGet(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<NotifySendRecord> lookup() throws DaoException {
        return entireLookupDelegate.lookup();
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<NotifySendRecord> lookup(PagingInfo pagingInfo) throws DaoException {
        return entireLookupDelegate.lookup(pagingInfo);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public int lookupCount() throws DaoException {
        return entireLookupDelegate.lookupCount();
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<NotifySendRecord> lookup(String preset, Object[] objs) throws DaoException {
        return presetLookupDelegate.lookup(preset, objs);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<NotifySendRecord> lookup(String preset, Object[] objs, PagingInfo pagingInfo) throws DaoException {
        return presetLookupDelegate.lookup(preset, objs, pagingInfo);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public int lookupCount(String preset, Object[] objs) throws DaoException {
        return presetLookupDelegate.lookupCount(preset, objs);
    }
}

package com.dwarfeng.notify.stack.dao;

import com.dwarfeng.notify.stack.bean.entity.NotifyInfoRecord;
import com.dwarfeng.notify.stack.bean.key.NotifyInfoRecordKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 通知信息记录数据访问层。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public interface NotifyInfoRecordDao extends BatchBaseDao<NotifyInfoRecordKey, NotifyInfoRecord>,
        EntireLookupDao<NotifyInfoRecord>, PresetLookupDao<NotifyInfoRecord> {
}

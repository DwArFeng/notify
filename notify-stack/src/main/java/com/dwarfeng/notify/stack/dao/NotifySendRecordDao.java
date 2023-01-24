package com.dwarfeng.notify.stack.dao;

import com.dwarfeng.notify.stack.bean.entity.NotifySendRecord;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 通知发送记录数据访问层。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public interface NotifySendRecordDao extends BatchBaseDao<LongIdKey, NotifySendRecord>,
        EntireLookupDao<NotifySendRecord>, PresetLookupDao<NotifySendRecord> {
}

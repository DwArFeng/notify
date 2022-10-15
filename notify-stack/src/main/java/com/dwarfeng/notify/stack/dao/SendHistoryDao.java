package com.dwarfeng.notify.stack.dao;

import com.dwarfeng.notify.stack.bean.entity.SendHistory;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 发送历史数据访问层。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface SendHistoryDao extends BatchBaseDao<LongIdKey, SendHistory>, EntireLookupDao<SendHistory>,
        PresetLookupDao<SendHistory> {
}

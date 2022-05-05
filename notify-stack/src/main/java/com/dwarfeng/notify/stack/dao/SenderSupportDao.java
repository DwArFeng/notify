package com.dwarfeng.notify.stack.dao;

import com.dwarfeng.notify.stack.bean.entity.SenderSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 发送器支持数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface SenderSupportDao extends BatchBaseDao<StringIdKey, SenderSupport>, EntireLookupDao<SenderSupport>,
        PresetLookupDao<SenderSupport> {
}

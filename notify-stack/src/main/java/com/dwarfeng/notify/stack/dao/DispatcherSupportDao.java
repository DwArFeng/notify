package com.dwarfeng.notify.stack.dao;

import com.dwarfeng.notify.stack.bean.entity.DispatcherSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 调度器支持数据访问层。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface DispatcherSupportDao extends BatchBaseDao<StringIdKey, DispatcherSupport>,
        EntireLookupDao<DispatcherSupport>, PresetLookupDao<DispatcherSupport> {
}

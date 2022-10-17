package com.dwarfeng.notify.stack.dao;

import com.dwarfeng.notify.stack.bean.entity.DispatcherInfo;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 调度器信息数据访问层。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface DispatcherInfoDao extends BatchBaseDao<StringIdKey, DispatcherInfo>, EntireLookupDao<DispatcherInfo>,
        PresetLookupDao<DispatcherInfo> {
}

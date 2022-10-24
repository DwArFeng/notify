package com.dwarfeng.notify.stack.dao;

import com.dwarfeng.notify.stack.bean.entity.MetaIndicator;
import com.dwarfeng.notify.stack.bean.entity.key.MetaIndicatorKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 元数据指示器数据访问层。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface MetaIndicatorDao extends BatchBaseDao<MetaIndicatorKey, MetaIndicator>,
        EntireLookupDao<MetaIndicator>, PresetLookupDao<MetaIndicator> {
}

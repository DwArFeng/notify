package com.dwarfeng.notify.stack.dao;

import com.dwarfeng.notify.stack.bean.entity.VariableIndicator;
import com.dwarfeng.notify.stack.bean.entity.key.VariableIndicatorKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 变量指示器数据访问层。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface VariableIndicatorDao extends BatchBaseDao<VariableIndicatorKey, VariableIndicator>,
        EntireLookupDao<VariableIndicator>, PresetLookupDao<VariableIndicator> {
}

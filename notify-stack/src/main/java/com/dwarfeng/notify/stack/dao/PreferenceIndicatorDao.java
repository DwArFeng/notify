package com.dwarfeng.notify.stack.dao;

import com.dwarfeng.notify.stack.bean.entity.PreferenceIndicator;
import com.dwarfeng.notify.stack.bean.entity.key.PreferenceIndicatorKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 偏好指示器数据访问层。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface PreferenceIndicatorDao extends BatchBaseDao<PreferenceIndicatorKey, PreferenceIndicator>,
        EntireLookupDao<PreferenceIndicator>, PresetLookupDao<PreferenceIndicator> {
}

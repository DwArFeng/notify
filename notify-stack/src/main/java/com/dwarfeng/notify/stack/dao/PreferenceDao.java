package com.dwarfeng.notify.stack.dao;

import com.dwarfeng.notify.stack.bean.entity.Preference;
import com.dwarfeng.notify.stack.bean.entity.key.PreferenceKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 偏好数据访问层。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface PreferenceDao extends BatchBaseDao<PreferenceKey, Preference>, EntireLookupDao<Preference>,
        PresetLookupDao<Preference> {
}

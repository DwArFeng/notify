package com.dwarfeng.notify.stack.cache;

import com.dwarfeng.notify.stack.bean.entity.Preference;
import com.dwarfeng.notify.stack.bean.entity.key.PreferenceKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 偏好缓存。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface PreferenceCache extends BatchBaseCache<PreferenceKey, Preference> {
}

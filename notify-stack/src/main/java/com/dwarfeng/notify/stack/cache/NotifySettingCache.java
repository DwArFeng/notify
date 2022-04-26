package com.dwarfeng.notify.stack.cache;

import com.dwarfeng.notify.stack.bean.entity.NotifySetting;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 通知设置缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface NotifySettingCache extends BatchBaseCache<LongIdKey, NotifySetting> {
}

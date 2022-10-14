package com.dwarfeng.notify.stack.cache;

import com.dwarfeng.notify.stack.bean.entity.DispatcherInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 调度器信息缓存。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface DispatcherInfoCache extends BatchBaseCache<LongIdKey, DispatcherInfo> {
}

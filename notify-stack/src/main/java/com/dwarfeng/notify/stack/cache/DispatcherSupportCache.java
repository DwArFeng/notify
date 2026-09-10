package com.dwarfeng.notify.stack.cache;

import com.dwarfeng.notify.stack.bean.entity.DispatcherSupport;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.data.stack.cache.BatchBaseCache;

/**
 * 调度器支持缓存。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface DispatcherSupportCache extends BatchBaseCache<StringIdKey, DispatcherSupport> {
}

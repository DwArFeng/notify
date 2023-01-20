package com.dwarfeng.notify.stack.cache;

import com.dwarfeng.notify.stack.bean.entity.MetaIndicator;
import com.dwarfeng.notify.stack.bean.key.MetaIndicatorKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 元数据缓存。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface MetaIndicatorCache extends BatchBaseCache<MetaIndicatorKey, MetaIndicator> {
}

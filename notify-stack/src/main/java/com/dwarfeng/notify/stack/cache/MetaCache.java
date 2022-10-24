package com.dwarfeng.notify.stack.cache;

import com.dwarfeng.notify.stack.bean.entity.Meta;
import com.dwarfeng.notify.stack.bean.entity.key.MetaKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 元数据缓存。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface MetaCache extends BatchBaseCache<MetaKey, Meta> {
}

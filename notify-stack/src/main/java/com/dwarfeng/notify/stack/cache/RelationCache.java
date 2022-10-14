package com.dwarfeng.notify.stack.cache;

import com.dwarfeng.notify.stack.bean.entity.Relation;
import com.dwarfeng.notify.stack.bean.entity.key.RelationKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 关系缓存。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface RelationCache extends BatchBaseCache<RelationKey, Relation> {
}

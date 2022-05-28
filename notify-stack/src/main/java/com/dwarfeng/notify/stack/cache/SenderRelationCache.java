package com.dwarfeng.notify.stack.cache;

import com.dwarfeng.notify.stack.bean.entity.SenderRelation;
import com.dwarfeng.notify.stack.bean.entity.key.SenderRelationKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 发送器关系缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface SenderRelationCache extends BatchBaseCache<SenderRelationKey, SenderRelation> {
}

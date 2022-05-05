package com.dwarfeng.notify.stack.cache;

import com.dwarfeng.notify.stack.bean.entity.Topic;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 主题缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface TopicCache extends BatchBaseCache<StringIdKey, Topic> {
}

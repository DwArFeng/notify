package com.dwarfeng.notify.stack.cache;

import com.dwarfeng.notify.stack.bean.entity.SendHistory;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 发送历史缓存。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface SendHistoryCache extends BatchBaseCache<LongIdKey, SendHistory> {
}

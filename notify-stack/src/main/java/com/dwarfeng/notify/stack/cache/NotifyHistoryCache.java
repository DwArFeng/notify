package com.dwarfeng.notify.stack.cache;

import com.dwarfeng.notify.stack.bean.entity.NotifyHistory;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 通知历史缓存。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public interface NotifyHistoryCache extends BatchBaseCache<LongIdKey, NotifyHistory> {
}

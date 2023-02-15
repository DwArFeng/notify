package com.dwarfeng.notify.stack.cache;

import com.dwarfeng.notify.stack.bean.entity.NotifyInfoRecord;
import com.dwarfeng.notify.stack.bean.key.NotifyInfoRecordKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 通知信息记录缓存。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public interface NotifyInfoRecordCache extends BatchBaseCache<NotifyInfoRecordKey, NotifyInfoRecord> {
}

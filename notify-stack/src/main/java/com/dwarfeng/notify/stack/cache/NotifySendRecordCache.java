package com.dwarfeng.notify.stack.cache;

import com.dwarfeng.notify.stack.bean.entity.NotifySendRecord;
import com.dwarfeng.notify.stack.bean.key.NotifySendRecordKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 通知发送记录缓存。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public interface NotifySendRecordCache extends BatchBaseCache<NotifySendRecordKey, NotifySendRecord> {
}

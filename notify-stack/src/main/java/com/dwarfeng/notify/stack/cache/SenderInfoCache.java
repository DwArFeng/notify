package com.dwarfeng.notify.stack.cache;

import com.dwarfeng.notify.stack.bean.entity.SenderInfo;
import com.dwarfeng.notify.stack.bean.key.SenderInfoKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 发送器信息缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface SenderInfoCache extends BatchBaseCache<SenderInfoKey, SenderInfo> {
}

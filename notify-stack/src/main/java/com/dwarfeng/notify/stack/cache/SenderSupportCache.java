package com.dwarfeng.notify.stack.cache;

import com.dwarfeng.notify.stack.bean.entity.SenderSupport;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.data.stack.cache.BatchBaseCache;

/**
 * 发送器支持缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface SenderSupportCache extends BatchBaseCache<StringIdKey, SenderSupport> {
}

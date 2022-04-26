package com.dwarfeng.notify.stack.cache;

import com.dwarfeng.notify.stack.bean.entity.RouterSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 路由器支持缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface RouterSupportCache extends BatchBaseCache<StringIdKey, RouterSupport> {
}

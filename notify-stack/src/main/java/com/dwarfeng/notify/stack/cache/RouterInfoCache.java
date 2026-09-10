package com.dwarfeng.notify.stack.cache;

import com.dwarfeng.notify.stack.bean.entity.RouterInfo;
import com.dwarfeng.subgrade.basic.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.data.stack.cache.BatchBaseCache;

/**
 * 路由器信息缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface RouterInfoCache extends BatchBaseCache<LongIdKey, RouterInfo> {
}

package com.dwarfeng.notify.stack.cache;

import com.dwarfeng.notify.stack.bean.entity.RouterInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.KeyListCache;

/**
 * 部件持有评价子项的缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface EnabledRouterInfoCache extends KeyListCache<LongIdKey, RouterInfo> {
}

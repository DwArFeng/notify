package com.dwarfeng.notify.stack.cache;

import com.dwarfeng.notify.stack.bean.entity.User;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 用户缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface UserCache extends BatchBaseCache<StringIdKey, User> {
}

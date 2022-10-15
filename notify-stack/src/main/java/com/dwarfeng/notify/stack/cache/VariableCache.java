package com.dwarfeng.notify.stack.cache;

import com.dwarfeng.notify.stack.bean.entity.Variable;
import com.dwarfeng.notify.stack.bean.entity.key.VariableKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 变量缓存。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface VariableCache extends BatchBaseCache<VariableKey, Variable> {
}

package com.dwarfeng.notify.stack.service;

import com.dwarfeng.notify.stack.bean.entity.User;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;

/**
 * 用户维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface UserMaintainService extends BatchCrudService<StringIdKey, User>, EntireLookupService<User> {
}

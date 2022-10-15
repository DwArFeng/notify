package com.dwarfeng.notify.stack.service;

import com.dwarfeng.notify.stack.bean.entity.User;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 用户维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface UserMaintainService extends BatchCrudService<StringIdKey, User>, EntireLookupService<User>,
        PresetLookupService<User> {

    String ID_LIKE = "id_like";
    String ENABLED = "enabled";
}

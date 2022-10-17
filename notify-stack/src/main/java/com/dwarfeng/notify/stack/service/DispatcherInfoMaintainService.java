package com.dwarfeng.notify.stack.service;

import com.dwarfeng.notify.stack.bean.entity.DispatcherInfo;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 调度器信息维护服务。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface DispatcherInfoMaintainService extends BatchCrudService<StringIdKey, DispatcherInfo>,
        EntireLookupService<DispatcherInfo>, PresetLookupService<DispatcherInfo> {

    String TYPE_EQUALS = "type_equals";
    String TYPE_LIKE = "type_like";
}

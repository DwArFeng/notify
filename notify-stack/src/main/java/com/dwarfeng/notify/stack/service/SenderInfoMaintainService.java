package com.dwarfeng.notify.stack.service;

import com.dwarfeng.notify.stack.bean.entity.SenderInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 发送器信息维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface SenderInfoMaintainService extends BatchCrudService<LongIdKey, SenderInfo>, EntireLookupService<SenderInfo>,
        PresetLookupService<SenderInfo> {

    String TYPE_EQUALS = "type_equals";
    String TYPE_LIKE = "type_like";
}

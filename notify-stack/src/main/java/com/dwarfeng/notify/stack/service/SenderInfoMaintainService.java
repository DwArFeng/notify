package com.dwarfeng.notify.stack.service;

import com.dwarfeng.notify.stack.bean.entity.SenderInfo;
import com.dwarfeng.notify.stack.bean.entity.key.SenderInfoKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 发送器信息维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface SenderInfoMaintainService extends BatchCrudService<SenderInfoKey, SenderInfo>,
        EntireLookupService<SenderInfo>, PresetLookupService<SenderInfo> {

    String CHILD_FOR_NOTIFY_SETTING = "child_for_notify_setting";
    String CHILD_FOR_TOPIC = "child_for_topic";
    String TYPE_EQUALS = "type_equals";
    String TYPE_LIKE = "type_like";
}

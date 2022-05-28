package com.dwarfeng.notify.stack.service;

import com.dwarfeng.notify.stack.bean.entity.SenderRelation;
import com.dwarfeng.notify.stack.bean.entity.key.SenderRelationKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 发送器关系维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface SenderRelationMaintainService extends BatchCrudService<SenderRelationKey, SenderRelation>, EntireLookupService<SenderRelation>,
        PresetLookupService<SenderRelation> {

    String CHILD_FOR_NOTIFY_SETTING = "child_for_notify_setting";
    String CHILD_FOR_TOPIC = "child_for_topic";
    String CHILD_FOR_SENDER_INFO = "child_for_sender_info";
}

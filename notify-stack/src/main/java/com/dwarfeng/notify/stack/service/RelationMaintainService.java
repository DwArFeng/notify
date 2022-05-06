package com.dwarfeng.notify.stack.service;

import com.dwarfeng.notify.stack.bean.entity.Relation;
import com.dwarfeng.notify.stack.bean.entity.key.RelationKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 用户维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface RelationMaintainService extends BatchCrudService<RelationKey, Relation>, EntireLookupService<Relation>,
        PresetLookupService<Relation> {

    String CHILD_FOR_NOTIFY_SETTING = "child_for_notify_setting";
    String CHILD_FOR_TOPIC = "child_for_topic";
    String CHILD_FOR_SENDER_INFO = "child_for_sender_info";
}

package com.dwarfeng.notify.stack.service;

import com.dwarfeng.notify.stack.bean.entity.SendHistory;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 偏好维护服务。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface SendHistoryMaintainService extends BatchCrudService<LongIdKey, SendHistory>,
        EntireLookupService<SendHistory>, PresetLookupService<SendHistory> {

    String CHILD_FOR_NOTIFY_SETTING = "child_for_notify_setting";
    String CHILD_FOR_TOPIC = "child_for_topic";
    String CHILD_FOR_USER = "child_for_user";
}

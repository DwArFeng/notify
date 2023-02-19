package com.dwarfeng.notify.stack.service;

import com.dwarfeng.notify.stack.bean.entity.NotifySendRecord;
import com.dwarfeng.notify.stack.bean.key.NotifySendRecordKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 通知发送记录维护服务。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public interface NotifySendRecordMaintainService extends BatchCrudService<NotifySendRecordKey, NotifySendRecord>,
        EntireLookupService<NotifySendRecord>, PresetLookupService<NotifySendRecord> {

    String CHILD_FOR_NOTIFY_HISTORY = "child_for_notify_history";
    String CHILD_FOR_TOPIC = "child_for_notify_topic";
    String CHILD_FOR_USER = "child_for_notify_user";
    String CHILD_FOR_NOTIFY_HISTORY_ORDERED = "child_for_notify_history_ordered";
}

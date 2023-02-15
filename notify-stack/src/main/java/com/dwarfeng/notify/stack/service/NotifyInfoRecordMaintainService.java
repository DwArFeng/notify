package com.dwarfeng.notify.stack.service;

import com.dwarfeng.notify.stack.bean.entity.NotifyInfoRecord;
import com.dwarfeng.notify.stack.bean.key.NotifyInfoRecordKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 通知信息记录维护服务。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public interface NotifyInfoRecordMaintainService extends BatchCrudService<NotifyInfoRecordKey, NotifyInfoRecord>,
        EntireLookupService<NotifyInfoRecord>, PresetLookupService<NotifyInfoRecord> {

    String CHILD_FOR_NOTIFY_HISTORY = "child_for_notify_history";
    String CHILD_FOR_NOTIFY_HISTORY_RECORD_ID_ASC = "child_for_notify_history_record_id_asc";
}

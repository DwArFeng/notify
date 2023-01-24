package com.dwarfeng.notify.stack.service;

import com.dwarfeng.notify.stack.bean.entity.NotifyHistory;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 通知历史维护服务。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public interface NotifyHistoryMaintainService extends BatchCrudService<LongIdKey, NotifyHistory>,
        EntireLookupService<NotifyHistory>, PresetLookupService<NotifyHistory> {

    String CHILD_FOR_NOTIFY_SETTING = "child_for_notify_setting";
    String HAPPENED_DATE_DESC = "happened_date_desc";
    String CHILD_FOR_NOTIFY_SETTING_HAPPENED_DATE_DESC = "child_for_notify_setting_happened_date_desc";
}

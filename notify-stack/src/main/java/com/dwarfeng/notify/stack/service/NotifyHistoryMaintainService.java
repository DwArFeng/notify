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

    /**
     * 获取将要被清理的通知历史实体。
     *
     * <p>
     * 返回 <code>happenedDate（发生日期）</code> 早于指定的日期的实体，
     * 且按照 <code>happenedDate（发生日期）</code> 升序排列。
     *
     * <p>
     * 参数列表：
     * <ol>
     *     <li>Data 指定的日期。</li>
     * </ol>
     * 返回的数据按照 <code>happenedDate（发生日期）</code> 升序排列。
     *
     * @since 1.6.0
     */
    String TO_PURGED = "to_purged";
}

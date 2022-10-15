package com.dwarfeng.notify.stack.service;

import com.dwarfeng.notify.stack.bean.entity.Preference;
import com.dwarfeng.notify.stack.bean.entity.key.PreferenceKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 偏好维护服务。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface PreferenceMaintainService extends BatchCrudService<PreferenceKey, Preference>,
        EntireLookupService<Preference>, PresetLookupService<Preference> {

    String CHILD_FOR_NOTIFY_SETTING = "child_for_notify_setting";
    String CHILD_FOR_TOPIC = "child_for_topic";
    String CHILD_FOR_USER = "child_for_user";
}

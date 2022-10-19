package com.dwarfeng.notify.stack.service;

import com.dwarfeng.notify.stack.bean.entity.PreferenceIndicator;
import com.dwarfeng.notify.stack.bean.entity.key.PreferenceIndicatorKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 偏好维护服务。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface PreferenceIndicatorMaintainService extends
        BatchCrudService<PreferenceIndicatorKey, PreferenceIndicator>, EntireLookupService<PreferenceIndicator>,
        PresetLookupService<PreferenceIndicator> {

    String CHILD_FOR_TOPIC = "child_for_topic";
    String CHILD_FOR_TOPIC_PREFERENCE_ID_ASC = "child_for_topic_preference_id_asc";
}

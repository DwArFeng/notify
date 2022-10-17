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

    String ID_LIKE = "id_like";
    String LABEL_LIKE = "label_like";
    String CHILD_FOR_TOPIC = "child_for_topic";
}

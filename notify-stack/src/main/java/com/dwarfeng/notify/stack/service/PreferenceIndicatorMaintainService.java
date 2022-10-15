package com.dwarfeng.notify.stack.service;

import com.dwarfeng.notify.stack.bean.entity.PreferenceIndicator;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 偏好维护服务。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface PreferenceIndicatorMaintainService extends BatchCrudService<StringIdKey, PreferenceIndicator>,
        EntireLookupService<PreferenceIndicator>, PresetLookupService<PreferenceIndicator> {

    String LABEL_LIKE = "label_like";
}

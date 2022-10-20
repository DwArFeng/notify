package com.dwarfeng.notify.stack.service;

import com.dwarfeng.notify.stack.bean.entity.VariableIndicator;
import com.dwarfeng.notify.stack.bean.entity.key.VariableIndicatorKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 变量维护服务。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface VariableIndicatorMaintainService extends
        BatchCrudService<VariableIndicatorKey, VariableIndicator>, EntireLookupService<VariableIndicator>,
        PresetLookupService<VariableIndicator> {

    String CHILD_FOR_TOPIC = "child_for_topic";
    String CHILD_FOR_TOPIC_VARIABLE_ID_ASC = "child_for_topic_variable_id_asc";
}

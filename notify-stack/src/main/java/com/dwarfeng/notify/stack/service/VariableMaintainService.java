package com.dwarfeng.notify.stack.service;

import com.dwarfeng.notify.stack.bean.entity.Variable;
import com.dwarfeng.notify.stack.bean.entity.key.VariableKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 元数据维护服务。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface VariableMaintainService extends BatchCrudService<VariableKey, Variable>,
        EntireLookupService<Variable>, PresetLookupService<Variable> {

    String CHILD_FOR_NOTIFY_SETTING = "child_for_notify_setting";
    String CHILD_FOR_TOPIC = "child_for_topic";
    String CHILD_FOR_USER = "child_for_user";
    String CHILD_FOR_NOTIFY_SETTING_TOPIC_USER_VARIABLE_ID_ASC = "child_for_notify_setting_topic_user_variable_id_asc";
}

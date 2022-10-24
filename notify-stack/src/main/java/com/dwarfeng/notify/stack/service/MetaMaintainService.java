package com.dwarfeng.notify.stack.service;

import com.dwarfeng.notify.stack.bean.entity.Meta;
import com.dwarfeng.notify.stack.bean.entity.key.MetaKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 元数据维护服务。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface MetaMaintainService extends BatchCrudService<MetaKey, Meta>,
        EntireLookupService<Meta>, PresetLookupService<Meta> {

    String CHILD_FOR_NOTIFY_SETTING = "child_for_notify_setting";
    String CHILD_FOR_TOPIC = "child_for_topic";
    String CHILD_FOR_USER = "child_for_user";
    String CHILD_FOR_NOTIFY_SETTING_TOPIC_USER_META_ID_ASC
            = "child_for_notify_setting_topic_user_meta_id_asc";
}

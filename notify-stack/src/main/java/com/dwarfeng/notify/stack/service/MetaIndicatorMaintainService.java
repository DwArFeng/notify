package com.dwarfeng.notify.stack.service;

import com.dwarfeng.notify.stack.bean.entity.MetaIndicator;
import com.dwarfeng.notify.stack.bean.key.MetaIndicatorKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 元数据维护服务。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface MetaIndicatorMaintainService extends
        BatchCrudService<MetaIndicatorKey, MetaIndicator>, EntireLookupService<MetaIndicator>,
        PresetLookupService<MetaIndicator> {

    String CHILD_FOR_TOPIC = "child_for_topic";
    String CHILD_FOR_TOPIC_META_ID_ASC = "child_for_topic_meta_id_asc";
}

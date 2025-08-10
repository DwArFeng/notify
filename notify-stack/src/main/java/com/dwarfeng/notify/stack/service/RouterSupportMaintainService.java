package com.dwarfeng.notify.stack.service;

import com.dwarfeng.notify.stack.bean.entity.RouterSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 路由器支持维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface RouterSupportMaintainService extends BatchCrudService<StringIdKey, RouterSupport>,
        EntireLookupService<RouterSupport>, PresetLookupService<RouterSupport> {

    String ID_LIKE = "id_like";
    String LABEL_LIKE = "label_like";
}

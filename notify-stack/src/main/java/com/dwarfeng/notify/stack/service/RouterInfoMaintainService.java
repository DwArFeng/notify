package com.dwarfeng.notify.stack.service;

import com.dwarfeng.notify.stack.bean.entity.RouterInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 路由器信息维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface RouterInfoMaintainService extends BatchCrudService<LongIdKey, RouterInfo>, EntireLookupService<RouterInfo>,
        PresetLookupService<RouterInfo> {

    String CHILD_FOR_NOTIFY_SETTING = "child_for_notify_setting";
    String TYPE_EQUALS = "type_equals";
    String TYPE_LIKE = "type_like";
}

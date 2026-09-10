package com.dwarfeng.notify.stack.service;

import com.dwarfeng.notify.stack.bean.entity.NotifySetting;
import com.dwarfeng.subgrade.basic.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.data.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.data.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.data.stack.service.PresetLookupService;

/**
 * 路由器信息维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface NotifySettingMaintainService extends BatchCrudService<LongIdKey, NotifySetting>,
        EntireLookupService<NotifySetting>, PresetLookupService<NotifySetting> {

    String LABEL_LIKE = "label_like";
}

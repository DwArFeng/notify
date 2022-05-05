package com.dwarfeng.notify.stack.service;

import com.dwarfeng.notify.stack.bean.entity.SenderSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 发送器支持维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface SenderSupportMaintainService extends BatchCrudService<StringIdKey, SenderSupport>,
        EntireLookupService<SenderSupport>, PresetLookupService<SenderSupport> {

    String ID_LIKE = "id_like";
    String LABEL_LIKE = "label_like";

    /**
     * 重置发送器支持。
     *
     * @throws ServiceException 服务异常。
     */
    void reset() throws ServiceException;
}

package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.stack.exception.NotifySettingNotExistsException;
import com.dwarfeng.notify.stack.service.NotifySettingMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 处理器验证器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
@Component
public class HandlerValidator {

    private final NotifySettingMaintainService notifySettingMaintainService;

    public HandlerValidator(NotifySettingMaintainService notifySettingMaintainService) {
        this.notifySettingMaintainService = notifySettingMaintainService;
    }

    public void makeSureNotifySettingExists(LongIdKey notifySettingKey) throws HandlerException {
        try {
            if (Objects.isNull(notifySettingKey) || !notifySettingMaintainService.exists(notifySettingKey)) {
                throw new NotifySettingNotExistsException(notifySettingKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }
}

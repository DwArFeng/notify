package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.notify.impl.internal.i18n.ImplMessages;

import com.dwarfeng.notify.stack.bean.dto.NotifyInfo;
import com.dwarfeng.notify.stack.handler.NotifyHandler;
import com.dwarfeng.notify.stack.service.NotifyService;
import com.dwarfeng.subgrade.basic.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.basic.stack.exception.ServiceException;
import com.dwarfeng.subgrade.basic.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.basic.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class NotifyServiceImpl implements NotifyService {

    private final NotifyHandler notifyHandler;

    private final ServiceExceptionMapper sem;

    public NotifyServiceImpl(NotifyHandler notifyHandler, ServiceExceptionMapper sem) {
        this.notifyHandler = notifyHandler;
        this.sem = sem;
    }

    @Override
    public void notify(NotifyInfo notifyInfo) throws ServiceException {
        try {
            notifyHandler.notify(notifyInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    ImplMessages.message(ImplMessageKey.ERROR_NOTIFY_QOS_SERVICE_CONFIRM_MODE_GET_FAILED),
                    LogLevel.WARN, e, sem
            );
        }
    }
}

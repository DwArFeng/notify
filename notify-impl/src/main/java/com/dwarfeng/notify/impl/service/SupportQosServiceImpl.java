package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.notify.impl.internal.i18n.ImplMessages;

import com.dwarfeng.notify.stack.handler.SupportHandler;
import com.dwarfeng.notify.stack.service.SupportQosService;
import com.dwarfeng.subgrade.basic.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.basic.stack.exception.HandlerException;
import com.dwarfeng.subgrade.basic.stack.exception.ServiceException;
import com.dwarfeng.subgrade.basic.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.basic.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class SupportQosServiceImpl implements SupportQosService {

    private final SupportHandler supportHandler;

    private final ServiceExceptionMapper sem;

    public SupportQosServiceImpl(SupportHandler supportHandler, ServiceExceptionMapper sem) {
        this.supportHandler = supportHandler;
        this.sem = sem;
    }

    @Override
    public void resetRouter() throws ServiceException {
        try {
            supportHandler.resetRouter();
        } catch (HandlerException e) {
            throw ServiceExceptionHelper.logParse(
                    ImplMessages.message(ImplMessageKey.ERROR_SUPPORT_QOS_SERVICE_ROUTER_RESET_FAILED),
                    LogLevel.WARN, e, sem
            );
        }
    }

    @Override
    public void resetSender() throws ServiceException {
        try {
            supportHandler.resetSender();
        } catch (HandlerException e) {
            throw ServiceExceptionHelper.logParse(
                    ImplMessages.message(ImplMessageKey.ERROR_SUPPORT_QOS_SERVICE_SENDER_RESET_FAILED),
                    LogLevel.WARN, e, sem
            );
        }
    }

    @Override
    public void resetDispatcher() throws ServiceException {
        try {
            supportHandler.resetDispatcher();
        } catch (HandlerException e) {
            throw ServiceExceptionHelper.logParse(
                    ImplMessages.message(ImplMessageKey.ERROR_SUPPORT_QOS_SERVICE_DISPATCHER_RESET_FAILED),
                    LogLevel.WARN, e, sem
            );
        }
    }
}

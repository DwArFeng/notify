package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.handler.SupportHandler;
import com.dwarfeng.notify.stack.service.SupportQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
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
            throw ServiceExceptionHelper.logParse("重置路由器时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void resetSender() throws ServiceException {
        try {
            supportHandler.resetSender();
        } catch (HandlerException e) {
            throw ServiceExceptionHelper.logParse("重置发送器时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void resetDispatcher() throws ServiceException {
        try {
            supportHandler.resetDispatcher();
        } catch (HandlerException e) {
            throw ServiceExceptionHelper.logParse("重置调度器时发生异常", LogLevel.WARN, e, sem);
        }
    }
}

package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.notify.impl.internal.i18n.ImplMessages;

import com.dwarfeng.notify.stack.handler.PurgeHandler;
import com.dwarfeng.notify.stack.service.PurgeQosService;
import com.dwarfeng.subgrade.basic.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.basic.stack.exception.HandlerException;
import com.dwarfeng.subgrade.basic.stack.exception.ServiceException;
import com.dwarfeng.subgrade.basic.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.basic.stack.log.LogLevel;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class PurgeQosServiceImpl implements PurgeQosService {

    private final PurgeHandler purgeHandler;

    private final ServiceExceptionMapper sem;

    public PurgeQosServiceImpl(PurgeHandler purgeHandler, ServiceExceptionMapper sem) {
        this.purgeHandler = purgeHandler;
        this.sem = sem;
    }

    @PreDestroy
    public void dispose() throws HandlerException {
        purgeHandler.stop();
        purgeHandler.offline();
    }

    @Override
    public boolean isOnline() throws ServiceException {
        try {
            return purgeHandler.isOnline();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    ImplMessages.message(ImplMessageKey.ERROR_PURGE_QOS_SERVICE_ONLINE_QUERY_FAILED),
                    LogLevel.WARN, e, sem
            );
        }
    }

    @Override
    public void online() throws ServiceException {
        try {
            purgeHandler.online();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    ImplMessages.message(ImplMessageKey.ERROR_PURGE_QOS_SERVICE_ONLINE_FAILED),
                    LogLevel.WARN, e, sem
            );
        }
    }

    @Override
    public void offline() throws ServiceException {
        try {
            purgeHandler.offline();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    ImplMessages.message(ImplMessageKey.ERROR_PURGE_QOS_SERVICE_OFFLINE_FAILED),
                    LogLevel.WARN, e, sem
            );
        }
    }

    @Override
    public boolean isLockHolding() throws ServiceException {
        try {
            return purgeHandler.isLockHolding();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    ImplMessages.message(ImplMessageKey.ERROR_PURGE_QOS_SERVICE_LOCKED_QUERY_FAILED),
                    LogLevel.WARN, e, sem
            );
        }
    }

    @Override
    public boolean isStarted() throws ServiceException {
        try {
            return purgeHandler.isStarted();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    ImplMessages.message(ImplMessageKey.ERROR_PURGE_QOS_SERVICE_STARTED_QUERY_FAILED),
                    LogLevel.WARN, e, sem
            );
        }
    }

    @Override
    public void start() throws ServiceException {
        try {
            purgeHandler.start();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    ImplMessages.message(ImplMessageKey.ERROR_PURGE_QOS_SERVICE_START_FAILED),
                    LogLevel.WARN, e, sem
            );
        }
    }

    @Override
    public void stop() throws ServiceException {
        try {
            purgeHandler.stop();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    ImplMessages.message(ImplMessageKey.ERROR_PURGE_QOS_SERVICE_STOP_FAILED),
                    LogLevel.WARN, e, sem
            );
        }
    }

    @Override
    public boolean isWorking() throws ServiceException {
        try {
            return purgeHandler.isWorking();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    ImplMessages.message(ImplMessageKey.ERROR_PURGE_QOS_SERVICE_WORKING_QUERY_FAILED),
                    LogLevel.WARN, e, sem
            );
        }
    }
}

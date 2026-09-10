package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.notify.impl.internal.i18n.ImplMessages;

import com.dwarfeng.notify.stack.handler.ResetHandler;
import com.dwarfeng.notify.stack.handler.Resetter;
import com.dwarfeng.notify.stack.handler.ResetterHandler;
import com.dwarfeng.notify.stack.service.ResetQosService;
import com.dwarfeng.subgrade.basic.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.basic.stack.exception.ServiceException;
import com.dwarfeng.subgrade.basic.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.basic.stack.log.LogLevel;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResetQosServiceImpl implements ResetQosService {

    private final ResetterHandler resetterHandler;
    private final ResetHandler resetHandler;

    private final ServiceExceptionMapper sem;

    public ResetQosServiceImpl(
            ResetterHandler resetterHandler,
            ResetHandler resetHandler,
            ServiceExceptionMapper sem
    ) {
        this.resetterHandler = resetterHandler;
        this.resetHandler = resetHandler;
        this.sem = sem;
    }

    @PreDestroy
    public void dispose() throws Exception {
        resetHandler.stop();
    }

    @Override
    public List<Resetter> all() throws ServiceException {
        try {
            return resetterHandler.all();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    ImplMessages.message(ImplMessageKey.ERROR_RESET_QOS_SERVICE_RESETTER_LIST_FAILED),
                    LogLevel.WARN, e, sem
            );
        }
    }

    @Override
    public boolean isStarted() throws ServiceException {
        try {
            return resetHandler.isStarted();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    ImplMessages.message(ImplMessageKey.ERROR_RESET_QOS_SERVICE_STARTED_QUERY_FAILED),
                    LogLevel.WARN, e, sem
            );
        }
    }

    @Override
    public void start() throws ServiceException {
        try {
            resetHandler.start();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    ImplMessages.message(ImplMessageKey.ERROR_RESET_QOS_SERVICE_START_FAILED),
                    LogLevel.WARN, e, sem
            );
        }
    }

    @Override
    public void stop() throws ServiceException {
        try {
            resetHandler.stop();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    ImplMessages.message(ImplMessageKey.ERROR_RESET_QOS_SERVICE_STOP_FAILED),
                    LogLevel.WARN, e, sem
            );
        }
    }

    @Override
    public void resetRoute() throws ServiceException {
        try {
            resetHandler.resetRoute();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    ImplMessages.message(ImplMessageKey.ERROR_RESET_QOS_SERVICE_ROUTE_FAILED),
                    LogLevel.WARN, e, sem
            );
        }
    }

    @Override
    public void resetDispatch() throws ServiceException {
        try {
            resetHandler.resetDispatch();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    ImplMessages.message(ImplMessageKey.ERROR_RESET_QOS_SERVICE_DISPATCH_FAILED),
                    LogLevel.WARN, e, sem
            );
        }
    }

    @Override
    public void resetSend() throws ServiceException {
        try {
            resetHandler.resetSend();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    ImplMessages.message(ImplMessageKey.ERROR_RESET_QOS_SERVICE_SEND_FAILED),
                    LogLevel.WARN, e, sem
            );
        }
    }
}

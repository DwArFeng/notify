package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.notify.impl.internal.i18n.ImplMessages;

import com.dwarfeng.notify.stack.bean.dto.NotifyInfo;
import com.dwarfeng.notify.stack.bean.key.SenderInfoKey;
import com.dwarfeng.notify.stack.handler.Dispatcher;
import com.dwarfeng.notify.stack.handler.NotifyHandler;
import com.dwarfeng.notify.stack.handler.Router;
import com.dwarfeng.notify.stack.handler.Sender;
import com.dwarfeng.notify.stack.service.NotifyQosService;
import com.dwarfeng.subgrade.basic.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.basic.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.basic.stack.exception.ServiceException;
import com.dwarfeng.subgrade.basic.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.basic.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class NotifyQosServiceImpl implements NotifyQosService {

    private final NotifyHandler notifyHandler;

    private final ServiceExceptionMapper sem;

    public NotifyQosServiceImpl(NotifyHandler notifyHandler, ServiceExceptionMapper sem) {
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

    @Override
    public Router getRouter(LongIdKey routerInfoKey) throws ServiceException {
        try {
            return notifyHandler.getRouter(routerInfoKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    ImplMessages.message(ImplMessageKey.ERROR_NOTIFY_QOS_SERVICE_ROUTER_GET_FAILED),
                    LogLevel.WARN, e, sem
            );
        }
    }

    @Override
    public void clearRouterLocalCache() throws ServiceException {
        try {
            notifyHandler.clearRouterLocalCache();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    ImplMessages.message(ImplMessageKey.ERROR_NOTIFY_QOS_SERVICE_ROUTER_LOCAL_CACHE_CLEAR_FAILED),
                    LogLevel.WARN, e, sem
            );
        }
    }

    @Override
    public Dispatcher getDispatcher(StringIdKey dispatcherInfoKey) throws ServiceException {
        try {
            return notifyHandler.getDispatcher(dispatcherInfoKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    ImplMessages.message(ImplMessageKey.ERROR_NOTIFY_QOS_SERVICE_DISPATCHER_GET_FAILED),
                    LogLevel.WARN, e, sem
            );
        }
    }

    @Override
    public void clearDispatcherLocalCache() throws ServiceException {
        try {
            notifyHandler.clearDispatcherLocalCache();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    ImplMessages.message(ImplMessageKey.ERROR_NOTIFY_QOS_SERVICE_DISPATCHER_LOCAL_CACHE_CLEAR_FAILED),
                    LogLevel.WARN, e, sem
            );
        }
    }

    @Override
    public Sender getSender(SenderInfoKey senderInfoKey) throws ServiceException {
        try {
            return notifyHandler.getSender(senderInfoKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    ImplMessages.message(ImplMessageKey.ERROR_NOTIFY_QOS_SERVICE_SENDER_GET_FAILED),
                    LogLevel.WARN, e, sem
            );
        }
    }

    @Override
    public void clearSenderLocalCache() throws ServiceException {
        try {
            notifyHandler.clearSenderLocalCache();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    ImplMessages.message(ImplMessageKey.ERROR_NOTIFY_QOS_SERVICE_SENDER_LOCAL_CACHE_CLEAR_FAILED),
                    LogLevel.WARN, e, sem
            );
        }
    }
}

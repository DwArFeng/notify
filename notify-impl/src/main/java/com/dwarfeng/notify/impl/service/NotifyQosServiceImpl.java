package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.dto.NotifyInfo;
import com.dwarfeng.notify.stack.bean.entity.key.SenderInfoKey;
import com.dwarfeng.notify.stack.handler.Dispatcher;
import com.dwarfeng.notify.stack.handler.NotifyHandler;
import com.dwarfeng.notify.stack.handler.Router;
import com.dwarfeng.notify.stack.handler.Sender;
import com.dwarfeng.notify.stack.service.NotifyQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
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
            throw ServiceExceptionHelper.logAndThrow("获取当前的确认模式时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public Router getRouter(LongIdKey routerInfoKey) throws ServiceException {
        try {
            return notifyHandler.getRouter(routerInfoKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("获取指定主键对应的路由器时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void clearRouterLocalCache() throws ServiceException {
        try {
            notifyHandler.clearRouterLocalCache();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("清理路由器本地缓存时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public Dispatcher getDispatcher(StringIdKey dispatcherInfoKey) throws ServiceException {
        try {
            return notifyHandler.getDispatcher(dispatcherInfoKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("获取指定主键对应的调度器时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void clearDispatcherLocalCache() throws ServiceException {
        try {
            notifyHandler.clearDispatcherLocalCache();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("清理调度器本地缓存时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public Sender getSender(SenderInfoKey senderInfoKey) throws ServiceException {
        try {
            return notifyHandler.getSender(senderInfoKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("获取指定主键对应的发送器时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void clearSenderLocalCache() throws ServiceException {
        try {
            notifyHandler.clearSenderLocalCache();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("清理发送器本地缓存时发生异常", LogLevel.WARN, sem, e);
        }
    }
}

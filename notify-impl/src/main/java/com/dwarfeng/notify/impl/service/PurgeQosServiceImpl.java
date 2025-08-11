package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.handler.PurgeHandler;
import com.dwarfeng.notify.stack.service.PurgeQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

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
            throw ServiceExceptionHelper.logParse("判断清除服务是否上线时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void online() throws ServiceException {
        try {
            purgeHandler.online();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("上线清除服务时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void offline() throws ServiceException {
        try {
            purgeHandler.offline();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下线清除服务时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public boolean isLockHolding() throws ServiceException {
        try {
            return purgeHandler.isLockHolding();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断清除服务是否正在持有锁时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public boolean isStarted() throws ServiceException {
        try {
            return purgeHandler.isStarted();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断清除服务是否启动时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void start() throws ServiceException {
        try {
            purgeHandler.start();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("启动清除服务时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void stop() throws ServiceException {
        try {
            purgeHandler.stop();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("停止清除服务时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public boolean isWorking() throws ServiceException {
        try {
            return purgeHandler.isWorking();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断清除服务是否正在工作时发生异常", LogLevel.WARN, e, sem);
        }
    }
}

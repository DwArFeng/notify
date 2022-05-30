package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.dto.NotifyInfo;
import com.dwarfeng.notify.stack.handler.NotifyHandler;
import com.dwarfeng.notify.stack.service.NotifyService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
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
            throw ServiceExceptionHelper.logAndThrow("获取当前的确认模式时发生异常", LogLevel.WARN, sem, e);
        }
    }
}

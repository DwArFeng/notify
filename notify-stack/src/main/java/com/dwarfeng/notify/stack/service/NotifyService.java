package com.dwarfeng.notify.stack.service;

import com.dwarfeng.notify.stack.bean.dto.NotifyInfo;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 通知服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface NotifyService extends Service {

    /**
     * 发送通知。
     *
     * @param notifyInfo 通知信息。
     * @throws ServiceException 服务异常。
     */
    void notify(NotifyInfo notifyInfo) throws ServiceException;
}

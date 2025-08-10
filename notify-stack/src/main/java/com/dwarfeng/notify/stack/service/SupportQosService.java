package com.dwarfeng.notify.stack.service;

import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 支持 QoS 服务。
 *
 * @author DwArFeng
 * @since 1.6.0
 */
public interface SupportQosService extends Service {

    /**
     * 重置路由器。
     *
     * @throws ServiceException 服务异常。
     */
    void resetRouter() throws ServiceException;

    /**
     * 重置发送器。
     *
     * @throws ServiceException 服务异常。
     */
    void resetSender() throws ServiceException;

    /**
     * 重置调度器。
     *
     * @throws ServiceException 服务异常。
     */
    void resetDispatcher() throws ServiceException;
}

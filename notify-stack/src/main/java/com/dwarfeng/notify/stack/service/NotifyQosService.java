package com.dwarfeng.notify.stack.service;

import com.dwarfeng.notify.stack.bean.dto.DispatchContext;
import com.dwarfeng.notify.stack.bean.dto.NotifyInfo;
import com.dwarfeng.notify.stack.bean.dto.RouteContext;
import com.dwarfeng.notify.stack.bean.dto.SendContext;
import com.dwarfeng.notify.stack.bean.entity.key.SenderInfoKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;

/**
 * 通知 QOS 服务。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface NotifyQosService {

    /**
     * 发送通知。
     *
     * @param notifyInfo 通知信息。
     * @throws ServiceException 服务异常。
     */
    void notify(NotifyInfo notifyInfo) throws ServiceException;

    /**
     * 获取指定主键对应的路由上下文。
     *
     * @param routerInfoKey 指定的主键。
     * @return 指定主键对应的路由上下文，如果不存在，则返回 null。
     * @throws ServiceException 服务异常。
     */
    RouteContext getRouteContext(LongIdKey routerInfoKey) throws ServiceException;

    /**
     * 清理路由器本地缓存。
     *
     * @throws ServiceException 服务异常。
     */
    void clearRouterLocalCache() throws ServiceException;

    /**
     * 获取指定主键对应的调度上下文。
     *
     * @param dispatcherInfoKey 指定的主键。
     * @return 指定主键对应的调度上下文，如果不存在，则返回 null。
     * @throws ServiceException 服务异常。
     */
    DispatchContext getDispatchContext(StringIdKey dispatcherInfoKey) throws ServiceException;

    /**
     * 清理调度器本地缓存。
     *
     * @throws ServiceException 服务异常。
     */
    void clearDispatcherLocalCache() throws ServiceException;

    /**
     * 获取指定主键对应的发送上下文。
     *
     * @param senderInfoKey 指定的主键。
     * @return 指定主键对应的发送上下文，如果不存在，则返回 null。
     * @throws ServiceException 服务异常。
     */
    SendContext getSendContext(SenderInfoKey senderInfoKey) throws ServiceException;

    /**
     * 清理发送器本地缓存。
     *
     * @throws ServiceException 服务异常。
     */
    void clearSenderLocalCache() throws ServiceException;
}

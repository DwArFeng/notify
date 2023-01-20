package com.dwarfeng.notify.stack.handler;

import com.dwarfeng.notify.stack.bean.dto.NotifyInfo;
import com.dwarfeng.notify.stack.bean.key.SenderInfoKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 通知处理器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface NotifyHandler extends Handler {

    /**
     * 发送通知。
     *
     * @param notifyInfo 通知信息。
     * @throws HandlerException 处理器异常。
     */
    void notify(NotifyInfo notifyInfo) throws HandlerException;

    /**
     * 获取指定主键对应的路由器。
     *
     * @param routerInfoKey 指定的主键。
     * @return 指定部件的评估上下文，如果主键对应的路由器信息不存在，则返回 null。
     * @throws HandlerException 处理器异常。
     */
    Router getRouter(LongIdKey routerInfoKey) throws HandlerException;

    /**
     * 清理路由器本地缓存。
     *
     * @throws HandlerException 处理器异常。
     */
    void clearRouterLocalCache() throws HandlerException;

    /**
     * 获取指定主键对应的调度器。
     *
     * @param dispatcherInfoKey 指定的主键。
     * @return 指定部件的评估上下文，如果主键对应的调度器信息不存在，则返回 null。
     * @throws HandlerException 处理器异常。
     */
    Dispatcher getDispatcher(StringIdKey dispatcherInfoKey) throws HandlerException;

    /**
     * 清理调度器本地缓存。
     *
     * @throws HandlerException 处理器异常。
     */
    void clearDispatcherLocalCache() throws HandlerException;

    /**
     * 获取指定主键对应的发送器。
     *
     * @param senderInfoKey 指定的主键。
     * @return 指定部件的评估上下文，如果主键对应的发送器信息不存在，则返回 null。
     * @throws HandlerException 处理器异常。
     */
    Sender getSender(SenderInfoKey senderInfoKey) throws HandlerException;

    /**
     * 清理发送器本地缓存。
     *
     * @throws HandlerException 处理器异常。
     */
    void clearSenderLocalCache() throws HandlerException;
}

package com.dwarfeng.notify.stack.handler;

import com.dwarfeng.notify.stack.bean.entity.SendHistory;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

import java.util.List;

/**
 * 推送处理器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface PushHandler extends Handler {

    /**
     * 通知被发送时执行的调度。
     *
     * @param sendHistory 被发送的通知历史。
     * @throws HandlerException 处理器异常。
     */
    void notifySent(SendHistory sendHistory) throws HandlerException;

    /**
     * 通知被发送时执行的调度。
     *
     * @param sendHistories 被发送的通知历史组成的列表。
     * @throws HandlerException 处理器异常。
     */
    void notifySent(List<SendHistory> sendHistories) throws HandlerException;

    /**
     * 路由被重置时执行的调度。
     *
     * @throws HandlerException 处理器异常。
     */
    void routeReset() throws HandlerException;

    /**
     * 调度被重置时执行的调度。
     *
     * @throws HandlerException 处理器异常。
     */
    void dispatchReset() throws HandlerException;

    /**
     * 发送被重置时执行的调度。
     *
     * @throws HandlerException 处理器异常。
     */
    void sendReset() throws HandlerException;
}

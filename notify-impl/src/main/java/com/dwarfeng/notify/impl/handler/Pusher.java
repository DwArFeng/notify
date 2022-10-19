package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.stack.bean.entity.SendHistory;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

import java.util.List;

/**
 * 事件推送器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface Pusher {

    /**
     * 返回事件推送器是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 事件推送器是否支持指定的类型。
     */
    boolean supportType(String type);

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
}

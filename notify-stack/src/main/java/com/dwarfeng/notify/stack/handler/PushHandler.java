package com.dwarfeng.notify.stack.handler;

import com.dwarfeng.notify.stack.bean.dto.NotifyHistoryRecordInfo;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 推送处理器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface PushHandler extends Handler {

    /**
     * 通知历史被记录时执行的调度。
     *
     * @param info 通知历史记录信息。
     * @throws HandlerException 处理器异常。
     */
    void notifyHistoryRecorded(NotifyHistoryRecordInfo info) throws HandlerException;

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

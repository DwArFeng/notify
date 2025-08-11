package com.dwarfeng.notify.sdk.handler;

import com.dwarfeng.notify.stack.bean.dto.NotifyHistoryRecordInfo;
import com.dwarfeng.notify.stack.bean.dto.PurgeFinishedResult;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 事件推送器。
 *
 * @author DwArFeng
 * @since 1.5.0
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

    /**
     * 清除完成时执行的调度。
     *
     * @param result 清除结束结果。
     * @throws HandlerException 处理器异常。
     * @since 1.6.0
     */
    void purgeFinished(PurgeFinishedResult result) throws HandlerException;

    /**
     * 清除失败时执行的调度。
     *
     * @throws HandlerException 处理器异常。
     * @since 1.6.0
     */
    void purgeFailed() throws HandlerException;
}

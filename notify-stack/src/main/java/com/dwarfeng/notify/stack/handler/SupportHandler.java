package com.dwarfeng.notify.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 支持处理器。
 *
 * @author DwArFeng
 * @since 1.6.0
 */
public interface SupportHandler extends Handler {

    /**
     * 重置路由器。
     *
     * @throws HandlerException 处理器异常。
     */
    void resetRouter() throws HandlerException;

    /**
     * 重置发送器。
     *
     * @throws HandlerException 处理器异常。
     */
    void resetSender() throws HandlerException;

    /**
     * 重置调度器。
     *
     * @throws HandlerException 处理器异常。
     */
    void resetDispatcher() throws HandlerException;
}

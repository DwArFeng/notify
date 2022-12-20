package com.dwarfeng.notify.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.StartableHandler;

/**
 * 重置处理器。
 *
 * @author DwArFeng
 * @since 1.2.2
 */
public interface ResetHandler extends StartableHandler {

    /**
     * 重置路由。
     *
     * @throws HandlerException 处理器异常。
     */
    void resetRoute() throws HandlerException;

    /**
     * 重置调度。
     *
     * @throws HandlerException 处理器异常。
     */
    void resetDispatch() throws HandlerException;

    /**
     * 重置发送。
     *
     * @throws HandlerException 处理器异常。
     */
    void resetSend() throws HandlerException;
}

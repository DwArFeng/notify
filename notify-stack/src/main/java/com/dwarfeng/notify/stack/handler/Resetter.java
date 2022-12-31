package com.dwarfeng.notify.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 重置器。
 *
 * @author DwArFeng
 * @since 1.2.2
 */
public interface Resetter {

    /**
     * 初始化重置器。
     *
     * <p>
     * 该方法会在重置器初始化后调用，请将 context 存放在重置器的字段中。<br>
     * 当重置器被触发后，执行上下文中的相应方法即可。
     *
     * @param context 重置器的上下文。
     */
    void init(Context context);

    /**
     * 重置开始。
     *
     * @throws HandlerException 处理器异常。
     */
    void start() throws HandlerException;

    /**
     * 重置结束。
     *
     * @throws HandlerException 处理器异常。
     */
    void stop() throws HandlerException;

    /**
     * 重置器上下文。
     *
     * @author DwArFeng
     * @since 1.2.2
     */
    interface Context {

        /**
         * 重置路由。
         *
         * @throws Exception 执行重置时抛出的任何异常。
         */
        void resetRoute() throws Exception;

        /**
         * 重置调度。
         *
         * @throws Exception 执行重置时抛出的任何异常。
         */
        void resetDispatch() throws Exception;

        /**
         * 重置发送。
         *
         * @throws Exception 执行重置时抛出的任何异常。
         */
        void resetSend() throws Exception;
    }
}

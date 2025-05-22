package com.dwarfeng.notify.sdk.handler;

import com.dwarfeng.notify.stack.exception.DispatcherException;
import com.dwarfeng.notify.stack.handler.Dispatcher;

/**
 * 调度器构造器。
 *
 * @author DwArFeng
 * @since 1.5.0
 */
public interface DispatcherMaker {

    /**
     * 返回制造器是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 制造器是否支持指定的类型。
     */
    boolean supportType(String type);

    /**
     * 根据指定的调度器信息生成一个调度器对象。
     *
     * <p>
     * 可以保证传入的调度器信息中的类型是支持的。
     *
     * @param type  调度器类型。
     * @param param 调度器参数。
     * @return 制造出的调度器。
     * @throws DispatcherException 调度器异常。
     */
    Dispatcher makeDispatcher(String type, String param) throws DispatcherException;
}

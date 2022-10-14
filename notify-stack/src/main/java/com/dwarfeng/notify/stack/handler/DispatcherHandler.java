package com.dwarfeng.notify.stack.handler;

import com.dwarfeng.notify.stack.exception.DispatcherException;

/**
 * 调度器处理器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface DispatcherHandler {

    /**
     * 根据指定的判断器信息构造一个判断器。
     *
     * @param type  调度器类型。
     * @param param 调度器参数。
     * @return 构造的判断器。
     * @throws DispatcherException 调度器异常。
     */
    Dispatcher make(String type, String param) throws DispatcherException;
}

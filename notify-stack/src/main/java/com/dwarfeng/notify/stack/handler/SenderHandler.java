package com.dwarfeng.notify.stack.handler;

import com.dwarfeng.notify.stack.exception.SenderException;

/**
 * 发送器处理器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface SenderHandler {

    /**
     * 根据指定的判断器信息构造一个判断器。
     *
     * @param type  发送器类型。
     * @param param 发送器参数。
     * @return 构造的判断器。
     * @throws SenderException 发送器异常。
     */
    Sender make(String type, String param) throws SenderException;
}

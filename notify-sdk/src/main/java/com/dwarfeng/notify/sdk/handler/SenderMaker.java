package com.dwarfeng.notify.sdk.handler;

import com.dwarfeng.notify.stack.exception.SenderException;
import com.dwarfeng.notify.stack.handler.Sender;

/**
 * 发送器构造器。
 *
 * @author DwArFeng
 * @since 1.5.0
 */
public interface SenderMaker {

    /**
     * 返回制造器是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 制造器是否支持指定的类型。
     */
    boolean supportType(String type);

    /**
     * 根据指定的发送器信息生成一个发送器对象。
     *
     * <p>
     * 可以保证传入的发送器信息中的类型是支持的。
     *
     * @param type  发送器类型。
     * @param param 发送器参数。
     * @return 制造出的发送器。
     * @throws SenderException 发送器异常。
     */
    Sender makeSender(String type, String param) throws SenderException;
}

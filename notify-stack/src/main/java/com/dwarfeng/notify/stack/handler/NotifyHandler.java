package com.dwarfeng.notify.stack.handler;

import com.dwarfeng.notify.stack.bean.dto.NotifyInfo;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 通知处理器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface NotifyHandler extends Handler {

    /**
     * 发送通知。
     *
     * @param notifyInfo 通知信息。
     * @throws HandlerException 处理器异常。
     */
    void notify(NotifyInfo notifyInfo) throws HandlerException;
}

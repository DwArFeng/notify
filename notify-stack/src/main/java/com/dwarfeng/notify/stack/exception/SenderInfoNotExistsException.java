package com.dwarfeng.notify.stack.exception;

import com.dwarfeng.notify.stack.bean.entity.key.SenderInfoKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 发送器信息不存在异常。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class SenderInfoNotExistsException extends HandlerException {

    private static final long serialVersionUID = -5946270273907577117L;

    private final SenderInfoKey senderInfoKey;

    public SenderInfoNotExistsException(SenderInfoKey senderInfoKey) {
        this.senderInfoKey = senderInfoKey;
    }

    public SenderInfoNotExistsException(Throwable cause, SenderInfoKey senderInfoKey) {
        super(cause);
        this.senderInfoKey = senderInfoKey;
    }

    @Override
    public String getMessage() {
        return "发送器信息 " + senderInfoKey + " 不存在";
    }
}

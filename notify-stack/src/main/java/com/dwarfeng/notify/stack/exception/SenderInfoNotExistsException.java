package com.dwarfeng.notify.stack.exception;

import com.dwarfeng.notify.stack.bean.key.SenderInfoKey;
import com.dwarfeng.notify.stack.internal.i18n.StackMessageKey;
import com.dwarfeng.notify.stack.internal.i18n.StackMessages;
import com.dwarfeng.subgrade.basic.stack.exception.HandlerException;

import java.io.Serial;

/**
 * 发送器信息不存在异常。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class SenderInfoNotExistsException extends HandlerException {

    @Serial
    private static final long serialVersionUID = -2900362851034009419L;

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
        return StackMessages.message(StackMessageKey.SENDER_INFO_NOT_EXISTS, senderInfoKey);
    }
}

package com.dwarfeng.notify.stack.exception;

import com.dwarfeng.notify.stack.internal.i18n.StackMessageKey;
import com.dwarfeng.notify.stack.internal.i18n.StackMessages;

import java.io.Serial;

/**
 * 不支持的发送器类型异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class UnsupportedSenderTypeException extends SenderException {

    @Serial
    private static final long serialVersionUID = 8424275931646679171L;

    private final String type;

    public UnsupportedSenderTypeException(String type) {
        this.type = type;
    }

    public UnsupportedSenderTypeException(Throwable cause, String type) {
        super(cause);
        this.type = type;
    }

    @Override
    public String getMessage() {
        return StackMessages.message(StackMessageKey.UNSUPPORTED_SENDER_TYPE, type);
    }
}

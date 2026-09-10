package com.dwarfeng.notify.stack.exception;

import com.dwarfeng.notify.stack.internal.i18n.StackMessageKey;
import com.dwarfeng.notify.stack.internal.i18n.StackMessages;

import java.io.Serial;

/**
 * 发送器构造异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class SenderMakeException extends SenderException {

    @Serial
    private static final long serialVersionUID = 319531295182011844L;

    private final String senderType;
    private final String param;

    public SenderMakeException(String senderType, String param) {
        this.senderType = senderType;
        this.param = param;
    }

    public SenderMakeException(Throwable cause, String senderType, String param) {
        super(cause);
        this.senderType = senderType;
        this.param = param;
    }

    @Override
    public String getMessage() {
        return StackMessages.message(StackMessageKey.SENDER_MAKE_EXCEPTION, senderType, param);
    }
}

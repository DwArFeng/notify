package com.dwarfeng.notify.stack.exception;

/**
 * 发送器构造异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class SenderMakeException extends SenderException {

    private static final long serialVersionUID = 5577007340111336445L;

    public SenderMakeException() {
    }

    public SenderMakeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SenderMakeException(String message) {
        super(message);
    }

    public SenderMakeException(Throwable cause) {
        super(cause);
    }
}

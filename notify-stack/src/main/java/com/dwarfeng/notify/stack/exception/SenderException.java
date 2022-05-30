package com.dwarfeng.notify.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 发送器异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class SenderException extends HandlerException {

    private static final long serialVersionUID = 2333548017100518399L;

    public SenderException() {
    }

    public SenderException(String message, Throwable cause) {
        super(message, cause);
    }

    public SenderException(String message) {
        super(message);
    }

    public SenderException(Throwable cause) {
        super(cause);
    }
}

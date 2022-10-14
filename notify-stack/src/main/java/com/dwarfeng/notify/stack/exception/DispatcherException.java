package com.dwarfeng.notify.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 调度器异常。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class DispatcherException extends HandlerException {

    private static final long serialVersionUID = 4260808881998068575L;

    public DispatcherException() {
    }

    public DispatcherException(String message, Throwable cause) {
        super(message, cause);
    }

    public DispatcherException(String message) {
        super(message);
    }

    public DispatcherException(Throwable cause) {
        super(cause);
    }
}

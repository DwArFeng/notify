package com.dwarfeng.notify.stack.exception;

/**
 * 路由器构造异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class RouterMakeException extends RouterException {

    private static final long serialVersionUID = -985767174276194192L;

    public RouterMakeException() {
    }

    public RouterMakeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RouterMakeException(String message) {
        super(message);
    }

    public RouterMakeException(Throwable cause) {
        super(cause);
    }
}

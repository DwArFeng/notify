package com.dwarfeng.notify.stack.exception;

import com.dwarfeng.subgrade.basic.stack.exception.HandlerException;

import java.io.Serial;

/**
 * 路由器异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class RouterException extends HandlerException {

    @Serial
    private static final long serialVersionUID = 1428991659014421967L;

    public RouterException() {
    }

    public RouterException(String message, Throwable cause) {
        super(message, cause);
    }

    public RouterException(String message) {
        super(message);
    }

    public RouterException(Throwable cause) {
        super(cause);
    }
}

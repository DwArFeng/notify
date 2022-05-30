package com.dwarfeng.notify.stack.exception;

/**
 * 路由器执行异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class RouterExecutionException extends RouterException {

    private static final long serialVersionUID = 7727523113758951141L;

    public RouterExecutionException() {
    }

    public RouterExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public RouterExecutionException(String message) {
        super(message);
    }

    public RouterExecutionException(Throwable cause) {
        super(cause);
    }
}

package com.dwarfeng.notify.stack.exception;

/**
 * 调度器执行异常。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class DispatcherExecutionException extends DispatcherException {

    private static final long serialVersionUID = -3520759771951461040L;

    public DispatcherExecutionException() {
    }

    public DispatcherExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DispatcherExecutionException(String message) {
        super(message);
    }

    public DispatcherExecutionException(Throwable cause) {
        super(cause);
    }
}

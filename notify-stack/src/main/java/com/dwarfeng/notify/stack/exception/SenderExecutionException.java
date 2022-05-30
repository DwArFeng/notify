package com.dwarfeng.notify.stack.exception;

/**
 * 发送器执行异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class SenderExecutionException extends SenderException {

    private static final long serialVersionUID = 151966740930854827L;

    public SenderExecutionException() {
    }

    public SenderExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public SenderExecutionException(String message) {
        super(message);
    }

    public SenderExecutionException(Throwable cause) {
        super(cause);
    }
}

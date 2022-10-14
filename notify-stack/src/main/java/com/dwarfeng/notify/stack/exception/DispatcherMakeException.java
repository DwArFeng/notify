package com.dwarfeng.notify.stack.exception;

/**
 * 调度器构造异常。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class DispatcherMakeException extends DispatcherException {

    private static final long serialVersionUID = -6038666667222292186L;
    
    private final String dispatcherType;
    private final String param;

    public DispatcherMakeException(String dispatcherType, String param) {
        this.dispatcherType = dispatcherType;
        this.param = param;
    }

    public DispatcherMakeException(Throwable cause, String dispatcherType, String param) {
        super(cause);
        this.dispatcherType = dispatcherType;
        this.param = param;
    }

    @Override
    public String getMessage() {
        return "调度器构造异常, 类型为: " + dispatcherType + ", 参数为: " + param;
    }
}

package com.dwarfeng.notify.stack.exception;

/**
 * 不支持的调度器类型异常。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class UnsupportedDispatcherTypeException extends SenderException {

    private static final long serialVersionUID = 2220611918313284006L;
    
    private final String type;

    public UnsupportedDispatcherTypeException(String type) {
        this.type = type;
    }

    public UnsupportedDispatcherTypeException(Throwable cause, String type) {
        super(cause);
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "不支持的调度器类型: " + type;
    }
}

package com.dwarfeng.notify.stack.exception;

/**
 * 路由器构造异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class RouterMakeException extends RouterException {

    private static final long serialVersionUID = -8716257155912288696L;

    private final String routerType;
    private final String param;

    public RouterMakeException(String routerType, String param) {
        this.routerType = routerType;
        this.param = param;
    }

    public RouterMakeException(Throwable cause, String routerType, String param) {
        super(cause);
        this.routerType = routerType;
        this.param = param;
    }

    @Override
    public String getMessage() {
        return "路由器构造异常, 类型为: " + routerType + ", 参数为: " + param;
    }
}

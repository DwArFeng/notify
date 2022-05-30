package com.dwarfeng.notify.stack.exception;

/**
 * 不支持的路由器类型异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class UnsupportedRouterTypeException extends RouterException {

    private static final long serialVersionUID = 1953787046753101292L;

    private final String type;

    public UnsupportedRouterTypeException(String type) {
        this.type = type;
    }

    public UnsupportedRouterTypeException(Throwable cause, String type) {
        super(cause);
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "不支持的路由器类型: " + type;
    }
}

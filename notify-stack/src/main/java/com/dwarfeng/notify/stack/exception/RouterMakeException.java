package com.dwarfeng.notify.stack.exception;

import com.dwarfeng.notify.stack.internal.i18n.StackMessageKey;
import com.dwarfeng.notify.stack.internal.i18n.StackMessages;

import java.io.Serial;

/**
 * 路由器构造异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class RouterMakeException extends RouterException {

    @Serial
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
        return StackMessages.message(StackMessageKey.ROUTER_MAKE_EXCEPTION, routerType, param);
    }
}

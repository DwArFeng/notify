package com.dwarfeng.notify.stack.exception;

import com.dwarfeng.notify.stack.internal.i18n.StackMessageKey;
import com.dwarfeng.notify.stack.internal.i18n.StackMessages;

import java.io.Serial;

/**
 * 不支持的路由器类型异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class UnsupportedRouterTypeException extends RouterException {

    @Serial
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
        return StackMessages.message(StackMessageKey.UNSUPPORTED_ROUTER_TYPE, type);
    }
}

package com.dwarfeng.notify.stack.exception;

import com.dwarfeng.notify.stack.internal.i18n.StackMessageKey;
import com.dwarfeng.notify.stack.internal.i18n.StackMessages;
import com.dwarfeng.subgrade.basic.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.basic.stack.exception.HandlerException;

import java.io.Serial;

/**
 * 路由器信息不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class RouterInfoNotExistsException extends HandlerException {

    @Serial
    private static final long serialVersionUID = -8682639254134428083L;

    private final LongIdKey routerInfoKey;

    public RouterInfoNotExistsException(LongIdKey routerInfoKey) {
        this.routerInfoKey = routerInfoKey;
    }

    public RouterInfoNotExistsException(Throwable cause, LongIdKey routerInfoKey) {
        super(cause);
        this.routerInfoKey = routerInfoKey;
    }

    @Override
    public String getMessage() {
        return StackMessages.message(StackMessageKey.ROUTER_INFO_NOT_EXISTS, routerInfoKey);
    }
}

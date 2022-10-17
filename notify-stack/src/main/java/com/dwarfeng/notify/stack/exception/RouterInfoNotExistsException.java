package com.dwarfeng.notify.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 路由器信息不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class RouterInfoNotExistsException extends HandlerException {

    private static final long serialVersionUID = 894423277298345147L;
    
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
        return "路由器信息 " + routerInfoKey + " 不存在";
    }
}

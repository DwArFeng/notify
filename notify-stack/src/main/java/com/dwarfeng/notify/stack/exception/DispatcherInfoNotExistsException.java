package com.dwarfeng.notify.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 调度器信息不存在异常。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class DispatcherInfoNotExistsException extends HandlerException {

    private static final long serialVersionUID = 5314206805665491703L;

    private final StringIdKey dispatcherInfoKey;

    public DispatcherInfoNotExistsException(StringIdKey dispatcherInfoKey) {
        this.dispatcherInfoKey = dispatcherInfoKey;
    }

    public DispatcherInfoNotExistsException(Throwable cause, StringIdKey dispatcherInfoKey) {
        super(cause);
        this.dispatcherInfoKey = dispatcherInfoKey;
    }

    @Override
    public String getMessage() {
        return "调度器信息 " + dispatcherInfoKey + " 不存在";
    }
}

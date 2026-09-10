package com.dwarfeng.notify.stack.exception;

import com.dwarfeng.notify.stack.internal.i18n.StackMessageKey;
import com.dwarfeng.notify.stack.internal.i18n.StackMessages;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.basic.stack.exception.HandlerException;

import java.io.Serial;

/**
 * 调度器信息不存在异常。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class DispatcherInfoNotExistsException extends HandlerException {

    @Serial
    private static final long serialVersionUID = 4613471776510071083L;

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
        return StackMessages.message(StackMessageKey.DISPATCHER_INFO_NOT_EXISTS, dispatcherInfoKey);
    }
}

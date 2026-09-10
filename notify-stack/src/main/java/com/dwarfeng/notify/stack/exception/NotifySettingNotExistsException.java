package com.dwarfeng.notify.stack.exception;

import com.dwarfeng.notify.stack.internal.i18n.StackMessageKey;
import com.dwarfeng.notify.stack.internal.i18n.StackMessages;
import com.dwarfeng.subgrade.basic.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.basic.stack.exception.HandlerException;

import java.io.Serial;

/**
 * 通知设置不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class NotifySettingNotExistsException extends HandlerException {

    @Serial
    private static final long serialVersionUID = -6591239391700680425L;

    private final LongIdKey notifySettingKey;

    public NotifySettingNotExistsException(LongIdKey notifySettingKey) {
        this.notifySettingKey = notifySettingKey;
    }

    public NotifySettingNotExistsException(Throwable cause, LongIdKey notifySettingKey) {
        super(cause);
        this.notifySettingKey = notifySettingKey;
    }

    @Override
    public String getMessage() {
        return StackMessages.message(StackMessageKey.NOTIFY_SETTING_NOT_EXISTS, notifySettingKey);
    }
}

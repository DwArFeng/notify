package com.dwarfeng.notify.stack.exception;

import com.dwarfeng.notify.stack.internal.i18n.StackMessageKey;
import com.dwarfeng.notify.stack.internal.i18n.StackMessages;
import com.dwarfeng.subgrade.basic.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.basic.stack.exception.HandlerException;

import java.io.Serial;

/**
 * 通知设置未使能异常。
 *
 * @author DwArFeng
 * @since 1.0.5
 */
public class NotifySettingDisabledException extends HandlerException {

    @Serial
    private static final long serialVersionUID = 7582339164855658303L;

    private final LongIdKey notifySettingKey;

    public NotifySettingDisabledException(LongIdKey notifySettingKey) {
        this.notifySettingKey = notifySettingKey;
    }

    public NotifySettingDisabledException(Throwable cause, LongIdKey notifySettingKey) {
        super(cause);
        this.notifySettingKey = notifySettingKey;
    }

    @Override
    public String getMessage() {
        return StackMessages.message(StackMessageKey.NOTIFY_SETTING_DISABLED, notifySettingKey);
    }
}

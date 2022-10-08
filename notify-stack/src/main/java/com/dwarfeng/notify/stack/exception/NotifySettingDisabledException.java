package com.dwarfeng.notify.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 通知设置未使能异常。
 *
 * @author DwArFeng
 * @since 1.0.5
 */
public class NotifySettingDisabledException extends HandlerException {

    private static final long serialVersionUID = -7401269890888016116L;

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
        return "通知设置 " + notifySettingKey + " 未使能";
    }
}

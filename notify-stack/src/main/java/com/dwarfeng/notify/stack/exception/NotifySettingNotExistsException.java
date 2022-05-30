package com.dwarfeng.notify.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 通知设置不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class NotifySettingNotExistsException extends HandlerException {

    private static final long serialVersionUID = -5619719405129064458L;

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
        return "通知设置 " + notifySettingKey + " 不存在";
    }
}

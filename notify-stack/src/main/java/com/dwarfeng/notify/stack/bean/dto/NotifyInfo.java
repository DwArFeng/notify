package com.dwarfeng.notify.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Arrays;

/**
 * 通知信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class NotifyInfo implements Dto {

    private static final long serialVersionUID = 1607405822178434303L;

    private LongIdKey notifySettingKey;
    private Object[] context;

    public NotifyInfo() {
    }

    public NotifyInfo(LongIdKey notifySettingKey, Object[] context) {
        this.notifySettingKey = notifySettingKey;
        this.context = context;
    }

    public LongIdKey getNotifySettingKey() {
        return notifySettingKey;
    }

    public void setNotifySettingKey(LongIdKey notifySettingKey) {
        this.notifySettingKey = notifySettingKey;
    }

    public Object[] getContext() {
        return context;
    }

    public void setContext(Object[] context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "NotifyInfo{" +
                "notifySettingKey=" + notifySettingKey +
                ", context=" + Arrays.toString(context) +
                '}';
    }
}

package com.dwarfeng.notify.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 通知信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class NotifyInfo implements Dto {

    private static final long serialVersionUID = 4505514778249069224L;

    private LongIdKey notifySettingKey;
    private Object routerContext;
    private Object senderContext;

    public NotifyInfo() {
    }

    public NotifyInfo(LongIdKey notifySettingKey, Object routerContext, Object senderContext) {
        this.notifySettingKey = notifySettingKey;
        this.routerContext = routerContext;
        this.senderContext = senderContext;
    }

    public LongIdKey getNotifySettingKey() {
        return notifySettingKey;
    }

    public void setNotifySettingKey(LongIdKey notifySettingKey) {
        this.notifySettingKey = notifySettingKey;
    }

    public Object getRouterContext() {
        return routerContext;
    }

    public void setRouterContext(Object routerContext) {
        this.routerContext = routerContext;
    }

    public Object getSenderContext() {
        return senderContext;
    }

    public void setSenderContext(Object senderContext) {
        this.senderContext = senderContext;
    }

    @Override
    public String toString() {
        return "NotifyInfo{" +
                "notifySettingKey=" + notifySettingKey +
                ", routerContext=" + routerContext +
                ", senderContext=" + senderContext +
                '}';
    }
}

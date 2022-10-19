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

    private static final long serialVersionUID = 4636026866629218486L;

    private LongIdKey notifySettingKey;
    private String routeInfo;
    private String dispatchInfo;
    private String sendInfo;

    public NotifyInfo() {
    }

    public NotifyInfo(LongIdKey notifySettingKey, String routeInfo, String dispatchInfo, String sendInfo) {
        this.notifySettingKey = notifySettingKey;
        this.routeInfo = routeInfo;
        this.dispatchInfo = dispatchInfo;
        this.sendInfo = sendInfo;
    }

    public LongIdKey getNotifySettingKey() {
        return notifySettingKey;
    }

    public void setNotifySettingKey(LongIdKey notifySettingKey) {
        this.notifySettingKey = notifySettingKey;
    }

    public String getRouteInfo() {
        return routeInfo;
    }

    public void setRouteInfo(String routeInfo) {
        this.routeInfo = routeInfo;
    }

    public String getDispatchInfo() {
        return dispatchInfo;
    }

    public void setDispatchInfo(String dispatchInfo) {
        this.dispatchInfo = dispatchInfo;
    }

    public String getSendInfo() {
        return sendInfo;
    }

    public void setSendInfo(String sendInfo) {
        this.sendInfo = sendInfo;
    }

    @Override
    public String toString() {
        return "NotifyInfo{" +
                "notifySettingKey=" + notifySettingKey +
                ", routeInfo='" + routeInfo + '\'' +
                ", dispatchInfo='" + dispatchInfo + '\'' +
                ", sendInfo='" + sendInfo + '\'' +
                '}';
    }
}

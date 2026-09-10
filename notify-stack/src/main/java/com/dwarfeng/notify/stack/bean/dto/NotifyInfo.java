package com.dwarfeng.notify.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.basic.stack.bean.key.LongIdKey;

import java.io.Serial;
import java.util.Map;

/**
 * 通知信息。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class NotifyInfo implements Dto {

    @Serial
    private static final long serialVersionUID = 1130867621945987363L;

    private LongIdKey notifySettingKey;
    private Map<String, String> routeInfoMap;
    private Map<String, String> dispatchInfoMap;
    private Map<String, String> sendInfoMap;

    public NotifyInfo() {
    }

    public NotifyInfo(
            LongIdKey notifySettingKey,
            Map<String, String> routeInfoMap, Map<String, String> dispatchInfoMap, Map<String, String> sendInfoMap
    ) {
        this.notifySettingKey = notifySettingKey;
        this.routeInfoMap = routeInfoMap;
        this.dispatchInfoMap = dispatchInfoMap;
        this.sendInfoMap = sendInfoMap;
    }

    public LongIdKey getNotifySettingKey() {
        return notifySettingKey;
    }

    public void setNotifySettingKey(LongIdKey notifySettingKey) {
        this.notifySettingKey = notifySettingKey;
    }

    public Map<String, String> getRouteInfoMap() {
        return routeInfoMap;
    }

    public void setRouteInfoMap(Map<String, String> routeInfoMap) {
        this.routeInfoMap = routeInfoMap;
    }

    public Map<String, String> getDispatchInfoMap() {
        return dispatchInfoMap;
    }

    public void setDispatchInfoMap(Map<String, String> dispatchInfoMap) {
        this.dispatchInfoMap = dispatchInfoMap;
    }

    public Map<String, String> getSendInfoMap() {
        return sendInfoMap;
    }

    public void setSendInfoMap(Map<String, String> sendInfoMap) {
        this.sendInfoMap = sendInfoMap;
    }

    @Override
    public String toString() {
        return "NotifyInfo{" +
                "notifySettingKey=" + notifySettingKey +
                ", routeInfoMap='" + routeInfoMap + '\'' +
                ", dispatchInfoMap='" + dispatchInfoMap + '\'' +
                ", sendInfoMap='" + sendInfoMap + '\'' +
                '}';
    }
}

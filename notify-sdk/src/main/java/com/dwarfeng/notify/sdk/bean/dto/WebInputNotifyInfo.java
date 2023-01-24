package com.dwarfeng.notify.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.dto.NotifyInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Objects;

/**
 * WebInput 通知信息。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class WebInputNotifyInfo implements Dto {

    private static final long serialVersionUID = 543189554207402052L;

    public static NotifyInfo toStackBean(WebInputNotifyInfo webInputNotifyInfo) {
        if (Objects.isNull(webInputNotifyInfo)) {
            return null;
        } else {
            return new NotifyInfo(
                    WebInputLongIdKey.toStackBean(webInputNotifyInfo.getNotifySettingKey()),
                    webInputNotifyInfo.getRouteInfoMap(),
                    webInputNotifyInfo.getDispatchInfoMap(),
                    webInputNotifyInfo.getSendInfoMap()
            );
        }
    }

    @JSONField(name = "notify_setting_key")
    @NotNull
    @Valid
    private WebInputLongIdKey notifySettingKey;

    @JSONField(name = "route_infos")
    private Map<String, String> routeInfoMap;

    @JSONField(name = "dispatch_infos")
    private Map<String, String> dispatchInfoMap;

    @JSONField(name = "send_infos")
    private Map<String, String> sendInfoMap;

    public WebInputNotifyInfo() {
    }

    public WebInputLongIdKey getNotifySettingKey() {
        return notifySettingKey;
    }

    public void setNotifySettingKey(WebInputLongIdKey notifySettingKey) {
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
        return "WebInputNotifyInfo{" +
                "notifySettingKey=" + notifySettingKey +
                ", routeInfoMap='" + routeInfoMap + '\'' +
                ", dispatchInfoMap='" + dispatchInfoMap + '\'' +
                ", sendInfoMap='" + sendInfoMap + '\'' +
                '}';
    }
}

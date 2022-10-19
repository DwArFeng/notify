package com.dwarfeng.notify.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.dto.NotifyInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 通知信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputNotifyInfo implements Dto {

    private static final long serialVersionUID = -6375412197497372943L;

    public static NotifyInfo toStackBean(WebInputNotifyInfo webInputNotifyInfo) {
        if (Objects.isNull(webInputNotifyInfo)) {
            return null;
        } else {
            return new NotifyInfo(
                    WebInputLongIdKey.toStackBean(webInputNotifyInfo.getNotifySettingKey()),
                    webInputNotifyInfo.getRouteInfo(), webInputNotifyInfo.getDispatchInfo(),
                    webInputNotifyInfo.getSendInfo()
            );
        }
    }

    @JSONField(name = "notify_setting_key")
    @Valid
    @NotNull
    private WebInputLongIdKey notifySettingKey;

    @JSONField(name = "route_info")
    private String routeInfo;

    @JSONField(name = "dispatch_info")
    private String dispatchInfo;

    @JSONField(name = "send_info")
    private String sendInfo;

    public WebInputNotifyInfo() {
    }

    public WebInputLongIdKey getNotifySettingKey() {
        return notifySettingKey;
    }

    public void setNotifySettingKey(WebInputLongIdKey notifySettingKey) {
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
        return "WebInputNotifyInfo{" +
                "notifySettingKey=" + notifySettingKey +
                ", routeInfo='" + routeInfo + '\'' +
                ", dispatchInfo='" + dispatchInfo + '\'' +
                ", sendInfo='" + sendInfo + '\'' +
                '}';
    }
}

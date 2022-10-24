package com.dwarfeng.notify.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.dto.NotifyInfo;
import com.dwarfeng.notify.stack.bean.dto.NotifyInfo.InfoDetail;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * WebInput 通知信息。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class WebInputNotifyInfo implements Dto {

    private static final long serialVersionUID = -3668319529952065134L;

    public static NotifyInfo toStackBean(WebInputNotifyInfo webInputNotifyInfo) {
        if (Objects.isNull(webInputNotifyInfo)) {
            return null;
        } else {
            return new NotifyInfo(
                    WebInputLongIdKey.toStackBean(webInputNotifyInfo.getNotifySettingKey()),
                    webInputNotifyInfo.getRouteInfoDetails().stream().map(WebInputInfoDetail::toStackBean)
                            .collect(Collectors.toList()),
                    webInputNotifyInfo.getDispatchInfoDetails().stream().map(WebInputInfoDetail::toStackBean)
                            .collect(Collectors.toList()),
                    webInputNotifyInfo.getSendInfoDetails().stream().map(WebInputInfoDetail::toStackBean)
                            .collect(Collectors.toList())
            );
        }
    }

    @JSONField(name = "notify_setting_key")
    @NotNull
    @Valid
    private WebInputLongIdKey notifySettingKey;

    @JSONField(name = "route_info_details")
    @NotNull
    @Valid
    private List<WebInputInfoDetail> routeInfoDetails;

    @JSONField(name = "dispatch_info_details")
    @NotNull
    @Valid
    private List<WebInputInfoDetail> dispatchInfoDetails;

    @JSONField(name = "send_info_details")
    @NotNull
    @Valid
    private List<WebInputInfoDetail> sendInfoDetails;

    public WebInputNotifyInfo() {
    }

    public WebInputLongIdKey getNotifySettingKey() {
        return notifySettingKey;
    }

    public void setNotifySettingKey(WebInputLongIdKey notifySettingKey) {
        this.notifySettingKey = notifySettingKey;
    }

    public List<WebInputInfoDetail> getRouteInfoDetails() {
        return routeInfoDetails;
    }

    public void setRouteInfoDetails(List<WebInputInfoDetail> routeInfoDetails) {
        this.routeInfoDetails = routeInfoDetails;
    }

    public List<WebInputInfoDetail> getDispatchInfoDetails() {
        return dispatchInfoDetails;
    }

    public void setDispatchInfoDetails(List<WebInputInfoDetail> dispatchInfoDetails) {
        this.dispatchInfoDetails = dispatchInfoDetails;
    }

    public List<WebInputInfoDetail> getSendInfoDetails() {
        return sendInfoDetails;
    }

    public void setSendInfoDetails(List<WebInputInfoDetail> sendInfoDetails) {
        this.sendInfoDetails = sendInfoDetails;
    }

    @Override
    public String toString() {
        return "WebInputNotifyInfo{" +
                "notifySettingKey=" + notifySettingKey +
                ", routeInfoDetails=" + routeInfoDetails +
                ", dispatchInfoDetails=" + dispatchInfoDetails +
                ", sendInfoDetails=" + sendInfoDetails +
                '}';
    }

    /**
     * WebInput 信息详情。
     *
     * @author DwArFeng
     * @since 1.1.0
     */
    public static class WebInputInfoDetail implements Dto {

        private static final long serialVersionUID = -4651918349655167801L;

        public static InfoDetail toStackBean(WebInputInfoDetail webInputInfoDetail) {
            if (Objects.isNull(webInputInfoDetail)) {
                return null;
            } else {
                return new InfoDetail(
                        webInputInfoDetail.getType(), webInputInfoDetail.getInfo()
                );
            }
        }

        @JSONField(name = "type")
        @NotNull
        @NotEmpty
        private String type;

        @JSONField(name = "info")
        private String info;

        public WebInputInfoDetail() {
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WebInputInfoDetail{" +
                    "type='" + type + '\'' +
                    ", info='" + info + '\'' +
                    '}';
        }
    }
}

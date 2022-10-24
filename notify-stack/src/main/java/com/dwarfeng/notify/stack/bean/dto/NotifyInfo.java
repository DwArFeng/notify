package com.dwarfeng.notify.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.List;

/**
 * 通知信息。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class NotifyInfo implements Dto {

    private static final long serialVersionUID = -2087796177459172443L;

    private LongIdKey notifySettingKey;
    private List<InfoDetail> routeInfoDetails;
    private List<InfoDetail> dispatchInfoDetails;
    private List<InfoDetail> sendInfoDetails;

    public NotifyInfo() {
    }

    public NotifyInfo(
            LongIdKey notifySettingKey, List<InfoDetail> routeInfoDetails, List<InfoDetail> dispatchInfoDetails,
            List<InfoDetail> sendInfoDetails
    ) {
        this.notifySettingKey = notifySettingKey;
        this.routeInfoDetails = routeInfoDetails;
        this.dispatchInfoDetails = dispatchInfoDetails;
        this.sendInfoDetails = sendInfoDetails;
    }

    public LongIdKey getNotifySettingKey() {
        return notifySettingKey;
    }

    public void setNotifySettingKey(LongIdKey notifySettingKey) {
        this.notifySettingKey = notifySettingKey;
    }

    public List<InfoDetail> getRouteInfoDetails() {
        return routeInfoDetails;
    }

    public void setRouteInfoDetails(List<InfoDetail> routeInfoDetails) {
        this.routeInfoDetails = routeInfoDetails;
    }

    public List<InfoDetail> getDispatchInfoDetails() {
        return dispatchInfoDetails;
    }

    public void setDispatchInfoDetails(List<InfoDetail> dispatchInfoDetails) {
        this.dispatchInfoDetails = dispatchInfoDetails;
    }

    public List<InfoDetail> getSendInfoDetails() {
        return sendInfoDetails;
    }

    public void setSendInfoDetails(List<InfoDetail> sendInfoDetails) {
        this.sendInfoDetails = sendInfoDetails;
    }

    @Override
    public String toString() {
        return "NotifyInfo{" +
                "notifySettingKey=" + notifySettingKey +
                ", routeInfoDetails=" + routeInfoDetails +
                ", dispatchInfoDetails=" + dispatchInfoDetails +
                ", sendInfoDetails=" + sendInfoDetails +
                '}';
    }

    /**
     * 信息详情。
     *
     * @author DwArFeng
     * @since 1.1.0
     */
    public static class InfoDetail implements Dto {

        private static final long serialVersionUID = -2380963124946745200L;

        private String type;
        private String info;

        public InfoDetail() {
        }

        public InfoDetail(String type, String info) {
            this.type = type;
            this.info = info;
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
            return "InfoDetail{" +
                    "type='" + type + '\'' +
                    ", info='" + info + '\'' +
                    '}';
        }
    }
}

package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.entity.SendHistory;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 发送历史。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class FastJsonSendHistory implements Bean {

    private static final long serialVersionUID = -3706903684741501662L;

    public static FastJsonSendHistory of(SendHistory sendHistory) {
        if (Objects.isNull(sendHistory)) {
            return null;
        } else {
            return new FastJsonSendHistory(
                    FastJsonLongIdKey.of(sendHistory.getKey()),
                    FastJsonLongIdKey.of(sendHistory.getNotifySettingKey()),
                    FastJsonStringIdKey.of(sendHistory.getTopicKey()),
                    FastJsonStringIdKey.of(sendHistory.getUserKey()),
                    sendHistory.getHappenedDate(), sendHistory.getRouteInfo(), sendHistory.getDispatchInfo(),
                    sendHistory.getSendInfo(), sendHistory.isSucceedFlag(), sendHistory.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "notify_setting_key", ordinal = 2)
    private FastJsonLongIdKey notifySettingKey;

    @JSONField(name = "topic_key", ordinal = 3)
    private FastJsonStringIdKey topicKey;

    @JSONField(name = "user_key", ordinal = 4)
    private FastJsonStringIdKey userKey;

    @JSONField(name = "happened_date", ordinal = 5)
    private Date happenedDate;

    @JSONField(name = "route_info", ordinal = 6)
    private String routeInfo;

    @JSONField(name = "dispatch_info", ordinal = 7)
    private String dispatchInfo;

    @JSONField(name = "send_info", ordinal = 8)
    private String sendInfo;

    @JSONField(name = "succeed_flag", ordinal = 9)
    private boolean succeedFlag;

    @JSONField(name = "remark", ordinal = 10)
    private String remark;

    public FastJsonSendHistory() {
    }

    public FastJsonSendHistory(
            FastJsonLongIdKey key, FastJsonLongIdKey notifySettingKey, FastJsonStringIdKey topicKey,
            FastJsonStringIdKey userKey, Date happenedDate, String routeInfo, String dispatchInfo, String sendInfo,
            boolean succeedFlag, String remark
    ) {
        this.key = key;
        this.notifySettingKey = notifySettingKey;
        this.topicKey = topicKey;
        this.userKey = userKey;
        this.happenedDate = happenedDate;
        this.routeInfo = routeInfo;
        this.dispatchInfo = dispatchInfo;
        this.sendInfo = sendInfo;
        this.succeedFlag = succeedFlag;
        this.remark = remark;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonLongIdKey getNotifySettingKey() {
        return notifySettingKey;
    }

    public void setNotifySettingKey(FastJsonLongIdKey notifySettingKey) {
        this.notifySettingKey = notifySettingKey;
    }

    public FastJsonStringIdKey getTopicKey() {
        return topicKey;
    }

    public void setTopicKey(FastJsonStringIdKey topicKey) {
        this.topicKey = topicKey;
    }

    public FastJsonStringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(FastJsonStringIdKey userKey) {
        this.userKey = userKey;
    }

    public Date getHappenedDate() {
        return happenedDate;
    }

    public void setHappenedDate(Date happenedDate) {
        this.happenedDate = happenedDate;
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

    public boolean isSucceedFlag() {
        return succeedFlag;
    }

    public void setSucceedFlag(boolean succeedFlag) {
        this.succeedFlag = succeedFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonSendHistory{" +
                "key=" + key +
                ", notifySettingKey=" + notifySettingKey +
                ", topicKey=" + topicKey +
                ", userKey=" + userKey +
                ", happenedDate=" + happenedDate +
                ", routeInfo='" + routeInfo + '\'' +
                ", dispatchInfo='" + dispatchInfo + '\'' +
                ", sendInfo='" + sendInfo + '\'' +
                ", succeedFlag=" + succeedFlag +
                ", remark='" + remark + '\'' +
                '}';
    }
}

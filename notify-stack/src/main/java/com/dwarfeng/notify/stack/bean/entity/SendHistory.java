package com.dwarfeng.notify.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

import java.util.Date;

/**
 * 发送历史。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class SendHistory implements Entity<LongIdKey> {

    private static final long serialVersionUID = -3680956971305126819L;

    private LongIdKey key;
    private LongIdKey notifySettingKey;
    private StringIdKey topicKey;
    private StringIdKey userKey;
    private Date happenedDate;
    private String routeInfo;
    private String sendInfo;
    private boolean succeedFlag;
    private String remark;

    public SendHistory() {
    }

    public SendHistory(
            LongIdKey key, LongIdKey notifySettingKey, StringIdKey topicKey, StringIdKey userKey, Date happenedDate,
            String routeInfo, String sendInfo, boolean succeedFlag, String remark
    ) {
        this.key = key;
        this.notifySettingKey = notifySettingKey;
        this.topicKey = topicKey;
        this.userKey = userKey;
        this.happenedDate = happenedDate;
        this.routeInfo = routeInfo;
        this.sendInfo = sendInfo;
        this.succeedFlag = succeedFlag;
        this.remark = remark;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
    }

    public LongIdKey getNotifySettingKey() {
        return notifySettingKey;
    }

    public void setNotifySettingKey(LongIdKey notifySettingKey) {
        this.notifySettingKey = notifySettingKey;
    }

    public StringIdKey getTopicKey() {
        return topicKey;
    }

    public void setTopicKey(StringIdKey topicKey) {
        this.topicKey = topicKey;
    }

    public StringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(StringIdKey userKey) {
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
        return "SendHistory{" +
                "key=" + key +
                ", notifySettingKey=" + notifySettingKey +
                ", topicKey=" + topicKey +
                ", userKey=" + userKey +
                ", happenedDate=" + happenedDate +
                ", routeInfo='" + routeInfo + '\'' +
                ", sendInfo='" + sendInfo + '\'' +
                ", succeedFlag=" + succeedFlag +
                ", remark='" + remark + '\'' +
                '}';
    }
}

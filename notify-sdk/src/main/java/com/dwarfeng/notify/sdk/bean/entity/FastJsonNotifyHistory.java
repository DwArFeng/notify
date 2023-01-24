package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.entity.NotifyHistory;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * 通知历史。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class FastJsonNotifyHistory implements Bean {

    private static final long serialVersionUID = -8048192394208557222L;

    public static FastJsonNotifyHistory of(NotifyHistory notifyHistory) {
        if (Objects.isNull(notifyHistory)) {
            return null;
        } else {
            return new FastJsonNotifyHistory(
                    FastJsonLongIdKey.of(notifyHistory.getKey()),
                    FastJsonLongIdKey.of(notifyHistory.getNotifySettingKey()),
                    notifyHistory.getHappenedDate(), notifyHistory.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "notify_setting_key", ordinal = 2)
    private FastJsonLongIdKey notifySettingKey;

    @JSONField(name = "happened_date", ordinal = 3)
    private Date happenedDate;

    @JSONField(name = "remark", ordinal = 4)
    private String remark;

    public FastJsonNotifyHistory() {
    }

    public FastJsonNotifyHistory(
            FastJsonLongIdKey key, FastJsonLongIdKey notifySettingKey, Date happenedDate, String remark
    ) {
        this.key = key;
        this.notifySettingKey = notifySettingKey;
        this.happenedDate = happenedDate;
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

    public Date getHappenedDate() {
        return happenedDate;
    }

    public void setHappenedDate(Date happenedDate) {
        this.happenedDate = happenedDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonNotifyHistory{" +
                "key=" + key +
                ", notifySettingKey=" + notifySettingKey +
                ", happenedDate=" + happenedDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}

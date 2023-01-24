package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.entity.NotifyHistory;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * JSFixed FastJson 通知历史。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class JSFixedFastJsonNotifyHistory implements Bean {

    private static final long serialVersionUID = 7746511971020093378L;

    public static JSFixedFastJsonNotifyHistory of(NotifyHistory notifyHistory) {
        if (Objects.isNull(notifyHistory)) {
            return null;
        } else {
            return new JSFixedFastJsonNotifyHistory(
                    JSFixedFastJsonLongIdKey.of(notifyHistory.getKey()),
                    JSFixedFastJsonLongIdKey.of(notifyHistory.getNotifySettingKey()),
                    notifyHistory.getHappenedDate(), notifyHistory.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "notify_setting_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey notifySettingKey;

    @JSONField(name = "happened_date", ordinal = 3)
    private Date happenedDate;

    @JSONField(name = "remark", ordinal = 4)
    private String remark;

    public JSFixedFastJsonNotifyHistory() {
    }

    public JSFixedFastJsonNotifyHistory(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey notifySettingKey, Date happenedDate, String remark
    ) {
        this.key = key;
        this.notifySettingKey = notifySettingKey;
        this.happenedDate = happenedDate;
        this.remark = remark;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public JSFixedFastJsonLongIdKey getNotifySettingKey() {
        return notifySettingKey;
    }

    public void setNotifySettingKey(JSFixedFastJsonLongIdKey notifySettingKey) {
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
        return "JSFixedFastJsonNotifyHistory{" +
                "key=" + key +
                ", notifySettingKey=" + notifySettingKey +
                ", happenedDate=" + happenedDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}

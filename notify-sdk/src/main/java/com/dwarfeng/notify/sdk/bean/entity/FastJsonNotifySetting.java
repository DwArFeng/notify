package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.entity.NotifySetting;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 通知设置。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonNotifySetting implements Bean {

    private static final long serialVersionUID = 4606041369318988629L;

    public static FastJsonNotifySetting of(NotifySetting notifySetting) {
        if (Objects.isNull(notifySetting)) {
            return null;
        } else {
            return new FastJsonNotifySetting(
                    FastJsonLongIdKey.of(notifySetting.getKey()),
                    notifySetting.getLabel(), notifySetting.isEnabled(), notifySetting.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "label", ordinal = 2)
    private String label;

    @JSONField(name = "enabled", ordinal = 3)
    private boolean enabled;

    @JSONField(name = "remark", ordinal = 4)
    private String remark;

    public FastJsonNotifySetting() {
    }

    public FastJsonNotifySetting(FastJsonLongIdKey key, String label, boolean enabled, String remark) {
        this.key = key;
        this.label = label;
        this.enabled = enabled;
        this.remark = remark;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonNotifySetting{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                '}';
    }
}

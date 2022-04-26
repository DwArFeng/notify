package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.entity.NotifySetting;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 通知设置。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonNotifySetting implements Bean {

    private static final long serialVersionUID = 7719890494391082860L;

    public static JSFixedFastJsonNotifySetting of(NotifySetting notifySetting) {
        if (Objects.isNull(notifySetting)) {
            return null;
        } else {
            return new JSFixedFastJsonNotifySetting(
                    JSFixedFastJsonLongIdKey.of(notifySetting.getKey()),
                    notifySetting.getLabel(), notifySetting.isEnabled(), notifySetting.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "label", ordinal = 2)
    private String label;

    @JSONField(name = "enabled", ordinal = 3)
    private boolean enabled;

    @JSONField(name = "remark", ordinal = 4)
    private String remark;

    public JSFixedFastJsonNotifySetting() {
    }

    public JSFixedFastJsonNotifySetting(JSFixedFastJsonLongIdKey key, String label, boolean enabled, String remark) {
        this.key = key;
        this.label = label;
        this.enabled = enabled;
        this.remark = remark;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
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
        return "JSFixedFastJsonNotifySetting{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                '}';
    }
}

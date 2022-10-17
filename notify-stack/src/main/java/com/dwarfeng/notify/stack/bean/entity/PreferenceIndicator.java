package com.dwarfeng.notify.stack.bean.entity;

import com.dwarfeng.notify.stack.bean.entity.key.PreferenceIndicatorKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

/**
 * 偏好指示器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class PreferenceIndicator implements Entity<PreferenceIndicatorKey> {

    private static final long serialVersionUID = 6383937805807341376L;

    private PreferenceIndicatorKey key;
    private String label;
    private String remark;
    private String defaultValue;

    public PreferenceIndicator() {
    }

    public PreferenceIndicator(PreferenceIndicatorKey key, String label, String remark, String defaultValue) {
        this.key = key;
        this.label = label;
        this.remark = remark;
        this.defaultValue = defaultValue;
    }

    @Override
    public PreferenceIndicatorKey getKey() {
        return key;
    }

    @Override
    public void setKey(PreferenceIndicatorKey key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public String toString() {
        return "PreferenceIndicator{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                '}';
    }
}

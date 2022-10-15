package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.entity.PreferenceIndicator;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 偏好指示器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class FastJsonPreferenceIndicator implements Bean {

    private static final long serialVersionUID = -1069058029699167413L;

    public static FastJsonPreferenceIndicator of(PreferenceIndicator preferenceIndicator) {
        if (Objects.isNull(preferenceIndicator)) {
            return null;
        } else {
            return new FastJsonPreferenceIndicator(
                    FastJsonStringIdKey.of(preferenceIndicator.getKey()),
                    preferenceIndicator.getLabel(), preferenceIndicator.getRemark(),
                    preferenceIndicator.getDefaultValue()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "label", ordinal = 2)
    private String label;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    @JSONField(name = "default_value", ordinal = 4)
    private String defaultValue;

    public FastJsonPreferenceIndicator() {
    }

    public FastJsonPreferenceIndicator(FastJsonStringIdKey key, String label, String remark, String defaultValue) {
        this.key = key;
        this.label = label;
        this.remark = remark;
        this.defaultValue = defaultValue;
    }

    public FastJsonStringIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonStringIdKey key) {
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
        return "FastJsonPreferenceIndicator{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                '}';
    }
}

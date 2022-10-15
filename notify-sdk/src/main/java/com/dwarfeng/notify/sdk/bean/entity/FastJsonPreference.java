package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.bean.entity.key.FastJsonPreferenceKey;
import com.dwarfeng.notify.stack.bean.entity.Preference;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 偏好。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class FastJsonPreference implements Bean {

    private static final long serialVersionUID = 4306571360096841117L;

    public static FastJsonPreference of(Preference preference) {
        if (Objects.isNull(preference)) {
            return null;
        } else {
            return new FastJsonPreference(
                    FastJsonPreferenceKey.of(preference.getKey()),
                    preference.getValue(), preference.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonPreferenceKey key;

    @JSONField(name = "value", ordinal = 2)
    private String value;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public FastJsonPreference() {
    }

    public FastJsonPreference(FastJsonPreferenceKey key, String value, String remark) {
        this.key = key;
        this.value = value;
        this.remark = remark;
    }

    public FastJsonPreferenceKey getKey() {
        return key;
    }

    public void setKey(FastJsonPreferenceKey key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonPreference{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

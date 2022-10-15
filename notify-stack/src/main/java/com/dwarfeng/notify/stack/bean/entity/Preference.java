package com.dwarfeng.notify.stack.bean.entity;

import com.dwarfeng.notify.stack.bean.entity.key.PreferenceKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

/**
 * 偏好。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class Preference implements Entity<PreferenceKey> {

    private static final long serialVersionUID = 1346918950596319177L;

    private PreferenceKey key;
    private String value;
    private String remark;

    public Preference() {
    }

    public Preference(PreferenceKey key, String value, String remark) {
        this.key = key;
        this.value = value;
        this.remark = remark;
    }

    @Override
    public PreferenceKey getKey() {
        return key;
    }

    @Override
    public void setKey(PreferenceKey key) {
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
        return "Preference{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

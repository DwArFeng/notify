package com.dwarfeng.notify.stack.bean.entity;

import com.dwarfeng.notify.stack.bean.key.MetaIndicatorKey;
import com.dwarfeng.subgrade.basic.stack.bean.entity.Entity;

import java.io.Serial;

/**
 * 元数据指示器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class MetaIndicator implements Entity<MetaIndicatorKey> {

    @Serial
    private static final long serialVersionUID = -3072083739453388999L;

    private MetaIndicatorKey key;
    private String label;
    private String remark;
    private String defaultValue;

    public MetaIndicator() {
    }

    public MetaIndicator(MetaIndicatorKey key, String label, String remark, String defaultValue) {
        this.key = key;
        this.label = label;
        this.remark = remark;
        this.defaultValue = defaultValue;
    }

    @Override
    public MetaIndicatorKey getKey() {
        return key;
    }

    @Override
    public void setKey(MetaIndicatorKey key) {
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
        return "MetaIndicator{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                '}';
    }
}

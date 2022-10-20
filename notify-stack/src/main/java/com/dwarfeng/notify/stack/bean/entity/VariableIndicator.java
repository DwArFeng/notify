package com.dwarfeng.notify.stack.bean.entity;

import com.dwarfeng.notify.stack.bean.entity.key.VariableIndicatorKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

/**
 * 变量指示器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class VariableIndicator implements Entity<VariableIndicatorKey> {

    private static final long serialVersionUID = -7071026527678002176L;
    
    private VariableIndicatorKey key;
    private String label;
    private String remark;
    private String defaultValue;

    public VariableIndicator() {
    }

    public VariableIndicator(VariableIndicatorKey key, String label, String remark, String defaultValue) {
        this.key = key;
        this.label = label;
        this.remark = remark;
        this.defaultValue = defaultValue;
    }

    @Override
    public VariableIndicatorKey getKey() {
        return key;
    }

    @Override
    public void setKey(VariableIndicatorKey key) {
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
        return "VariableIndicator{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                '}';
    }
}

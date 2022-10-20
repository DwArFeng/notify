package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.bean.entity.key.FastJsonVariableIndicatorKey;
import com.dwarfeng.notify.stack.bean.entity.VariableIndicator;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 变量指示器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class FastJsonVariableIndicator implements Bean {

    private static final long serialVersionUID = 2222096925460100300L;

    public static FastJsonVariableIndicator of(VariableIndicator variableIndicator) {
        if (Objects.isNull(variableIndicator)) {
            return null;
        } else {
            return new FastJsonVariableIndicator(
                    FastJsonVariableIndicatorKey.of(variableIndicator.getKey()),
                    variableIndicator.getLabel(), variableIndicator.getRemark(),
                    variableIndicator.getDefaultValue()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonVariableIndicatorKey key;

    @JSONField(name = "label", ordinal = 2)
    private String label;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    @JSONField(name = "default_value", ordinal = 4)
    private String defaultValue;

    public FastJsonVariableIndicator() {
    }

    public FastJsonVariableIndicator(
            FastJsonVariableIndicatorKey key, String label, String remark, String defaultValue
    ) {
        this.key = key;
        this.label = label;
        this.remark = remark;
        this.defaultValue = defaultValue;
    }

    public FastJsonVariableIndicatorKey getKey() {
        return key;
    }

    public void setKey(FastJsonVariableIndicatorKey key) {
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
        return "FastJsonVariableIndicator{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                '}';
    }
}

package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.bean.key.FastJsonMetaIndicatorKey;
import com.dwarfeng.notify.stack.bean.entity.MetaIndicator;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 元数据指示器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class FastJsonMetaIndicator implements Bean {

    private static final long serialVersionUID = -2976132205130036158L;

    public static FastJsonMetaIndicator of(MetaIndicator metaIndicator) {
        if (Objects.isNull(metaIndicator)) {
            return null;
        } else {
            return new FastJsonMetaIndicator(
                    FastJsonMetaIndicatorKey.of(metaIndicator.getKey()),
                    metaIndicator.getLabel(), metaIndicator.getRemark(),
                    metaIndicator.getDefaultValue()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonMetaIndicatorKey key;

    @JSONField(name = "label", ordinal = 2)
    private String label;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    @JSONField(name = "default_value", ordinal = 4)
    private String defaultValue;

    public FastJsonMetaIndicator() {
    }

    public FastJsonMetaIndicator(
            FastJsonMetaIndicatorKey key, String label, String remark, String defaultValue
    ) {
        this.key = key;
        this.label = label;
        this.remark = remark;
        this.defaultValue = defaultValue;
    }

    public FastJsonMetaIndicatorKey getKey() {
        return key;
    }

    public void setKey(FastJsonMetaIndicatorKey key) {
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
        return "FastJsonMetaIndicator{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                '}';
    }
}

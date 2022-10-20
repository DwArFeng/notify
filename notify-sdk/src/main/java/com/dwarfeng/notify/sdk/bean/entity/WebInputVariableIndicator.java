package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.bean.entity.key.WebInputVariableIndicatorKey;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.notify.stack.bean.entity.VariableIndicator;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * FastJson 变量指示器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class WebInputVariableIndicator implements Bean {

    private static final long serialVersionUID = -8235089215149613052L;

    public static VariableIndicator toStackBean(WebInputVariableIndicator webInputVariableIndicator) {
        if (Objects.isNull(webInputVariableIndicator)) {
            return null;
        } else {
            return new VariableIndicator(
                    WebInputVariableIndicatorKey.toStackBean(webInputVariableIndicator.getKey()),
                    webInputVariableIndicator.getLabel(), webInputVariableIndicator.getRemark(),
                    webInputVariableIndicator.getDefaultValue()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    @NotNull
    private WebInputVariableIndicatorKey key;

    @JSONField(name = "label")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_LABEL)
    private String label;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    @JSONField(name = "default_value")
    private String defaultValue;

    public WebInputVariableIndicator() {
    }

    public WebInputVariableIndicatorKey getKey() {
        return key;
    }

    public void setKey(WebInputVariableIndicatorKey key) {
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
        return "WebInputVariableIndicator{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                '}';
    }
}

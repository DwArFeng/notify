package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.bean.entity.key.WebInputPreferenceIndicatorKey;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.notify.stack.bean.entity.PreferenceIndicator;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * FastJson 偏好指示器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class WebInputPreferenceIndicator implements Bean {

    private static final long serialVersionUID = 7069576984601145104L;

    public static PreferenceIndicator toStackBean(WebInputPreferenceIndicator webInputPreferenceIndicator) {
        if (Objects.isNull(webInputPreferenceIndicator)) {
            return null;
        } else {
            return new PreferenceIndicator(
                    WebInputPreferenceIndicatorKey.toStackBean(webInputPreferenceIndicator.getKey()),
                    webInputPreferenceIndicator.getLabel(), webInputPreferenceIndicator.getRemark(),
                    webInputPreferenceIndicator.getDefaultValue()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    @NotNull
    private WebInputPreferenceIndicatorKey key;

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

    public WebInputPreferenceIndicator() {
    }

    public WebInputPreferenceIndicatorKey getKey() {
        return key;
    }

    public void setKey(WebInputPreferenceIndicatorKey key) {
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
        return "WebInputPreferenceIndicator{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                '}';
    }
}

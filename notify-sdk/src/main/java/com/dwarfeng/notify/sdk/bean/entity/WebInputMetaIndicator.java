package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.notify.sdk.bean.key.WebInputMetaIndicatorKey;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.notify.stack.bean.entity.MetaIndicator;
import com.dwarfeng.subgrade.basic.stack.bean.Bean;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.util.Objects;

/**
 * FastJson 元数据指示器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class WebInputMetaIndicator implements Bean {

    @Serial
    private static final long serialVersionUID = 4491561330257680983L;

    public static MetaIndicator toStackBean(WebInputMetaIndicator webInputMetaIndicator) {
        if (Objects.isNull(webInputMetaIndicator)) {
            return null;
        } else {
            return new MetaIndicator(
                    WebInputMetaIndicatorKey.toStackBean(webInputMetaIndicator.getKey()),
                    webInputMetaIndicator.getLabel(), webInputMetaIndicator.getRemark(),
                    webInputMetaIndicator.getDefaultValue()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    @NotNull
    private WebInputMetaIndicatorKey key;

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

    public WebInputMetaIndicator() {
    }

    public WebInputMetaIndicatorKey getKey() {
        return key;
    }

    public void setKey(WebInputMetaIndicatorKey key) {
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
        return "WebInputMetaIndicator{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                '}';
    }
}

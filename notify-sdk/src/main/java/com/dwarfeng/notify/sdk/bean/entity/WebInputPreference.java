package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.bean.entity.key.WebInputPreferenceKey;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.notify.stack.bean.entity.Preference;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * FastJson 偏好。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class WebInputPreference implements Bean {

    private static final long serialVersionUID = -5818932335612206557L;

    public static Preference toStackBean(WebInputPreference webInputPreference) {
        if (Objects.isNull(webInputPreference)) {
            return null;
        } else {
            return new Preference(
                    WebInputPreferenceKey.toStackBean(webInputPreference.getKey()),
                    webInputPreference.getValue(), webInputPreference.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    @NotNull
    private WebInputPreferenceKey key;

    @JSONField(name = "value")
    private String value;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputPreference() {
    }

    public WebInputPreferenceKey getKey() {
        return key;
    }

    public void setKey(WebInputPreferenceKey key) {
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
        return "WebInputPreference{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

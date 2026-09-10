package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.notify.stack.bean.entity.NotifySetting;
import com.dwarfeng.subgrade.basic.stack.bean.Bean;
import com.dwarfeng.subgrade.web.sdk.bean.key.WebInputLongIdKey;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.util.Objects;

/**
 * WebInput 通知设置。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputNotifySetting implements Bean {

    @Serial
    private static final long serialVersionUID = -504049421241807820L;

    public static NotifySetting toStackBean(WebInputNotifySetting webInputNotifySetting) {
        if (Objects.isNull(webInputNotifySetting)) {
            return null;
        } else {
            return new NotifySetting(
                    WebInputLongIdKey.toStackBean(webInputNotifySetting.getKey()),
                    webInputNotifySetting.getLabel(), webInputNotifySetting.getRemark(),
                    webInputNotifySetting.isEnabled()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputLongIdKey key;

    @JSONField(name = "label")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_LABEL)
    private String label;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    @JSONField(name = "enabled")
    private boolean enabled;

    public WebInputNotifySetting() {
    }

    public WebInputLongIdKey getKey() {
        return key;
    }

    public void setKey(WebInputLongIdKey key) {
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "WebInputNotifySetting{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}

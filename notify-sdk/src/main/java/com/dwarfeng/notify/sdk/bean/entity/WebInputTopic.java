package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.notify.stack.bean.entity.Topic;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 主题。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputTopic implements Bean {

    private static final long serialVersionUID = 2773264961331580703L;

    public static Topic toStackBean(WebInputTopic webInputTopic) {
        if (Objects.isNull(webInputTopic)) {
            return null;
        }
        return new Topic(
                WebInputStringIdKey.toStackBean(webInputTopic.getKey()),
                webInputTopic.getLabel(), webInputTopic.getRemark(), webInputTopic.isEnabled(),
                webInputTopic.getPriority()
        );
    }

    @JSONField(name = "key")
    @Valid
    @NotNull
    private WebInputStringIdKey key;

    @JSONField(name = "label")
    @Length(max = Constraints.LENGTH_LABEL)
    private String label;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    @JSONField(name = "enabled")
    private boolean enabled;

    @JSONField(name = "priority")
    private int priority;

    public WebInputTopic() {
    }

    public WebInputStringIdKey getKey() {
        return key;
    }

    public void setKey(WebInputStringIdKey key) {
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "WebInputTopic{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                ", enabled=" + enabled +
                ", priority=" + priority +
                '}';
    }
}

package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.entity.Topic;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 主题。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonTopic implements Bean {

    private static final long serialVersionUID = -2259138942051918903L;

    public static FastJsonTopic of(Topic topic) {
        if (Objects.isNull(topic)) {
            return null;
        }
        return new FastJsonTopic(
                FastJsonStringIdKey.of(topic.getKey()),
                topic.getLabel(), topic.getRemark(), topic.isEnabled(), topic.getPriority()
        );
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "label", ordinal = 2)
    private String label;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    @JSONField(name = "enabled", ordinal = 4)
    private boolean enabled;

    @JSONField(name = "priority", ordinal = 5)
    private int priority;

    public FastJsonTopic() {
    }

    public FastJsonTopic(FastJsonStringIdKey key, String label, String remark, boolean enabled, int priority) {
        this.key = key;
        this.label = label;
        this.remark = remark;
        this.enabled = enabled;
        this.priority = priority;
    }

    public FastJsonStringIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonStringIdKey key) {
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
        return "FastJsonTopic{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                ", enabled=" + enabled +
                ", priority=" + priority +
                '}';
    }
}

package com.dwarfeng.notify.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 主题。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class Topic implements Entity<StringIdKey> {

    private static final long serialVersionUID = 8089461632829965343L;
    
    private StringIdKey key;
    private String label;
    private String remark;

    /**
     * @since 1.0.5
     */
    private boolean enabled;

    /**
     * @since 1.0.5
     */
    private int priority;

    public Topic() {
    }

    public Topic(StringIdKey key, String label, String remark, boolean enabled, int priority) {
        this.key = key;
        this.label = label;
        this.remark = remark;
        this.enabled = enabled;
        this.priority = priority;
    }

    @Override
    public StringIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(StringIdKey key) {
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
        return "Topic{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                ", enabled=" + enabled +
                ", priority=" + priority +
                '}';
    }
}

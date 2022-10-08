package com.dwarfeng.notify.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 通知设置。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class NotifySetting implements Entity<LongIdKey> {

    private static final long serialVersionUID = 26748674208998290L;

    private LongIdKey key;
    private String label;
    private String remark;

    /**
     * @since 1.0.5
     */
    private boolean enabled;

    public NotifySetting() {
    }

    public NotifySetting(LongIdKey key, String label, String remark, boolean enabled) {
        this.key = key;
        this.label = label;
        this.remark = remark;
        this.enabled = enabled;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
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
        return "NotifySetting{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}

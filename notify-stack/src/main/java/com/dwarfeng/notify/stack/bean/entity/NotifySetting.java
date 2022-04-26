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

    private static final long serialVersionUID = -4715670540957204065L;

    private LongIdKey key;
    private String label;
    private boolean enabled;
    private String remark;

    public NotifySetting() {
    }

    public NotifySetting(LongIdKey key, String label, boolean enabled, String remark) {
        this.key = key;
        this.label = label;
        this.enabled = enabled;
        this.remark = remark;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "NotifySetting{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                '}';
    }
}

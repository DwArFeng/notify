package com.dwarfeng.notify.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 用户。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class User implements Entity<StringIdKey> {

    private static final long serialVersionUID = 6741466564036557711L;

    private StringIdKey key;
    private String remark;

    /**
     * @since 1.1.0
     */
    private boolean enabled;

    public User() {
    }

    public User(StringIdKey key, String remark, boolean enabled) {
        this.key = key;
        this.remark = remark;
        this.enabled = enabled;
    }

    @Override
    public StringIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(StringIdKey key) {
        this.key = key;
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
        return "User{" +
                "key=" + key +
                ", remark='" + remark + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}

package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.entity.User;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 用户。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonUser implements Bean {

    private static final long serialVersionUID = 7151598859289481185L;

    public static FastJsonUser of(User user) {
        if (Objects.isNull(user)) {
            return null;
        }
        return new FastJsonUser(
                FastJsonStringIdKey.of(user.getKey()),
                user.getRemark(), user.isEnabled()
        );
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "remark", ordinal = 2)
    private String remark;

    @JSONField(name = "enabled", ordinal = 3)
    private boolean enabled;

    public FastJsonUser() {
    }

    public FastJsonUser(FastJsonStringIdKey key, String remark, boolean enabled) {
        this.key = key;
        this.remark = remark;
        this.enabled = enabled;
    }

    public FastJsonStringIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonStringIdKey key) {
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
        return "FastJsonUser{" +
                "key=" + key +
                ", remark='" + remark + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}

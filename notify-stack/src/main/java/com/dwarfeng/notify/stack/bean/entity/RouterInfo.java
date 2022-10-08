package com.dwarfeng.notify.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 路由器信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class RouterInfo implements Entity<LongIdKey> {

    private static final long serialVersionUID = 2833378733467353522L;
    
    private LongIdKey key;
    private LongIdKey notifySettingKey;
    private String label;
    private String type;
    private String param;
    private String remark;

    /**
     * @since 1.0.5
     */
    private boolean enabled;

    public RouterInfo() {
    }

    public RouterInfo(
            LongIdKey key, LongIdKey notifySettingKey, String label, String type, String param, String remark,
            boolean enabled
    ) {
        this.key = key;
        this.notifySettingKey = notifySettingKey;
        this.label = label;
        this.type = type;
        this.param = param;
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

    public LongIdKey getNotifySettingKey() {
        return notifySettingKey;
    }

    public void setNotifySettingKey(LongIdKey notifySettingKey) {
        this.notifySettingKey = notifySettingKey;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
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
        return "RouterInfo{" +
                "key=" + key +
                ", notifySettingKey=" + notifySettingKey +
                ", label='" + label + '\'' +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}

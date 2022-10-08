package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.entity.RouterInfo;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 路由器信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonRouterInfo implements Bean {

    private static final long serialVersionUID = -57263079714917985L;

    public static FastJsonRouterInfo of(RouterInfo routerInfo) {
        if (Objects.isNull(routerInfo)) {
            return null;
        } else {
            return new FastJsonRouterInfo(
                    FastJsonLongIdKey.of(routerInfo.getKey()),
                    FastJsonLongIdKey.of(routerInfo.getNotifySettingKey()),
                    routerInfo.getLabel(), routerInfo.getType(), routerInfo.getParam(), routerInfo.getRemark(),
                    routerInfo.isEnabled()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "notify_setting_key", ordinal = 2)
    private FastJsonLongIdKey notifySettingKey;

    @JSONField(name = "label", ordinal = 3)
    private String label;

    @JSONField(name = "type", ordinal = 4)
    private String type;

    @JSONField(name = "param", ordinal = 5)
    private String param;

    @JSONField(name = "remark", ordinal = 6)
    private String remark;

    @JSONField(name = "enabled", ordinal = 7)
    private boolean enabled;

    public FastJsonRouterInfo() {
    }

    public FastJsonRouterInfo(
            FastJsonLongIdKey key, FastJsonLongIdKey notifySettingKey, String label, String type, String param,
            String remark, boolean enabled
    ) {
        this.key = key;
        this.notifySettingKey = notifySettingKey;
        this.label = label;
        this.type = type;
        this.param = param;
        this.remark = remark;
        this.enabled = enabled;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonLongIdKey getNotifySettingKey() {
        return notifySettingKey;
    }

    public void setNotifySettingKey(FastJsonLongIdKey notifySettingKey) {
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
        return "FastJsonRouterInfo{" +
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

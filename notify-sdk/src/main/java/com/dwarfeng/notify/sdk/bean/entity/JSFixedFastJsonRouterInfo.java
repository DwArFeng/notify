package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.entity.RouterInfo;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 路由器信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonRouterInfo implements Bean {

    private static final long serialVersionUID = 3157504625706211425L;

    public static JSFixedFastJsonRouterInfo of(RouterInfo routerInfo) {
        if (Objects.isNull(routerInfo)) {
            return null;
        } else {
            return new JSFixedFastJsonRouterInfo(
                    JSFixedFastJsonLongIdKey.of(routerInfo.getKey()),
                    JSFixedFastJsonLongIdKey.of(routerInfo.getNotifySettingKey()),
                    routerInfo.isEnabled(), routerInfo.getType(), routerInfo.getParam(), routerInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "notify_setting_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey notifySettingKey;

    @JSONField(name = "enabled", ordinal = 3)
    private boolean enabled;

    @JSONField(name = "type", ordinal = 4)
    private String type;

    @JSONField(name = "param", ordinal = 5)
    private String param;

    @JSONField(name = "remark", ordinal = 6)
    private String remark;

    public JSFixedFastJsonRouterInfo() {
    }

    public JSFixedFastJsonRouterInfo(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey notifySettingKey, boolean enabled, String type,
            String param, String remark
    ) {
        this.key = key;
        this.notifySettingKey = notifySettingKey;
        this.enabled = enabled;
        this.type = type;
        this.param = param;
        this.remark = remark;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public JSFixedFastJsonLongIdKey getNotifySettingKey() {
        return notifySettingKey;
    }

    public void setNotifySettingKey(JSFixedFastJsonLongIdKey notifySettingKey) {
        this.notifySettingKey = notifySettingKey;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    @Override
    public String toString() {
        return "JSFixedFastJsonRouterInfo{" +
                "key=" + key +
                ", notifySettingKey=" + notifySettingKey +
                ", enabled=" + enabled +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

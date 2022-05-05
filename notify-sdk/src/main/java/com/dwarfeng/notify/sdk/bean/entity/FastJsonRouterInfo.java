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

    private static final long serialVersionUID = -5584208591930194823L;

    public static FastJsonRouterInfo of(RouterInfo routerInfo) {
        if (Objects.isNull(routerInfo)) {
            return null;
        } else {
            return new FastJsonRouterInfo(
                    FastJsonLongIdKey.of(routerInfo.getKey()),
                    FastJsonLongIdKey.of(routerInfo.getNotifySettingKey()),
                    routerInfo.getType(), routerInfo.getParam(), routerInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "notify_setting_key", ordinal = 2)
    private FastJsonLongIdKey notifySettingKey;

    @JSONField(name = "type", ordinal = 3)
    private String type;

    @JSONField(name = "param", ordinal = 4)
    private String param;

    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    public FastJsonRouterInfo() {
    }

    public FastJsonRouterInfo(
            FastJsonLongIdKey key, FastJsonLongIdKey notifySettingKey, String type, String param, String remark
    ) {
        this.key = key;
        this.notifySettingKey = notifySettingKey;
        this.type = type;
        this.param = param;
        this.remark = remark;
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
        return "FastJsonRouterInfo{" +
                "key=" + key +
                ", notifySettingKey=" + notifySettingKey +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

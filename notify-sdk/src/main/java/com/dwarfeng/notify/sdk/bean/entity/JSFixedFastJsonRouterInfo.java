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

    private static final long serialVersionUID = -2931332337319017599L;

    public static JSFixedFastJsonRouterInfo of(RouterInfo routerInfo) {
        if (Objects.isNull(routerInfo)) {
            return null;
        } else {
            return new JSFixedFastJsonRouterInfo(
                    JSFixedFastJsonLongIdKey.of(routerInfo.getKey()),
                    routerInfo.getLabel(), routerInfo.getType(), routerInfo.getParam(), routerInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "label", ordinal = 2)
    private String label;

    @JSONField(name = "type", ordinal = 3)
    private String type;

    @JSONField(name = "param", ordinal = 4)
    private String param;

    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    public JSFixedFastJsonRouterInfo() {
    }

    public JSFixedFastJsonRouterInfo(
            JSFixedFastJsonLongIdKey key, String label, String type, String param, String remark
    ) {
        this.key = key;
        this.label = label;
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

    @Override
    public String toString() {
        return "JSFixedFastJsonRouterInfo{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.entity.DispatcherInfo;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 调度器信息。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class JSFixedFastJsonDispatcherInfo implements Bean {

    private static final long serialVersionUID = -7641318105320143794L;

    public static JSFixedFastJsonDispatcherInfo of(DispatcherInfo dispatcherInfo) {
        if (Objects.isNull(dispatcherInfo)) {
            return null;
        } else {
            return new JSFixedFastJsonDispatcherInfo(
                    JSFixedFastJsonLongIdKey.of(dispatcherInfo.getKey()),
                    dispatcherInfo.getLabel(), dispatcherInfo.getType(), dispatcherInfo.getParam(), dispatcherInfo.getRemark()
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

    public JSFixedFastJsonDispatcherInfo() {
    }

    public JSFixedFastJsonDispatcherInfo(
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
        return "JSFixedFastJsonDispatcherInfo{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

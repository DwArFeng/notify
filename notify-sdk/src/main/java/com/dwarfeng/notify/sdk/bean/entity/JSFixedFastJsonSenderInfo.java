package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.bean.entity.key.JSFixedFastJsonSenderInfoKey;
import com.dwarfeng.notify.stack.bean.entity.SenderInfo;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 发送器信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonSenderInfo implements Bean {

    private static final long serialVersionUID = 3782354829995688189L;

    public static JSFixedFastJsonSenderInfo of(SenderInfo senderInfo) {
        if (Objects.isNull(senderInfo)) {
            return null;
        } else {
            return new JSFixedFastJsonSenderInfo(
                    JSFixedFastJsonSenderInfoKey.of(senderInfo.getKey()),
                    senderInfo.getLabel(), senderInfo.getType(), senderInfo.getParam(), senderInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonSenderInfoKey key;

    @JSONField(name = "label", ordinal = 2)
    private String label;

    @JSONField(name = "type", ordinal = 3)
    private String type;

    @JSONField(name = "param", ordinal = 4)
    private String param;

    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    public JSFixedFastJsonSenderInfo() {
    }

    public JSFixedFastJsonSenderInfo(
            JSFixedFastJsonSenderInfoKey key, String label, String type, String param, String remark
    ) {
        this.key = key;
        this.label = label;
        this.type = type;
        this.param = param;
        this.remark = remark;
    }

    public JSFixedFastJsonSenderInfoKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonSenderInfoKey key) {
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
        return "JSFixedFastJsonSenderInfo{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

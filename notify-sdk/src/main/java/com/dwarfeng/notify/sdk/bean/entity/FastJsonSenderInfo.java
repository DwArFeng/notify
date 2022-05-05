package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.entity.SenderInfo;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 发送器信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonSenderInfo implements Bean {

    private static final long serialVersionUID = -5729171871615805666L;

    public static FastJsonSenderInfo of(SenderInfo senderInfo) {
        if (Objects.isNull(senderInfo)) {
            return null;
        } else {
            return new FastJsonSenderInfo(
                    FastJsonLongIdKey.of(senderInfo.getKey()),
                    senderInfo.getType(), senderInfo.getParam(), senderInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "type", ordinal = 2)
    private String type;

    @JSONField(name = "param", ordinal = 3)
    private String param;

    @JSONField(name = "remark", ordinal = 4)
    private String remark;

    public FastJsonSenderInfo() {
    }

    public FastJsonSenderInfo(FastJsonLongIdKey key, String type, String param, String remark) {
        this.key = key;
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
        return "FastJsonSenderInfo{" +
                "key=" + key +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.bean.entity.key.FastJsonSenderRelationKey;
import com.dwarfeng.notify.stack.bean.entity.SenderRelation;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 发送器关系。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonSenderRelation implements Bean {

    private static final long serialVersionUID = 8032024672328863110L;

    public static FastJsonSenderRelation of(SenderRelation senderRelation) {
        if (Objects.isNull(senderRelation)) {
            return null;
        } else {
            return new FastJsonSenderRelation(
                    FastJsonSenderRelationKey.of(senderRelation.getKey()),
                    FastJsonLongIdKey.of(senderRelation.getSenderInfoKey()),
                    senderRelation.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonSenderRelationKey key;

    @JSONField(name = "sender_info_key", ordinal = 2)
    private FastJsonLongIdKey senderInfoKey;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public FastJsonSenderRelation() {
    }

    public FastJsonSenderRelation(FastJsonSenderRelationKey key, FastJsonLongIdKey senderInfoKey, String remark) {
        this.key = key;
        this.senderInfoKey = senderInfoKey;
        this.remark = remark;
    }

    public FastJsonSenderRelationKey getKey() {
        return key;
    }

    public void setKey(FastJsonSenderRelationKey key) {
        this.key = key;
    }

    public FastJsonLongIdKey getSenderInfoKey() {
        return senderInfoKey;
    }

    public void setSenderInfoKey(FastJsonLongIdKey senderInfoKey) {
        this.senderInfoKey = senderInfoKey;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonSenderRelation{" +
                "key=" + key +
                ", senderInfoKey=" + senderInfoKey +
                ", remark='" + remark + '\'' +
                '}';
    }
}

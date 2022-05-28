package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.bean.entity.key.JSFixedFastJsonSenderRelationKey;
import com.dwarfeng.notify.stack.bean.entity.SenderRelation;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 发送器关系。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonSenderRelation implements Bean {

    private static final long serialVersionUID = -5686545643511724881L;

    public static JSFixedFastJsonSenderRelation of(SenderRelation senderRelation) {
        if (Objects.isNull(senderRelation)) {
            return null;
        } else {
            return new JSFixedFastJsonSenderRelation(
                    JSFixedFastJsonSenderRelationKey.of(senderRelation.getKey()),
                    JSFixedFastJsonLongIdKey.of(senderRelation.getSenderInfoKey()),
                    senderRelation.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonSenderRelationKey key;

    @JSONField(name = "sender_info_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey senderInfoKey;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public JSFixedFastJsonSenderRelation() {
    }

    public JSFixedFastJsonSenderRelation(
            JSFixedFastJsonSenderRelationKey key, JSFixedFastJsonLongIdKey senderInfoKey, String remark
    ) {
        this.key = key;
        this.senderInfoKey = senderInfoKey;
        this.remark = remark;
    }

    public JSFixedFastJsonSenderRelationKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonSenderRelationKey key) {
        this.key = key;
    }

    public JSFixedFastJsonLongIdKey getSenderInfoKey() {
        return senderInfoKey;
    }

    public void setSenderInfoKey(JSFixedFastJsonLongIdKey senderInfoKey) {
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
        return "JSFixedFastJsonSenderRelation{" +
                "key=" + key +
                ", senderInfoKey=" + senderInfoKey +
                ", remark='" + remark + '\'' +
                '}';
    }
}

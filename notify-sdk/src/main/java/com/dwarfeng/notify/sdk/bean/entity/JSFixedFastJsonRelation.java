package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.bean.entity.key.JSFixedFastJsonRelationKey;
import com.dwarfeng.notify.stack.bean.entity.Relation;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 关系。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonRelation implements Bean {

    private static final long serialVersionUID = 7524681367863176164L;

    public static JSFixedFastJsonRelation of(Relation relation) {
        if (Objects.isNull(relation)) {
            return null;
        } else {
            return new JSFixedFastJsonRelation(
                    JSFixedFastJsonRelationKey.of(relation.getKey()),
                    JSFixedFastJsonLongIdKey.of(relation.getSenderInfoKey()),
                    relation.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonRelationKey key;

    @JSONField(name = "sender_info_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey senderInfoKey;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public JSFixedFastJsonRelation() {
    }

    public JSFixedFastJsonRelation(
            JSFixedFastJsonRelationKey key, JSFixedFastJsonLongIdKey senderInfoKey, String remark
    ) {
        this.key = key;
        this.senderInfoKey = senderInfoKey;
        this.remark = remark;
    }

    public JSFixedFastJsonRelationKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonRelationKey key) {
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
        return "JSFixedFastJsonRelation{" +
                "key=" + key +
                ", senderInfoKey=" + senderInfoKey +
                ", remark='" + remark + '\'' +
                '}';
    }
}

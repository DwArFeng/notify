package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.bean.entity.key.FastJsonRelationKey;
import com.dwarfeng.notify.stack.bean.entity.Relation;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 关系。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class FastJsonRelation implements Bean {

    private static final long serialVersionUID = 1900620071997143194L;

    public static FastJsonRelation of(Relation relation) {
        if (Objects.isNull(relation)) {
            return null;
        } else {
            return new FastJsonRelation(
                    FastJsonRelationKey.of(relation.getKey()),
                    FastJsonLongIdKey.of(relation.getSenderInfoKey()),
                    FastJsonLongIdKey.of(relation.getDispatcherInfoKey()),
                    relation.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonRelationKey key;

    @JSONField(name = "sender_info_key", ordinal = 2)
    private FastJsonLongIdKey senderInfoKey;

    @JSONField(name = "dispatcher_info_key", ordinal = 3)
    private FastJsonLongIdKey dispatcherInfoKey;

    @JSONField(name = "remark", ordinal = 4)
    private String remark;

    public FastJsonRelation() {
    }

    public FastJsonRelation(
            FastJsonRelationKey key, FastJsonLongIdKey senderInfoKey, FastJsonLongIdKey dispatcherInfoKey,
            String remark
    ) {
        this.key = key;
        this.senderInfoKey = senderInfoKey;
        this.dispatcherInfoKey = dispatcherInfoKey;
        this.remark = remark;
    }

    public FastJsonRelationKey getKey() {
        return key;
    }

    public void setKey(FastJsonRelationKey key) {
        this.key = key;
    }

    public FastJsonLongIdKey getSenderInfoKey() {
        return senderInfoKey;
    }

    public void setSenderInfoKey(FastJsonLongIdKey senderInfoKey) {
        this.senderInfoKey = senderInfoKey;
    }

    public FastJsonLongIdKey getDispatcherInfoKey() {
        return dispatcherInfoKey;
    }

    public void setDispatcherInfoKey(FastJsonLongIdKey dispatcherInfoKey) {
        this.dispatcherInfoKey = dispatcherInfoKey;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonRelation{" +
                "key=" + key +
                ", senderInfoKey=" + senderInfoKey +
                ", dispatcherInfoKey=" + dispatcherInfoKey +
                ", remark='" + remark + '\'' +
                '}';
    }
}

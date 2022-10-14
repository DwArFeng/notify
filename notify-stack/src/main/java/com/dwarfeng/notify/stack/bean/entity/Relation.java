package com.dwarfeng.notify.stack.bean.entity;

import com.dwarfeng.notify.stack.bean.entity.key.RelationKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 关系。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class Relation implements Entity<RelationKey> {

    private static final long serialVersionUID = -1730519514515656753L;

    private RelationKey key;
    private LongIdKey senderInfoKey;
    private String remark;

    public Relation() {
    }

    public Relation(RelationKey key, LongIdKey senderInfoKey, String remark) {
        this.key = key;
        this.senderInfoKey = senderInfoKey;
        this.remark = remark;
    }

    @Override
    public RelationKey getKey() {
        return key;
    }

    @Override
    public void setKey(RelationKey key) {
        this.key = key;
    }

    public LongIdKey getSenderInfoKey() {
        return senderInfoKey;
    }

    public void setSenderInfoKey(LongIdKey senderInfoKey) {
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
        return "Relation{" +
                "key=" + key +
                ", senderInfoKey=" + senderInfoKey +
                ", remark='" + remark + '\'' +
                '}';
    }
}

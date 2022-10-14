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

    private static final long serialVersionUID = -1133724242038268249L;
    
    private RelationKey key;
    private LongIdKey senderInfoKey;
    private LongIdKey dispatcherInfoKey;
    private String remark;

    public Relation() {
    }

    public Relation(RelationKey key, LongIdKey senderInfoKey, LongIdKey dispatcherInfoKey, String remark) {
        this.key = key;
        this.senderInfoKey = senderInfoKey;
        this.dispatcherInfoKey = dispatcherInfoKey;
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

    public LongIdKey getDispatcherInfoKey() {
        return dispatcherInfoKey;
    }

    public void setDispatcherInfoKey(LongIdKey dispatcherInfoKey) {
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
        return "Relation{" +
                "key=" + key +
                ", senderInfoKey=" + senderInfoKey +
                ", dispatcherInfoKey=" + dispatcherInfoKey +
                ", remark='" + remark + '\'' +
                '}';
    }
}

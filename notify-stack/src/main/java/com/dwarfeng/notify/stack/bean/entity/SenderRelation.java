package com.dwarfeng.notify.stack.bean.entity;

import com.dwarfeng.notify.stack.bean.entity.key.SenderRelationKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 发送器关系。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class SenderRelation implements Entity<SenderRelationKey> {

    private static final long serialVersionUID = -1889462001218721327L;

    private SenderRelationKey key;
    private LongIdKey senderInfoKey;
    private String remark;

    public SenderRelation() {
    }

    public SenderRelation(SenderRelationKey key, LongIdKey senderInfoKey, String remark) {
        this.key = key;
        this.senderInfoKey = senderInfoKey;
        this.remark = remark;
    }

    @Override
    public SenderRelationKey getKey() {
        return key;
    }

    @Override
    public void setKey(SenderRelationKey key) {
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
        return "SenderRelation{" +
                "key=" + key +
                ", senderInfoKey=" + senderInfoKey +
                ", remark='" + remark + '\'' +
                '}';
    }
}

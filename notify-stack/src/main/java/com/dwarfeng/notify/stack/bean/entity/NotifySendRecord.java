package com.dwarfeng.notify.stack.bean.entity;

import com.dwarfeng.notify.stack.bean.key.NotifySendRecordKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

/**
 * 通知发送记录。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class NotifySendRecord implements Entity<NotifySendRecordKey> {

    private static final long serialVersionUID = -4519591340700222670L;
    
    private NotifySendRecordKey key;
    private Boolean succeedFlag;
    private String senderMessage;

    public NotifySendRecord() {
    }

    public NotifySendRecord(NotifySendRecordKey key, Boolean succeedFlag, String senderMessage) {
        this.key = key;
        this.succeedFlag = succeedFlag;
        this.senderMessage = senderMessage;
    }

    @Override
    public NotifySendRecordKey getKey() {
        return key;
    }

    @Override
    public void setKey(NotifySendRecordKey key) {
        this.key = key;
    }

    public Boolean getSucceedFlag() {
        return succeedFlag;
    }

    public void setSucceedFlag(Boolean succeedFlag) {
        this.succeedFlag = succeedFlag;
    }

    public String getSenderMessage() {
        return senderMessage;
    }

    public void setSenderMessage(String senderMessage) {
        this.senderMessage = senderMessage;
    }

    @Override
    public String toString() {
        return "NotifySendRecord{" +
                "key=" + key +
                ", succeedFlag=" + succeedFlag +
                ", senderMessage='" + senderMessage + '\'' +
                '}';
    }
}

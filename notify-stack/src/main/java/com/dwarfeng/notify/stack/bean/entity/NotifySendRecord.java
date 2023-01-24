package com.dwarfeng.notify.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 通知发送记录。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class NotifySendRecord implements Entity<LongIdKey> {

    private static final long serialVersionUID = -6462476543587770245L;

    private LongIdKey key;
    private LongIdKey notifyHistoryKey;
    private StringIdKey topicKey;
    private StringIdKey userKey;
    private Boolean succeedFlag;
    private String senderMessage;

    public NotifySendRecord() {
    }

    public NotifySendRecord(
            LongIdKey key, LongIdKey notifyHistoryKey, StringIdKey topicKey, StringIdKey userKey,
            Boolean succeedFlag, String senderMessage
    ) {
        this.key = key;
        this.notifyHistoryKey = notifyHistoryKey;
        this.topicKey = topicKey;
        this.userKey = userKey;
        this.succeedFlag = succeedFlag;
        this.senderMessage = senderMessage;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
    }

    public LongIdKey getNotifyHistoryKey() {
        return notifyHistoryKey;
    }

    public void setNotifyHistoryKey(LongIdKey notifyHistoryKey) {
        this.notifyHistoryKey = notifyHistoryKey;
    }

    public StringIdKey getTopicKey() {
        return topicKey;
    }

    public void setTopicKey(StringIdKey topicKey) {
        this.topicKey = topicKey;
    }

    public StringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(StringIdKey userKey) {
        this.userKey = userKey;
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
                ", notifyHistoryKey=" + notifyHistoryKey +
                ", topicKey=" + topicKey +
                ", userKey=" + userKey +
                ", succeedFlag=" + succeedFlag +
                ", senderMessage='" + senderMessage + '\'' +
                '}';
    }
}

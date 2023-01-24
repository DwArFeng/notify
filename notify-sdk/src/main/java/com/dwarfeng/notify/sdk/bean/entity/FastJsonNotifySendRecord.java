package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.entity.NotifySendRecord;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 通知发送记录。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class FastJsonNotifySendRecord implements Bean {

    private static final long serialVersionUID = 732250091193215232L;

    public static FastJsonNotifySendRecord of(NotifySendRecord notifySendRecord) {
        if (Objects.isNull(notifySendRecord)) {
            return null;
        } else {
            return new FastJsonNotifySendRecord(
                    FastJsonLongIdKey.of(notifySendRecord.getKey()),
                    FastJsonLongIdKey.of(notifySendRecord.getNotifyHistoryKey()),
                    FastJsonStringIdKey.of(notifySendRecord.getTopicKey()),
                    FastJsonStringIdKey.of(notifySendRecord.getUserKey()),
                    notifySendRecord.getSucceedFlag(), notifySendRecord.getSenderMessage()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "notify_history_key", ordinal = 2)
    private FastJsonLongIdKey notifyHistoryKey;

    @JSONField(name = "topic_key", ordinal = 3)
    private FastJsonStringIdKey topicKey;

    @JSONField(name = "user_key", ordinal = 4)
    private FastJsonStringIdKey userKey;

    @JSONField(name = "succeed_flag", ordinal = 6)
    private Boolean succeedFlag;

    @JSONField(name = "sender_message", ordinal = 7)
    private String senderMessage;

    public FastJsonNotifySendRecord() {
    }

    public FastJsonNotifySendRecord(
            FastJsonLongIdKey key, FastJsonLongIdKey notifyHistoryKey,
            FastJsonStringIdKey topicKey, FastJsonStringIdKey userKey,
            Boolean succeedFlag, String senderMessage
    ) {
        this.key = key;
        this.notifyHistoryKey = notifyHistoryKey;
        this.topicKey = topicKey;
        this.userKey = userKey;
        this.succeedFlag = succeedFlag;
        this.senderMessage = senderMessage;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonLongIdKey getNotifyHistoryKey() {
        return notifyHistoryKey;
    }

    public void setNotifyHistoryKey(FastJsonLongIdKey notifyHistoryKey) {
        this.notifyHistoryKey = notifyHistoryKey;
    }

    public FastJsonStringIdKey getTopicKey() {
        return topicKey;
    }

    public void setTopicKey(FastJsonStringIdKey topicKey) {
        this.topicKey = topicKey;
    }

    public FastJsonStringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(FastJsonStringIdKey userKey) {
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
        return "FastJsonNotifySendRecord{" +
                "key=" + key +
                ", notifyHistoryKey=" + notifyHistoryKey +
                ", topicKey=" + topicKey +
                ", userKey=" + userKey +
                ", succeedFlag=" + succeedFlag +
                ", senderMessage='" + senderMessage + '\'' +
                '}';
    }
}

package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.entity.NotifySendRecord;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 通知发送记录。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class JSFixedFastJsonNotifySendRecord implements Bean {

    private static final long serialVersionUID = -6325271870447798132L;

    public static JSFixedFastJsonNotifySendRecord of(NotifySendRecord notifySendRecord) {
        if (Objects.isNull(notifySendRecord)) {
            return null;
        } else {
            return new JSFixedFastJsonNotifySendRecord(
                    JSFixedFastJsonLongIdKey.of(notifySendRecord.getKey()),
                    JSFixedFastJsonLongIdKey.of(notifySendRecord.getNotifyHistoryKey()),
                    FastJsonStringIdKey.of(notifySendRecord.getTopicKey()),
                    FastJsonStringIdKey.of(notifySendRecord.getUserKey()),
                    notifySendRecord.getSucceedFlag(), notifySendRecord.getSenderMessage()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "notify_history_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey notifyHistoryKey;

    @JSONField(name = "topic_key", ordinal = 3)
    private FastJsonStringIdKey topicKey;

    @JSONField(name = "user_key", ordinal = 4)
    private FastJsonStringIdKey userKey;

    @JSONField(name = "succeed_flag", ordinal = 6)
    private Boolean succeedFlag;

    @JSONField(name = "sender_message", ordinal = 7)
    private String senderMessage;

    public JSFixedFastJsonNotifySendRecord() {
    }

    public JSFixedFastJsonNotifySendRecord(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey notifyHistoryKey,
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

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public JSFixedFastJsonLongIdKey getNotifyHistoryKey() {
        return notifyHistoryKey;
    }

    public void setNotifyHistoryKey(JSFixedFastJsonLongIdKey notifyHistoryKey) {
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
        return "JSFixedFastJsonNotifySendRecord{" +
                "key=" + key +
                ", notifyHistoryKey=" + notifyHistoryKey +
                ", topicKey=" + topicKey +
                ", userKey=" + userKey +
                ", succeedFlag=" + succeedFlag +
                ", senderMessage='" + senderMessage + '\'' +
                '}';
    }
}

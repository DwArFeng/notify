package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.bean.key.FastJsonNotifySendRecordKey;
import com.dwarfeng.notify.stack.bean.entity.NotifySendRecord;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 通知发送记录。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class FastJsonNotifySendRecord implements Bean {

    private static final long serialVersionUID = -328243401069539303L;

    public static FastJsonNotifySendRecord of(NotifySendRecord notifySendRecord) {
        if (Objects.isNull(notifySendRecord)) {
            return null;
        } else {
            return new FastJsonNotifySendRecord(
                    FastJsonNotifySendRecordKey.of(notifySendRecord.getKey()),
                    notifySendRecord.getSucceedFlag(), notifySendRecord.getSenderMessage()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonNotifySendRecordKey key;

    @JSONField(name = "succeed_flag", ordinal = 2)
    private Boolean succeedFlag;

    @JSONField(name = "sender_message", ordinal = 3)
    private String senderMessage;

    public FastJsonNotifySendRecord() {
    }

    public FastJsonNotifySendRecord(FastJsonNotifySendRecordKey key, Boolean succeedFlag, String senderMessage) {
        this.key = key;
        this.succeedFlag = succeedFlag;
        this.senderMessage = senderMessage;
    }

    public FastJsonNotifySendRecordKey getKey() {
        return key;
    }

    public void setKey(FastJsonNotifySendRecordKey key) {
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
}

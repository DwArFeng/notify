package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.bean.key.JSFixedFastJsonNotifySendRecordKey;
import com.dwarfeng.notify.stack.bean.entity.NotifySendRecord;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 通知发送记录。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class JSFixedFastJsonNotifySendRecord implements Bean {

    private static final long serialVersionUID = 9154307502968973288L;

    public static JSFixedFastJsonNotifySendRecord of(NotifySendRecord notifySendRecord) {
        if (Objects.isNull(notifySendRecord)) {
            return null;
        } else {
            return new JSFixedFastJsonNotifySendRecord(
                    JSFixedFastJsonNotifySendRecordKey.of(notifySendRecord.getKey()),
                    notifySendRecord.getSucceedFlag(), notifySendRecord.getSenderMessage()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonNotifySendRecordKey key;

    @JSONField(name = "succeed_flag", ordinal = 6)
    private Boolean succeedFlag;

    @JSONField(name = "sender_message", ordinal = 7)
    private String senderMessage;

    public JSFixedFastJsonNotifySendRecord() {
    }

    public JSFixedFastJsonNotifySendRecord(
            JSFixedFastJsonNotifySendRecordKey key, Boolean succeedFlag, String senderMessage
    ) {
        this.key = key;
        this.succeedFlag = succeedFlag;
        this.senderMessage = senderMessage;
    }

    public JSFixedFastJsonNotifySendRecordKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonNotifySendRecordKey key) {
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
        return "JSFixedFastJsonNotifySendRecord{" +
                "key=" + key +
                ", succeedFlag=" + succeedFlag +
                ", senderMessage='" + senderMessage + '\'' +
                '}';
    }
}

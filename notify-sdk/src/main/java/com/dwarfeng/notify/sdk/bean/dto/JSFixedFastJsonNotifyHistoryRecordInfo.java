package com.dwarfeng.notify.sdk.bean.dto;

import com.dwarfeng.notify.stack.bean.dto.NotifyHistoryRecordInfo;
import com.dwarfeng.notify.stack.bean.dto.NotifyHistoryRecordInfo.InfoRecord;
import com.dwarfeng.notify.stack.bean.dto.NotifyHistoryRecordInfo.SendRecord;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * JSFixed FastJson 通知记录。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class JSFixedFastJsonNotifyHistoryRecordInfo implements Dto {

    private static final long serialVersionUID = -3264210579109717227L;

    public static JSFixedFastJsonNotifyHistoryRecordInfo of(NotifyHistoryRecordInfo notifyHistoryRecordInfo) {
        if (Objects.isNull(notifyHistoryRecordInfo)) {
            return null;
        } else {
            return new JSFixedFastJsonNotifyHistoryRecordInfo(
                    JSFixedFastJsonLongIdKey.of(notifyHistoryRecordInfo.getKey()),
                    JSFixedFastJsonLongIdKey.of(notifyHistoryRecordInfo.getNotifySettingKey()),
                    notifyHistoryRecordInfo.getHappenedDate(), notifyHistoryRecordInfo.getRemark(),
                    notifyHistoryRecordInfo.getInfoRecords().stream().map(JSFixedFastJsonInfoRecord::of)
                            .collect(Collectors.toList()),
                    notifyHistoryRecordInfo.getSendRecords().stream().map(JSFixedFastJsonSendRecord::of)
                            .collect(Collectors.toList())
            );
        }
    }

    private JSFixedFastJsonLongIdKey key;
    private JSFixedFastJsonLongIdKey notifySettingKey;
    private Date happenedDate;
    private String remark;
    private List<JSFixedFastJsonInfoRecord> infoRecords;
    private List<JSFixedFastJsonSendRecord> sendRecords;

    public JSFixedFastJsonNotifyHistoryRecordInfo() {
    }

    public JSFixedFastJsonNotifyHistoryRecordInfo(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey notifySettingKey, Date happenedDate, String remark,
            List<JSFixedFastJsonInfoRecord> fastJsonInfoRecords, List<JSFixedFastJsonSendRecord> fastJsonSendRecords
    ) {
        this.key = key;
        this.notifySettingKey = notifySettingKey;
        this.happenedDate = happenedDate;
        this.remark = remark;
        this.infoRecords = fastJsonInfoRecords;
        this.sendRecords = fastJsonSendRecords;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public JSFixedFastJsonLongIdKey getNotifySettingKey() {
        return notifySettingKey;
    }

    public void setNotifySettingKey(JSFixedFastJsonLongIdKey notifySettingKey) {
        this.notifySettingKey = notifySettingKey;
    }

    public Date getHappenedDate() {
        return happenedDate;
    }

    public void setHappenedDate(Date happenedDate) {
        this.happenedDate = happenedDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<JSFixedFastJsonInfoRecord> getInfoRecords() {
        return infoRecords;
    }

    public void setInfoRecords(List<JSFixedFastJsonInfoRecord> infoRecords) {
        this.infoRecords = infoRecords;
    }

    public List<JSFixedFastJsonSendRecord> getSendRecords() {
        return sendRecords;
    }

    public void setSendRecords(List<JSFixedFastJsonSendRecord> sendRecords) {
        this.sendRecords = sendRecords;
    }

    @Override
    public String toString() {
        return "JSFixedFastJsonNotifyHistoryRecordInfo{" +
                "key=" + key +
                ", notifySettingKey=" + notifySettingKey +
                ", happenedDate=" + happenedDate +
                ", remark='" + remark + '\'' +
                ", infoRecords=" + infoRecords +
                ", sendRecords=" + sendRecords +
                '}';
    }

    public static class JSFixedFastJsonInfoRecord implements Dto {

        private static final long serialVersionUID = 6887637681202418700L;

        public static JSFixedFastJsonInfoRecord of(InfoRecord infoRecord) {
            if (Objects.isNull(infoRecord)) {
                return null;
            } else {
                return new JSFixedFastJsonInfoRecord(
                        infoRecord.getType(), infoRecord.getRecordId(), infoRecord.getValue()
                );
            }
        }

        private Integer type;
        private String recordId;
        private String value;

        public JSFixedFastJsonInfoRecord() {
        }

        public JSFixedFastJsonInfoRecord(Integer type, String recordId, String value) {
            this.type = type;
            this.recordId = recordId;
            this.value = value;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getRecordId() {
            return recordId;
        }

        public void setRecordId(String recordId) {
            this.recordId = recordId;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "JSFixedFastJsonInfoRecord{" +
                    "type=" + type +
                    ", recordId='" + recordId + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    public static class JSFixedFastJsonSendRecord implements Dto {

        private static final long serialVersionUID = 3209450650270713880L;

        public static JSFixedFastJsonSendRecord of(SendRecord sendRecord) {
            if (Objects.isNull(sendRecord)) {
                return null;
            } else {
                return new JSFixedFastJsonSendRecord(
                        FastJsonStringIdKey.of(sendRecord.getTopicKey()),
                        FastJsonStringIdKey.of(sendRecord.getUserKey()),
                        sendRecord.isSucceedFlag(), sendRecord.getSenderMessage()
                );
            }
        }

        private FastJsonStringIdKey topicKey;
        private FastJsonStringIdKey userKey;
        private boolean succeedFlag;
        private String senderMessage;

        public JSFixedFastJsonSendRecord() {
        }

        public JSFixedFastJsonSendRecord(
                FastJsonStringIdKey topicKey, FastJsonStringIdKey userKey, boolean succeedFlag, String senderMessage
        ) {
            this.topicKey = topicKey;
            this.userKey = userKey;
            this.succeedFlag = succeedFlag;
            this.senderMessage = senderMessage;
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

        public boolean isSucceedFlag() {
            return succeedFlag;
        }

        public void setSucceedFlag(boolean succeedFlag) {
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
            return "JSFixedFastJsonSendRecord{" +
                    "topicKey=" + topicKey +
                    ", userKey=" + userKey +
                    ", succeedFlag=" + succeedFlag +
                    ", senderMessage='" + senderMessage + '\'' +
                    '}';
        }
    }
}

package com.dwarfeng.notify.sdk.bean.dto;

import com.dwarfeng.notify.stack.bean.dto.NotifyHistoryRecordInfo;
import com.dwarfeng.notify.stack.bean.dto.NotifyHistoryRecordInfo.InfoRecord;
import com.dwarfeng.notify.stack.bean.dto.NotifyHistoryRecordInfo.SendRecord;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * FastJson 通知记录。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class FastJsonNotifyHistoryRecordInfo implements Dto {

    private static final long serialVersionUID = 7723575918616769783L;

    public static FastJsonNotifyHistoryRecordInfo of(NotifyHistoryRecordInfo notifyHistoryRecordInfo) {
        if (Objects.isNull(notifyHistoryRecordInfo)) {
            return null;
        } else {
            return new FastJsonNotifyHistoryRecordInfo(
                    FastJsonLongIdKey.of(notifyHistoryRecordInfo.getKey()),
                    FastJsonLongIdKey.of(notifyHistoryRecordInfo.getNotifySettingKey()),
                    notifyHistoryRecordInfo.getHappenedDate(), notifyHistoryRecordInfo.getRemark(),
                    Optional.ofNullable(notifyHistoryRecordInfo.getInfoRecords()).map(
                            f -> f.stream().map(FastJsonInfoRecord::of).collect(Collectors.toList())
                    ).orElse(null),
                    Optional.ofNullable(notifyHistoryRecordInfo.getSendRecords()).map(
                            f -> f.stream().map(FastJsonSendRecord::of).collect(Collectors.toList())
                    ).orElse(null)
            );
        }
    }

    private FastJsonLongIdKey key;
    private FastJsonLongIdKey notifySettingKey;
    private Date happenedDate;
    private String remark;
    private List<FastJsonInfoRecord> infoRecords;
    private List<FastJsonSendRecord> sendRecords;

    public FastJsonNotifyHistoryRecordInfo() {
    }

    public FastJsonNotifyHistoryRecordInfo(
            FastJsonLongIdKey key, FastJsonLongIdKey notifySettingKey, Date happenedDate, String remark,
            List<FastJsonInfoRecord> fastJsonInfoRecords, List<FastJsonSendRecord> fastJsonSendRecords
    ) {
        this.key = key;
        this.notifySettingKey = notifySettingKey;
        this.happenedDate = happenedDate;
        this.remark = remark;
        this.infoRecords = fastJsonInfoRecords;
        this.sendRecords = fastJsonSendRecords;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonLongIdKey getNotifySettingKey() {
        return notifySettingKey;
    }

    public void setNotifySettingKey(FastJsonLongIdKey notifySettingKey) {
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

    public List<FastJsonInfoRecord> getInfoRecords() {
        return infoRecords;
    }

    public void setInfoRecords(List<FastJsonInfoRecord> infoRecords) {
        this.infoRecords = infoRecords;
    }

    public List<FastJsonSendRecord> getSendRecords() {
        return sendRecords;
    }

    public void setSendRecords(List<FastJsonSendRecord> sendRecords) {
        this.sendRecords = sendRecords;
    }

    @Override
    public String toString() {
        return "FastJsonNotifyHistoryRecordInfo{" +
                "key=" + key +
                ", notifySettingKey=" + notifySettingKey +
                ", happenedDate=" + happenedDate +
                ", remark='" + remark + '\'' +
                ", infoRecords=" + infoRecords +
                ", sendRecords=" + sendRecords +
                '}';
    }

    public static class FastJsonInfoRecord implements Dto {

        private static final long serialVersionUID = -823916524032485519L;

        public static FastJsonInfoRecord of(InfoRecord infoRecord) {
            if (Objects.isNull(infoRecord)) {
                return null;
            } else {
                return new FastJsonInfoRecord(
                        infoRecord.getType(), infoRecord.getRecordId(), infoRecord.getValue()
                );
            }
        }

        private Integer type;
        private String recordId;
        private String value;

        public FastJsonInfoRecord() {
        }

        public FastJsonInfoRecord(Integer type, String recordId, String value) {
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
            return "FastJsonInfoRecord{" +
                    "type=" + type +
                    ", recordId='" + recordId + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    public static class FastJsonSendRecord implements Dto {

        private static final long serialVersionUID = -2763712276369938873L;

        public static FastJsonSendRecord of(SendRecord sendRecord) {
            if (Objects.isNull(sendRecord)) {
                return null;
            } else {
                return new FastJsonSendRecord(
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

        public FastJsonSendRecord() {
        }

        public FastJsonSendRecord(
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
            return "FastJsonSendRecord{" +
                    "topicKey=" + topicKey +
                    ", userKey=" + userKey +
                    ", succeedFlag=" + succeedFlag +
                    ", senderMessage='" + senderMessage + '\'' +
                    '}';
        }
    }
}

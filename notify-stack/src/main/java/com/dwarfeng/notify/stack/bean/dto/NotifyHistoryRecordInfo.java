package com.dwarfeng.notify.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

import java.util.Date;
import java.util.List;

/**
 * 通知记录。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class NotifyHistoryRecordInfo implements Dto {

    private static final long serialVersionUID = -7348932754670762323L;

    private LongIdKey key;
    private LongIdKey notifySettingKey;
    private Date happenedDate;
    private String remark;
    private List<InfoRecord> infoRecords;
    private List<SendRecord> sendRecords;

    public NotifyHistoryRecordInfo() {
    }

    public NotifyHistoryRecordInfo(
            LongIdKey key, LongIdKey notifySettingKey, Date happenedDate, String remark,
            List<InfoRecord> infoRecords, List<SendRecord> sendRecords
    ) {
        this.key = key;
        this.notifySettingKey = notifySettingKey;
        this.happenedDate = happenedDate;
        this.remark = remark;
        this.infoRecords = infoRecords;
        this.sendRecords = sendRecords;
    }

    public LongIdKey getKey() {
        return key;
    }

    public void setKey(LongIdKey key) {
        this.key = key;
    }

    public LongIdKey getNotifySettingKey() {
        return notifySettingKey;
    }

    public void setNotifySettingKey(LongIdKey notifySettingKey) {
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

    public List<InfoRecord> getInfoRecords() {
        return infoRecords;
    }

    public void setInfoRecords(List<InfoRecord> infoRecords) {
        this.infoRecords = infoRecords;
    }

    public List<SendRecord> getSendRecords() {
        return sendRecords;
    }

    public void setSendRecords(List<SendRecord> sendRecords) {
        this.sendRecords = sendRecords;
    }

    @Override
    public String toString() {
        return "NotifyHistoryRecordInfo{" +
                "key=" + key +
                ", notifySettingKey=" + notifySettingKey +
                ", happenedDate=" + happenedDate +
                ", remark='" + remark + '\'' +
                ", infoRecords=" + infoRecords +
                ", sendRecords=" + sendRecords +
                '}';
    }

    /**
     * 信息记录。
     *
     * @since 1.3.0
     */
    public static class InfoRecord implements Dto {

        private static final long serialVersionUID = -8237879824227381515L;

        private Integer type;
        private String recordId;
        private String value;

        public InfoRecord() {
        }

        public InfoRecord(Integer type, String recordId, String value) {
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
            return "InfoRecord{" +
                    "type=" + type +
                    ", recordId='" + recordId + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    /**
     * 发送记录。
     *
     * @since 1.3.0
     */
    public static class SendRecord implements Dto {

        private static final long serialVersionUID = -8554653160649335378L;
        
        private StringIdKey topicKey;
        private StringIdKey userKey;
        private boolean succeedFlag;
        private String senderMessage;

        public SendRecord() {
        }

        public SendRecord(StringIdKey topicKey, StringIdKey userKey, boolean succeedFlag, String senderMessage) {
            this.topicKey = topicKey;
            this.userKey = userKey;
            this.succeedFlag = succeedFlag;
            this.senderMessage = senderMessage;
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
            return "SendRecord{" +
                    "topicKey=" + topicKey +
                    ", userKey=" + userKey +
                    ", succeedFlag=" + succeedFlag +
                    ", senderMessage='" + senderMessage + '\'' +
                    '}';
        }
    }
}

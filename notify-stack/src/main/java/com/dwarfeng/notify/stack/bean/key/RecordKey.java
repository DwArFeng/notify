package com.dwarfeng.notify.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 记录主键。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class RecordKey implements Key {

    private static final long serialVersionUID = -6301508811092936068L;

    private Long notifyHistoryId;
    private Integer type;
    private String recordId;

    public RecordKey() {
    }

    public RecordKey(Long notifyHistoryId, Integer type, String recordId) {
        this.notifyHistoryId = notifyHistoryId;
        this.type = type;
        this.recordId = recordId;
    }

    public Long getNotifyHistoryId() {
        return notifyHistoryId;
    }

    public void setNotifyHistoryId(Long notifyHistoryId) {
        this.notifyHistoryId = notifyHistoryId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecordKey recordKey = (RecordKey) o;

        if (!Objects.equals(notifyHistoryId, recordKey.notifyHistoryId))
            return false;
        if (!Objects.equals(type, recordKey.type)) return false;
        return Objects.equals(recordId, recordKey.recordId);
    }

    @Override
    public int hashCode() {
        int result = notifyHistoryId != null ? notifyHistoryId.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (recordId != null ? recordId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RecordKey{" +
                "notifyHistoryId=" + notifyHistoryId +
                ", type=" + type +
                ", recordId='" + recordId + '\'' +
                '}';
    }
}

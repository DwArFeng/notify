package com.dwarfeng.notify.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.key.RecordKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * FastJson 记录主键。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class FastJsonRecordKey implements Key {

    private static final long serialVersionUID = 3862848727644188243L;

    public static FastJsonRecordKey of(RecordKey recordKey) {
        if (Objects.isNull(recordKey)) {
            return null;
        } else {
            return new FastJsonRecordKey(
                    recordKey.getNotifyHistoryId(), recordKey.getType(), recordKey.getRecordId()
            );
        }
    }

    @JSONField(name = "notify_history_id", ordinal = 1)
    private Long notifyHistoryId;

    @JSONField(name = "type", ordinal = 2)
    private Integer type;

    @JSONField(name = "record_id", ordinal = 3)
    private String recordId;

    public FastJsonRecordKey() {
    }

    public FastJsonRecordKey(Long notifyHistoryId, Integer type, String recordId) {
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

        FastJsonRecordKey that = (FastJsonRecordKey) o;

        if (!Objects.equals(notifyHistoryId, that.notifyHistoryId))
            return false;
        if (!Objects.equals(type, that.type)) return false;
        return Objects.equals(recordId, that.recordId);
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
        return "FastJsonRecordKey{" +
                "notifyHistoryId=" + notifyHistoryId +
                ", type=" + type +
                ", recordId='" + recordId + '\'' +
                '}';
    }
}

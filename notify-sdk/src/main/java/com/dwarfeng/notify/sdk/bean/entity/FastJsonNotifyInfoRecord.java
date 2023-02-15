package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.bean.key.FastJsonNotifyInfoRecordKey;
import com.dwarfeng.notify.stack.bean.entity.NotifyInfoRecord;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 通知信息记录。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class FastJsonNotifyInfoRecord implements Bean {

    private static final long serialVersionUID = 4711154051703858930L;

    public static FastJsonNotifyInfoRecord of(NotifyInfoRecord notifyInfoRecord) {
        if (Objects.isNull(notifyInfoRecord)) {
            return null;
        } else {
            return new FastJsonNotifyInfoRecord(
                    FastJsonNotifyInfoRecordKey.of(notifyInfoRecord.getKey()),
                    notifyInfoRecord.getValue()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonNotifyInfoRecordKey key;

    @JSONField(name = "value", ordinal = 2)
    private String value;

    public FastJsonNotifyInfoRecord() {
    }

    public FastJsonNotifyInfoRecord(FastJsonNotifyInfoRecordKey key, String value) {
        this.key = key;
        this.value = value;
    }

    public FastJsonNotifyInfoRecordKey getKey() {
        return key;
    }

    public void setKey(FastJsonNotifyInfoRecordKey key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "FastJsonNotifyInfoRecord{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }
}

package com.dwarfeng.notify.stack.bean.entity;

import com.dwarfeng.notify.stack.bean.key.RecordKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

/**
 * 通知信息记录。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class NotifyInfoRecord implements Entity<RecordKey> {

    private static final long serialVersionUID = 7745773740215488481L;

    private RecordKey key;
    private String value;

    public NotifyInfoRecord() {
    }

    public NotifyInfoRecord(RecordKey key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public RecordKey getKey() {
        return key;
    }

    @Override
    public void setKey(RecordKey key) {
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
        return "NotifyInfoRecord{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }
}

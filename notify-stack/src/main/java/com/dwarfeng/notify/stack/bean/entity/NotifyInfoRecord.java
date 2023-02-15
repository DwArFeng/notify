package com.dwarfeng.notify.stack.bean.entity;

import com.dwarfeng.notify.stack.bean.key.NotifyInfoRecordKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

/**
 * 通知信息记录。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class NotifyInfoRecord implements Entity<NotifyInfoRecordKey> {

    private static final long serialVersionUID = 7745773740215488481L;

    private NotifyInfoRecordKey key;
    private String value;

    public NotifyInfoRecord() {
    }

    public NotifyInfoRecord(NotifyInfoRecordKey key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public NotifyInfoRecordKey getKey() {
        return key;
    }

    @Override
    public void setKey(NotifyInfoRecordKey key) {
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

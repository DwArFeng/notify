package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.bean.key.JSFixedFastJsonNotifyInfoRecordKey;
import com.dwarfeng.notify.stack.bean.entity.NotifyInfoRecord;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 通知信息记录。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class JSFixedFastJsonNotifyInfoRecord implements Bean {

    private static final long serialVersionUID = -996152611281758945L;

    public static JSFixedFastJsonNotifyInfoRecord of(NotifyInfoRecord notifyInfoRecord) {
        if (Objects.isNull(notifyInfoRecord)) {
            return null;
        } else {
            return new JSFixedFastJsonNotifyInfoRecord(
                    JSFixedFastJsonNotifyInfoRecordKey.of(notifyInfoRecord.getKey()),
                    notifyInfoRecord.getValue()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonNotifyInfoRecordKey key;

    @JSONField(name = "value", ordinal = 2)
    private String value;

    public JSFixedFastJsonNotifyInfoRecord() {
    }

    public JSFixedFastJsonNotifyInfoRecord(JSFixedFastJsonNotifyInfoRecordKey key, String value) {
        this.key = key;
        this.value = value;
    }

    public JSFixedFastJsonNotifyInfoRecordKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonNotifyInfoRecordKey key) {
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
        return "JSFixedFastJsonNotifyInfoRecord{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }
}

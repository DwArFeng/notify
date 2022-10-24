package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.bean.entity.key.FastJsonMetaKey;
import com.dwarfeng.notify.stack.bean.entity.Meta;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 元数据。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class FastJsonMeta implements Bean {

    private static final long serialVersionUID = 1073618335683884193L;

    public static FastJsonMeta of(Meta meta) {
        if (Objects.isNull(meta)) {
            return null;
        } else {
            return new FastJsonMeta(
                    FastJsonMetaKey.of(meta.getKey()),
                    meta.getValue(), meta.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonMetaKey key;

    @JSONField(name = "value", ordinal = 2)
    private String value;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public FastJsonMeta() {
    }

    public FastJsonMeta(FastJsonMetaKey key, String value, String remark) {
        this.key = key;
        this.value = value;
        this.remark = remark;
    }

    public FastJsonMetaKey getKey() {
        return key;
    }

    public void setKey(FastJsonMetaKey key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonMeta{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

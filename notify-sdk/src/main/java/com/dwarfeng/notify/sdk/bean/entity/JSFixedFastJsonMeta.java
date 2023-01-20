package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.bean.key.JSFixedFastJsonMetaKey;
import com.dwarfeng.notify.stack.bean.entity.Meta;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 元数据。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class JSFixedFastJsonMeta implements Bean {

    private static final long serialVersionUID = -3273497524082325426L;

    public static JSFixedFastJsonMeta of(Meta meta) {
        if (Objects.isNull(meta)) {
            return null;
        } else {
            return new JSFixedFastJsonMeta(
                    JSFixedFastJsonMetaKey.of(meta.getKey()),
                    meta.getValue(), meta.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonMetaKey key;

    @JSONField(name = "value", ordinal = 2)
    private String value;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public JSFixedFastJsonMeta() {
    }

    public JSFixedFastJsonMeta(JSFixedFastJsonMetaKey key, String value, String remark) {
        this.key = key;
        this.value = value;
        this.remark = remark;
    }

    public JSFixedFastJsonMetaKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonMetaKey key) {
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
        return "JSFixedFastJsonMeta{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

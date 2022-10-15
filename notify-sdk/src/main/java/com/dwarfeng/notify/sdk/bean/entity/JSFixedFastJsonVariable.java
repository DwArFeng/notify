package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.bean.entity.key.JSFixedFastJsonVariableKey;
import com.dwarfeng.notify.stack.bean.entity.Variable;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 变量。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class JSFixedFastJsonVariable implements Bean {

    private static final long serialVersionUID = -6349477832176709570L;

    public static JSFixedFastJsonVariable of(Variable variable) {
        if (Objects.isNull(variable)) {
            return null;
        } else {
            return new JSFixedFastJsonVariable(
                    JSFixedFastJsonVariableKey.of(variable.getKey()),
                    variable.getValue(), variable.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonVariableKey key;

    @JSONField(name = "value", ordinal = 2)
    private String value;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public JSFixedFastJsonVariable() {
    }

    public JSFixedFastJsonVariable(JSFixedFastJsonVariableKey key, String value, String remark) {
        this.key = key;
        this.value = value;
        this.remark = remark;
    }

    public JSFixedFastJsonVariableKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonVariableKey key) {
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
        return "JSFixedFastJsonVariable{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.bean.entity.key.WebInputVariableKey;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.notify.stack.bean.entity.Variable;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * FastJson 变量。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class WebInputVariable implements Bean {

    private static final long serialVersionUID = -4003456492412019831L;

    public static Variable toStackBean(WebInputVariable webInputVariable) {
        if (Objects.isNull(webInputVariable)) {
            return null;
        } else {
            return new Variable(
                    WebInputVariableKey.toStackBean(webInputVariable.getKey()),
                    webInputVariable.getValue(), webInputVariable.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    @NotNull
    private WebInputVariableKey key;

    @JSONField(name = "value")
    private String value;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputVariable() {
    }

    public WebInputVariableKey getKey() {
        return key;
    }

    public void setKey(WebInputVariableKey key) {
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
        return "WebInputVariable{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

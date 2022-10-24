package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.bean.entity.key.WebInputMetaKey;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.notify.stack.bean.entity.Meta;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * FastJson 元数据。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class WebInputMeta implements Bean {

    private static final long serialVersionUID = -7829513707284331007L;

    public static Meta toStackBean(WebInputMeta webInputMeta) {
        if (Objects.isNull(webInputMeta)) {
            return null;
        } else {
            return new Meta(
                    WebInputMetaKey.toStackBean(webInputMeta.getKey()),
                    webInputMeta.getValue(), webInputMeta.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    @NotNull
    private WebInputMetaKey key;

    @JSONField(name = "value")
    private String value;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputMeta() {
    }

    public WebInputMetaKey getKey() {
        return key;
    }

    public void setKey(WebInputMetaKey key) {
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
        return "WebInputMeta{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

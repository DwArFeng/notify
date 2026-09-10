package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.notify.sdk.bean.key.WebInputSenderInfoKey;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.notify.stack.bean.entity.SenderInfo;
import com.dwarfeng.subgrade.basic.stack.bean.Bean;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.util.Objects;

/**
 * WebInput 发送器信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputSenderInfo implements Bean {

    @Serial
    private static final long serialVersionUID = 5666569591929038343L;

    public static SenderInfo toStackBean(WebInputSenderInfo webInputSenderInfo) {
        if (Objects.isNull(webInputSenderInfo)) {
            return null;
        } else {
            return new SenderInfo(
                    WebInputSenderInfoKey.toStackBean(webInputSenderInfo.getKey()),
                    webInputSenderInfo.getLabel(), webInputSenderInfo.getType(), webInputSenderInfo.getParam(),
                    webInputSenderInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    @NotNull(groups = Default.class)
    private WebInputSenderInfoKey key;

    @JSONField(name = "label")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_LABEL)
    private String label;

    @JSONField(name = "type")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_TYPE)
    private String type;

    @JSONField(name = "param")
    private String param;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputSenderInfo() {
    }

    public WebInputSenderInfoKey getKey() {
        return key;
    }

    public void setKey(WebInputSenderInfoKey key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WebInputSenderInfo{" +
                "key=" + key +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

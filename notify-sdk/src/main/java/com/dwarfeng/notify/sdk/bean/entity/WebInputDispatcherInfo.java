package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.notify.stack.bean.entity.DispatcherInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.Objects;

/**
 * WebInput 调度器信息。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class WebInputDispatcherInfo implements Bean {

    private static final long serialVersionUID = -7830323916988267771L;

    public static DispatcherInfo toStackBean(WebInputDispatcherInfo webInputDispatcherInfo) {
        if (Objects.isNull(webInputDispatcherInfo)) {
            return null;
        } else {
            return new DispatcherInfo(
                    WebInputStringIdKey.toStackBean(webInputDispatcherInfo.getKey()),
                    webInputDispatcherInfo.getLabel(), webInputDispatcherInfo.getType(),
                    webInputDispatcherInfo.getParam(), webInputDispatcherInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    @NotNull(groups = Default.class)
    private WebInputStringIdKey key;

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

    public WebInputDispatcherInfo() {
    }

    public WebInputStringIdKey getKey() {
        return key;
    }

    public void setKey(WebInputStringIdKey key) {
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
        return "WebInputDispatcherInfo{" +
                "key=" + key +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

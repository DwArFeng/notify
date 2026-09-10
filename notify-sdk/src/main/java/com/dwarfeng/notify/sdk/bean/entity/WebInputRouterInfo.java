package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.notify.stack.bean.entity.RouterInfo;
import com.dwarfeng.subgrade.basic.stack.bean.Bean;
import com.dwarfeng.subgrade.web.sdk.bean.key.WebInputLongIdKey;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.util.Objects;

/**
 * WebInput 判断器信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputRouterInfo implements Bean {

    @Serial
    private static final long serialVersionUID = -4482379728795446499L;

    public static RouterInfo toStackBean(WebInputRouterInfo webInputRouterInfo) {
        if (Objects.isNull(webInputRouterInfo)) {
            return null;
        } else {
            return new RouterInfo(
                    WebInputLongIdKey.toStackBean(webInputRouterInfo.getKey()),
                    webInputRouterInfo.getLabel(), webInputRouterInfo.getType(), webInputRouterInfo.getParam(),
                    webInputRouterInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    @NotNull(groups = Default.class)
    private WebInputLongIdKey key;

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

    public WebInputRouterInfo() {
    }

    public WebInputLongIdKey getKey() {
        return key;
    }

    public void setKey(WebInputLongIdKey key) {
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
        return "WebInputRouterInfo{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

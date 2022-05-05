package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.notify.stack.bean.entity.RouterInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.Objects;

/**
 * WebInput 判断器信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputRouterInfo implements Bean {

    private static final long serialVersionUID = -3877303833017166569L;

    public static RouterInfo toStackBean(WebInputRouterInfo webInputRouterInfo) {
        if (Objects.isNull(webInputRouterInfo)) {
            return null;
        } else {
            return new RouterInfo(
                    WebInputLongIdKey.toStackBean(webInputRouterInfo.getKey()),
                    WebInputLongIdKey.toStackBean(webInputRouterInfo.getNotifySettingKey()),
                    webInputRouterInfo.getType(), webInputRouterInfo.getParam(),
                    webInputRouterInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    @NotNull(groups = Default.class)
    private WebInputLongIdKey key;

    @JSONField(name = "notify_setting_key")
    @Valid
    private WebInputLongIdKey notifySettingKey;

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

    public WebInputLongIdKey getNotifySettingKey() {
        return notifySettingKey;
    }

    public void setNotifySettingKey(WebInputLongIdKey notifySettingKey) {
        this.notifySettingKey = notifySettingKey;
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
                ", notifySettingKey=" + notifySettingKey +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

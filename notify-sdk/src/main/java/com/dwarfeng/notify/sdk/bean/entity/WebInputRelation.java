package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.bean.entity.key.WebInputRelationKey;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.notify.stack.bean.entity.Relation;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import java.util.Objects;

/**
 * WebInput 关系。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class WebInputRelation implements Bean {

    private static final long serialVersionUID = -4057175559033366193L;

    public static Relation toStackBean(WebInputRelation webInputRelation) {
        if (Objects.isNull(webInputRelation)) {
            return null;
        } else {
            return new Relation(
                    WebInputRelationKey.toStackBean(webInputRelation.getKey()),
                    WebInputLongIdKey.toStackBean(webInputRelation.getSenderInfoKey()),
                    webInputRelation.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputRelationKey key;

    @JSONField(name = "sender_info_key")
    @Valid
    private WebInputLongIdKey senderInfoKey;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputRelation() {
    }

    public WebInputRelationKey getKey() {
        return key;
    }

    public void setKey(WebInputRelationKey key) {
        this.key = key;
    }

    public WebInputLongIdKey getSenderInfoKey() {
        return senderInfoKey;
    }

    public void setSenderInfoKey(WebInputLongIdKey senderInfoKey) {
        this.senderInfoKey = senderInfoKey;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WebInputRelation{" +
                "key=" + key +
                ", senderInfoKey=" + senderInfoKey +
                ", remark='" + remark + '\'' +
                '}';
    }
}

package com.dwarfeng.notify.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.bean.entity.key.WebInputSenderRelationKey;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.notify.stack.bean.entity.SenderRelation;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import java.util.Objects;

/**
 * WebInput 发送器关系。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputSenderRelation implements Bean {

    private static final long serialVersionUID = -7063583107543070143L;

    public static SenderRelation toStackBean(WebInputSenderRelation webInputSenderRelation) {
        if (Objects.isNull(webInputSenderRelation)) {
            return null;
        } else {
            return new SenderRelation(
                    WebInputSenderRelationKey.toStackBean(webInputSenderRelation.getKey()),
                    WebInputLongIdKey.toStackBean(webInputSenderRelation.getSenderInfoKey()),
                    webInputSenderRelation.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputSenderRelationKey key;

    @JSONField(name = "sender_info_key")
    @Valid
    private WebInputLongIdKey senderInfoKey;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputSenderRelation() {
    }

    public WebInputSenderRelationKey getKey() {
        return key;
    }

    public void setKey(WebInputSenderRelationKey key) {
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
        return "WebInputSenderRelation{" +
                "key=" + key +
                ", senderInfoKey=" + senderInfoKey +
                ", remark='" + remark + '\'' +
                '}';
    }
}

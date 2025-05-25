package com.dwarfeng.notify.impl.bean.entity;

import com.dwarfeng.datamark.bean.jpa.DatamarkEntityListener;
import com.dwarfeng.datamark.bean.jpa.DatamarkField;
import com.dwarfeng.notify.impl.bean.key.HibernateSenderInfoKey;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(HibernateSenderInfoKey.class)
@Table(name = "tbl_sender_info")
@EntityListeners(DatamarkEntityListener.class)
public class HibernateSenderInfo implements Bean {

    private static final long serialVersionUID = 6717479113778427998L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "notify_setting_id", nullable = false)
    private Long notifySettingId;

    @Id
    @Column(name = "topic_id", length = Constraints.LENGTH_ID, nullable = false)
    private String topicId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "label", length = Constraints.LENGTH_LABEL)
    private String label;

    @Column(name = "type", length = Constraints.LENGTH_TYPE)
    private String type;

    @Column(name = "param", columnDefinition = "TEXT")
    private String param;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateNotifySetting.class)
    @JoinColumns({ //
            @JoinColumn(name = "notify_setting_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateNotifySetting notifySetting;

    @ManyToOne(targetEntity = HibernateTopic.class)
    @JoinColumns({ //
            @JoinColumn(name = "topic_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateTopic topic;

    // -----------------------------------------------------------审计-----------------------------------------------------------
    @DatamarkField(handlerName = "senderDatamarkHandler")
    @Column(
            name = "created_datamark",
            length = com.dwarfeng.datamark.util.Constraints.LENGTH_DATAMARK_VALUE,
            updatable = false
    )
    private String createdDatamark;

    @DatamarkField(handlerName = "senderDatamarkHandler")
    @Column(
            name = "modified_datamark",
            length = com.dwarfeng.datamark.util.Constraints.LENGTH_DATAMARK_VALUE
    )
    private String modifiedDatamark;

    public HibernateSenderInfo() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateSenderInfoKey getKey() {
        return new HibernateSenderInfoKey(notifySettingId, topicId);
    }

    public void setKey(HibernateSenderInfoKey key) {
        if (Objects.isNull(key)) {
            this.notifySettingId = null;
            this.topicId = null;
        } else {
            this.notifySettingId = key.getNotifySettingId();
            this.topicId = key.getTopicId();
        }
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getNotifySettingId() {
        return notifySettingId;
    }

    public void setNotifySettingId(Long notifySettingId) {
        this.notifySettingId = notifySettingId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
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

    public HibernateNotifySetting getNotifySetting() {
        return notifySetting;
    }

    public void setNotifySetting(HibernateNotifySetting notifySetting) {
        this.notifySetting = notifySetting;
    }

    public HibernateTopic getTopic() {
        return topic;
    }

    public void setTopic(HibernateTopic topic) {
        this.topic = topic;
    }

    public String getCreatedDatamark() {
        return createdDatamark;
    }

    public void setCreatedDatamark(String createdDatamark) {
        this.createdDatamark = createdDatamark;
    }

    public String getModifiedDatamark() {
        return modifiedDatamark;
    }

    public void setModifiedDatamark(String modifiedDatamark) {
        this.modifiedDatamark = modifiedDatamark;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "notifySettingId = " + notifySettingId + ", " +
                "topicId = " + topicId + ", " +
                "label = " + label + ", " +
                "type = " + type + ", " +
                "param = " + param + ", " +
                "remark = " + remark + ", " +
                "notifySetting = " + notifySetting + ", " +
                "topic = " + topic + ", " +
                "createdDatamark = " + createdDatamark + ", " +
                "modifiedDatamark = " + modifiedDatamark + ")";
    }
}

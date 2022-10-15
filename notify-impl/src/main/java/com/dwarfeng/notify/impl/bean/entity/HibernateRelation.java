package com.dwarfeng.notify.impl.bean.entity;

import com.dwarfeng.notify.impl.bean.entity.key.HibernateRelationKey;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Objects;
import java.util.Optional;

@Entity
@IdClass(HibernateRelationKey.class)
@Table(name = "tbl_relation")
public class HibernateRelation implements Bean {

    private static final long serialVersionUID = 516055792781958534L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "notify_setting_id", nullable = false)
    private Long notifySettingId;

    @Id
    @Column(name = "topic_id", length = Constraints.LENGTH_ID, nullable = false)
    private String topicId;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "sender_info_id")
    private Long senderInfoLongId;

    @Column(name = "dispatcher_info_id")
    private Long dispatcherInfoLongId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
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

    @ManyToOne(targetEntity = HibernateSenderInfo.class)
    @JoinColumns({ //
            @JoinColumn(name = "sender_info_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateSenderInfo senderInfo;

    @ManyToOne(targetEntity = HibernateDispatcherInfo.class)
    @JoinColumns({ //
            @JoinColumn(name = "dispatcher_info_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateDispatcherInfo dispatcherInfo;

    public HibernateRelation() {
    }

    // -----------------------------------------------------------映射用 getter&setter-----------------------------------------------------------
    public HibernateRelationKey getKey() {
        return new HibernateRelationKey(notifySettingId, topicId);
    }

    public void setKey(HibernateRelationKey key) {
        if (Objects.isNull(key)) {
            this.notifySettingId = null;
            this.topicId = null;
        } else {
            this.notifySettingId = key.getNotifySettingId();
            this.topicId = key.getTopicId();
        }
    }

    public HibernateLongIdKey getSenderInfoKey() {
        return Optional.ofNullable(senderInfoLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setSenderInfoKey(HibernateLongIdKey key) {
        this.senderInfoLongId = Optional.ofNullable(key).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getDispatcherInfoKey() {
        return Optional.ofNullable(dispatcherInfoLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setDispatcherInfoKey(HibernateLongIdKey key) {
        this.dispatcherInfoLongId = Optional.ofNullable(key).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // -----------------------------------------------------------常规 getter&setter-----------------------------------------------------------
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

    public Long getSenderInfoLongId() {
        return senderInfoLongId;
    }

    public void setSenderInfoLongId(Long senderInfoLongId) {
        this.senderInfoLongId = senderInfoLongId;
    }

    public Long getDispatcherInfoLongId() {
        return dispatcherInfoLongId;
    }

    public void setDispatcherInfoLongId(Long dispatcherInfoLongId) {
        this.dispatcherInfoLongId = dispatcherInfoLongId;
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

    public HibernateSenderInfo getSenderInfo() {
        return senderInfo;
    }

    public void setSenderInfo(HibernateSenderInfo senderInfo) {
        this.senderInfo = senderInfo;
    }

    public HibernateDispatcherInfo getDispatcherInfo() {
        return dispatcherInfo;
    }

    public void setDispatcherInfo(HibernateDispatcherInfo dispatcherInfo) {
        this.dispatcherInfo = dispatcherInfo;
    }

    @Override
    public String toString() {
        return "HibernateRelation{" +
                "notifySettingId=" + notifySettingId +
                ", topicId='" + topicId + '\'' +
                ", senderInfoLongId=" + senderInfoLongId +
                ", dispatcherInfoLongId=" + dispatcherInfoLongId +
                ", remark='" + remark + '\'' +
                ", notifySetting=" + notifySetting +
                ", topic=" + topic +
                ", senderInfo=" + senderInfo +
                ", dispatcherInfo=" + dispatcherInfo +
                '}';
    }
}

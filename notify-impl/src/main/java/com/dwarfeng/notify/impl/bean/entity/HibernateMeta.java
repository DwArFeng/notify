package com.dwarfeng.notify.impl.bean.entity;

import com.dwarfeng.notify.impl.bean.key.HibernateMetaKey;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(HibernateMetaKey.class)
@Table(name = "tbl_meta")
public class HibernateMeta implements Bean {

    private static final long serialVersionUID = -2128593082064931893L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "notify_setting_id", nullable = false)
    private Long notifySettingId;

    @Id
    @Column(name = "topic_id", length = Constraints.LENGTH_ID, nullable = false)
    private String topicId;

    @Id
    @Column(name = "user_id", length = Constraints.LENGTH_ID, nullable = false)
    private String userId;

    @Id
    @Column(name = "meta_id", length = Constraints.LENGTH_ID, nullable = false)
    private String metaId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "value", columnDefinition = "TEXT")
    private String value;

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

    @ManyToOne(targetEntity = HibernateUser.class)
    @JoinColumns({ //
            @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateUser user;

    public HibernateMeta() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateMetaKey getKey() {
        return new HibernateMetaKey(notifySettingId, topicId, userId, metaId);
    }

    public void setKey(HibernateMetaKey key) {
        if (Objects.isNull(key)) {
            this.notifySettingId = null;
            this.topicId = null;
            this.userId = null;
            this.metaId = null;
        } else {
            this.notifySettingId = key.getNotifySettingId();
            this.topicId = key.getTopicId();
            this.userId = key.getUserId();
            this.metaId = key.getMetaId();
        }
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMetaId() {
        return metaId;
    }

    public void setMetaId(String metaId) {
        this.metaId = metaId;
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

    public HibernateUser getUser() {
        return user;
    }

    public void setUser(HibernateUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "notifySettingId = " + notifySettingId + ", " +
                "topicId = " + topicId + ", " +
                "userId = " + userId + ", " +
                "metaId = " + metaId + ", " +
                "value = " + value + ", " +
                "remark = " + remark + ", " +
                "notifySetting = " + notifySetting + ", " +
                "topic = " + topic + ", " +
                "user = " + user + ")";
    }
}

package com.dwarfeng.notify.impl.bean.entity;

import com.dwarfeng.notify.impl.bean.key.HibernateNotifySendRecordKey;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(HibernateNotifySendRecordKey.class)
@Table(name = "tbl_notify_send_record")
public class HibernateNotifySendRecord implements Bean {

    private static final long serialVersionUID = 6401476278480844891L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "notify_history_id", nullable = false)
    private Long notifyHistoryId;

    @Id
    @Column(name = "topic_Id", length = Constraints.LENGTH_ID, nullable = false)
    private String topicId;

    @Id
    @Column(name = "user_Id", length = Constraints.LENGTH_ID, nullable = false)
    private String userId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "succeed_flag")
    private Boolean succeedFlag;

    @Column(name = "sender_message", length = Constraints.LENGTH_MESSAGE)
    private String senderMessage;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateNotifyHistory.class)
    @JoinColumns({ //
            @JoinColumn(name = "notify_history_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateNotifyHistory notifyHistory;

    @ManyToOne(targetEntity = HibernateTopic.class)
    @JoinColumns({ //
            @JoinColumn(name = "topic_Id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateTopic topic;

    @ManyToOne(targetEntity = HibernateUser.class)
    @JoinColumns({ //
            @JoinColumn(name = "user_Id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateUser user;

    public HibernateNotifySendRecord() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateNotifySendRecordKey getKey() {
        return new HibernateNotifySendRecordKey(notifyHistoryId, topicId, userId);
    }

    public void setKey(HibernateNotifySendRecordKey key) {
        if (Objects.isNull(key)) {
            this.notifyHistoryId = null;
            this.topicId = null;
            this.userId = null;
        } else {
            this.notifyHistoryId = key.getNotifyHistoryId();
            this.topicId = key.getTopicId();
            this.userId = key.getUserId();
        }
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getNotifyHistoryId() {
        return notifyHistoryId;
    }

    public void setNotifyHistoryId(Long notifyHistoryId) {
        this.notifyHistoryId = notifyHistoryId;
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

    public Boolean getSucceedFlag() {
        return succeedFlag;
    }

    public void setSucceedFlag(Boolean succeedFlag) {
        this.succeedFlag = succeedFlag;
    }

    public String getSenderMessage() {
        return senderMessage;
    }

    public void setSenderMessage(String senderMessage) {
        this.senderMessage = senderMessage;
    }

    public HibernateNotifyHistory getNotifyHistory() {
        return notifyHistory;
    }

    public void setNotifyHistory(HibernateNotifyHistory notifyHistory) {
        this.notifyHistory = notifyHistory;
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
                "notifyHistoryId = " + notifyHistoryId + ", " +
                "topicId = " + topicId + ", " +
                "userId = " + userId + ", " +
                "succeedFlag = " + succeedFlag + ", " +
                "senderMessage = " + senderMessage + ", " +
                "notifyHistory = " + notifyHistory + ", " +
                "topic = " + topic + ", " +
                "user = " + user + ")";
    }
}

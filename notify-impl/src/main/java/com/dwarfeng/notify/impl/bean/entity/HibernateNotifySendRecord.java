package com.dwarfeng.notify.impl.bean.entity;

import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Optional;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_notify_send_record")
public class HibernateNotifySendRecord implements Bean {

    private static final long serialVersionUID = 1361956877693541068L;
    
    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "notify_history_id")
    private Long notifyHistoryLongId;

    @Column(name = "topic_Id", length = Constraints.LENGTH_ID)
    private String topicStringId;

    @Column(name = "user_Id", length = Constraints.LENGTH_ID)
    private String userStringId;

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

    public HibernateNotifySendRecord() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey key) {
        this.longId = Optional.ofNullable(key).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getNotifyHistoryKey() {
        return Optional.ofNullable(notifyHistoryLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setNotifyHistoryKey(HibernateLongIdKey key) {
        this.notifyHistoryLongId = Optional.ofNullable(key).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateStringIdKey getTopicKey() {
        return Optional.ofNullable(topicStringId).map(HibernateStringIdKey::new).orElse(null);
    }

    public void setTopicKey(HibernateStringIdKey key) {
        this.topicStringId = Optional.ofNullable(key).map(HibernateStringIdKey::getStringId).orElse(null);
    }

    public HibernateStringIdKey getUserKey() {
        return Optional.ofNullable(userStringId).map(HibernateStringIdKey::new).orElse(null);
    }

    public void setUserKey(HibernateStringIdKey key) {
        this.userStringId = Optional.ofNullable(key).map(HibernateStringIdKey::getStringId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public Long getNotifyHistoryLongId() {
        return notifyHistoryLongId;
    }

    public void setNotifyHistoryLongId(Long notifyHistoryLongId) {
        this.notifyHistoryLongId = notifyHistoryLongId;
    }

    public String getTopicStringId() {
        return topicStringId;
    }

    public void setTopicStringId(String topicStringId) {
        this.topicStringId = topicStringId;
    }

    public String getUserStringId() {
        return userStringId;
    }

    public void setUserStringId(String userStringId) {
        this.userStringId = userStringId;
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

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "notifyHistoryLongId = " + notifyHistoryLongId + ", " +
                "topicStringId = " + topicStringId + ", " +
                "userStringId = " + userStringId + ", " +
                "succeedFlag = " + succeedFlag + ", " +
                "senderMessage = " + senderMessage + ", " +
                "notifyHistory = " + notifyHistory + ")";
    }
}

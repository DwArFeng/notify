package com.dwarfeng.notify.impl.bean.entity;

import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_notify_history")
public class HibernateNotifyHistory implements Bean {

    private static final long serialVersionUID = -8657379149799147790L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "notify_setting_id")
    private Long notifySettingLongId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "happened_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date happenedDate;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateNotifySetting.class)
    @JoinColumns({ //
            @JoinColumn(name = "notify_setting_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateNotifySetting notifySetting;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateNotifyInfoRecord.class, mappedBy = "notifyHistory")
    private Set<HibernateNotifyInfoRecord> notifyInfoRecords = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateNotifySendRecord.class, mappedBy = "notifyHistory")
    private Set<HibernateNotifySendRecord> notifySendRecords = new HashSet<>();

    public HibernateNotifyHistory() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey key) {
        this.longId = Optional.ofNullable(key).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getNotifySettingKey() {
        return Optional.ofNullable(notifySettingLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setNotifySettingKey(HibernateLongIdKey key) {
        this.notifySettingLongId = Optional.ofNullable(key).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // -----------------------------------------------------------常规 getter&setter-----------------------------------------------------------
    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public Long getNotifySettingLongId() {
        return notifySettingLongId;
    }

    public void setNotifySettingLongId(Long notifySettingLongId) {
        this.notifySettingLongId = notifySettingLongId;
    }

    public Date getHappenedDate() {
        return happenedDate;
    }

    public void setHappenedDate(Date happenedDate) {
        this.happenedDate = happenedDate;
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

    public Set<HibernateNotifyInfoRecord> getNotifyInfoRecords() {
        return notifyInfoRecords;
    }

    public void setNotifyInfoRecords(Set<HibernateNotifyInfoRecord> notifyInfoRecords) {
        this.notifyInfoRecords = notifyInfoRecords;
    }

    public Set<HibernateNotifySendRecord> getNotifySendRecords() {
        return notifySendRecords;
    }

    public void setNotifySendRecords(Set<HibernateNotifySendRecord> notifySendRecords) {
        this.notifySendRecords = notifySendRecords;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "notifySettingLongId = " + notifySettingLongId + ", " +
                "happenedDate = " + happenedDate + ", " +
                "remark = " + remark + ", " +
                "notifySetting = " + notifySetting + ")";
    }
}

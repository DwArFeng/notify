package com.dwarfeng.notify.impl.bean.entity;

import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.subgrade.basic.stack.bean.Bean;
import com.dwarfeng.subgrade.data.sdk.bean.key.hibernate.HibernateLongIdKey;
import jakarta.persistence.*;

import java.io.Serial;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_notify_history")
public class HibernateNotifyHistory implements Bean {

    @Serial
    private static final long serialVersionUID = 4647062074716725538L;

    // region 主键

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // endregion

    // region 外键

    @Column(name = "notify_setting_id")
    private Long notifySettingLongId;

    // endregion

    // region 主属性字段

    @Column(name = "happened_date")
    private Timestamp happenedDate;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // endregion

    // region 多对一

    @ManyToOne(targetEntity = HibernateNotifySetting.class)
    @JoinColumns({ //
            @JoinColumn(name = "notify_setting_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateNotifySetting notifySetting;

    // endregion

    // region 一对多

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateNotifyInfoRecord.class, mappedBy = "notifyHistory")
    private Set<HibernateNotifyInfoRecord> notifyInfoRecords = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateNotifySendRecord.class, mappedBy = "notifyHistory")
    private Set<HibernateNotifySendRecord> notifySendRecords = new HashSet<>();

    // endregion

    public HibernateNotifyHistory() {
    }

    // region 映射用属性区

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

    // endregion

    // region 常规 getter&setter

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

    public Timestamp getHappenedDate() {
        return happenedDate;
    }

    public void setHappenedDate(Timestamp happenedDate) {
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

    // endregion

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

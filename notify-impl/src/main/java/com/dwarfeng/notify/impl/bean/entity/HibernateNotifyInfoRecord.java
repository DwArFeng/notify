package com.dwarfeng.notify.impl.bean.entity;

import com.dwarfeng.notify.impl.bean.key.HibernateNotifyInfoRecordKey;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(HibernateNotifyInfoRecordKey.class)
@Table(name = "tbl_notify_info_record")
public class HibernateNotifyInfoRecord implements Bean {

    private static final long serialVersionUID = 5281515276251667342L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "notify_history_id", nullable = false)
    private Long notifyHistoryId;

    @Id
    @Column(name = "type", nullable = false)
    private Integer type;

    @Id
    @Column(name = "record_id", length = Constraints.LENGTH_ID, nullable = false)
    private String recordId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "value", columnDefinition = "TEXT")
    private String value;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateNotifyHistory.class)
    @JoinColumns({ //
            @JoinColumn(name = "notify_history_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateNotifyHistory notifyHistory;

    public HibernateNotifyInfoRecord() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateNotifyInfoRecordKey getKey() {
        return new HibernateNotifyInfoRecordKey(notifyHistoryId, type, recordId);
    }

    public void setKey(HibernateNotifyInfoRecordKey key) {
        if (Objects.isNull(key)) {
            this.notifyHistoryId = null;
            this.type = null;
            this.recordId = null;
        } else {
            this.notifyHistoryId = key.getNotifyHistoryId();
            this.type = key.getType();
            this.recordId = key.getRecordId();
        }
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getNotifyHistoryId() {
        return notifyHistoryId;
    }

    public void setNotifyHistoryId(Long notifyHistoryId) {
        this.notifyHistoryId = notifyHistoryId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
                "notifyHistoryId = " + notifyHistoryId + ", " +
                "recordId = " + recordId + ", " +
                "value = " + value + ", " +
                "notifyHistory = " + notifyHistory + ")";
    }
}

package com.dwarfeng.notify.impl.bean.entity;

import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@IdClass(HibernateStringIdKey.class)
@Table(name = "tbl_user")
public class HibernateUser implements Bean {

    private static final long serialVersionUID = -6763701875229339369L;
    
    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", length = Constraints.LENGTH_ID, nullable = false, unique = true)
    private String stringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateMeta.class, mappedBy = "user")
    private Set<HibernateMeta> metas = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateSendHistory.class, mappedBy = "user")
    private Set<HibernateSendHistory> sendHistories = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateNotifySendRecord.class, mappedBy = "user")
    private Set<HibernateNotifySendRecord> sendRecords = new HashSet<>();

    public HibernateUser() {
    }

    // -----------------------------------------------------------映射用 getter&setter-----------------------------------------------------------
    public HibernateStringIdKey getKey() {
        return Optional.ofNullable(stringId).map(HibernateStringIdKey::new).orElse(null);
    }

    public void setKey(HibernateStringIdKey key) {
        this.stringId = Optional.ofNullable(key).map(HibernateStringIdKey::getStringId).orElse(null);
    }

    // -----------------------------------------------------------常规 getter&setter-----------------------------------------------------------
    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<HibernateMeta> getMetas() {
        return metas;
    }

    public void setMetas(Set<HibernateMeta> metas) {
        this.metas = metas;
    }

    public Set<HibernateSendHistory> getSendHistories() {
        return sendHistories;
    }

    public void setSendHistories(Set<HibernateSendHistory> sendHistories) {
        this.sendHistories = sendHistories;
    }

    public Set<HibernateNotifySendRecord> getSendRecords() {
        return sendRecords;
    }

    public void setSendRecords(Set<HibernateNotifySendRecord> sendRecords) {
        this.sendRecords = sendRecords;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "stringId = " + stringId + ", " +
                "remark = " + remark + ", " +
                "enabled = " + enabled + ")";
    }
}

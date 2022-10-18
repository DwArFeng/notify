package com.dwarfeng.notify.impl.bean.entity;

import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_notify_setting")
public class HibernateNotifySetting implements Bean {

    private static final long serialVersionUID = 1481238128233525805L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "label", length = Constraints.LENGTH_LABEL)
    private String label;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    @Column(name = "enabled")
    private boolean enabled;

    // -----------------------------------------------------------一对一-----------------------------------------------------------
    @OneToOne(cascade = CascadeType.MERGE, targetEntity = HibernateRouterInfo.class, mappedBy = "notifySetting")
    private HibernateRouterInfo routerInfo;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateSenderInfo.class, mappedBy = "notifySetting")
    private Set<HibernateSenderInfo> senderInfos = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernatePreference.class, mappedBy = "notifySetting")
    private Set<HibernatePreference> preferences = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateVariable.class, mappedBy = "notifySetting")
    private Set<HibernateVariable> variables = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateSendHistory.class, mappedBy = "notifySetting")
    private Set<HibernateSendHistory> sendHistories = new HashSet<>();

    public HibernateNotifySetting() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey key) {
        this.longId = Optional.ofNullable(key).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public HibernateRouterInfo getRouterInfo() {
        return routerInfo;
    }

    public void setRouterInfo(HibernateRouterInfo routerInfo) {
        this.routerInfo = routerInfo;
    }

    public Set<HibernateSenderInfo> getSenderInfos() {
        return senderInfos;
    }

    public void setSenderInfos(Set<HibernateSenderInfo> senderInfos) {
        this.senderInfos = senderInfos;
    }

    public Set<HibernatePreference> getPreferences() {
        return preferences;
    }

    public void setPreferences(Set<HibernatePreference> preferences) {
        this.preferences = preferences;
    }

    public Set<HibernateVariable> getVariables() {
        return variables;
    }

    public void setVariables(Set<HibernateVariable> variables) {
        this.variables = variables;
    }

    public Set<HibernateSendHistory> getSendHistories() {
        return sendHistories;
    }

    public void setSendHistories(Set<HibernateSendHistory> sendHistories) {
        this.sendHistories = sendHistories;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "label = " + label + ", " +
                "remark = " + remark + ", " +
                "enabled = " + enabled + ", " +
                "routerInfo = " + routerInfo + ")";
    }
}

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

    private static final long serialVersionUID = 8173138834953655924L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", length = Constraints.LENGTH_ID, nullable = false, unique = true)
    private String stringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernatePreference.class, mappedBy = "user")
    private Set<HibernatePreference> preferences = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateVariable.class, mappedBy = "user")
    private Set<HibernateVariable> variables = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateSendHistory.class, mappedBy = "user")
    private Set<HibernateSendHistory> sendHistories = new HashSet<>();

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
                "stringId = " + stringId + ", " +
                "remark = " + remark + ")";
    }
}

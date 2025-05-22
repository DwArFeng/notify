package com.dwarfeng.notify.impl.bean.entity;

import com.dwarfeng.datamark.bean.jpa.DatamarkEntityListener;
import com.dwarfeng.datamark.bean.jpa.DatamarkField;
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
@EntityListeners(DatamarkEntityListener.class)
public class HibernateNotifySetting implements Bean {

    private static final long serialVersionUID = -7599137720811010367L;

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

    // -----------------------------------------------------------审计-----------------------------------------------------------
    @DatamarkField(handlerName = "notifySettingDatamarkHandler")
    @Column(
            name = "created_datamark",
            length = com.dwarfeng.datamark.util.Constraints.LENGTH_DATAMARK_VALUE,
            updatable = false
    )
    private String createdDatamark;

    @DatamarkField(handlerName = "notifySettingDatamarkHandler")
    @Column(
            name = "modified_datamark",
            length = com.dwarfeng.datamark.util.Constraints.LENGTH_DATAMARK_VALUE
    )
    private String modifiedDatamark;

    // -----------------------------------------------------------一对一-----------------------------------------------------------
    @OneToOne(cascade = CascadeType.MERGE, targetEntity = HibernateRouterInfo.class, mappedBy = "notifySetting")
    private HibernateRouterInfo routerInfo;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateSenderInfo.class, mappedBy = "notifySetting")
    private Set<HibernateSenderInfo> senderInfos = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateMeta.class, mappedBy = "notifySetting")
    private Set<HibernateMeta> metas = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateNotifyHistory.class, mappedBy = "notifySetting")
    private Set<HibernateNotifyHistory> notifyHistories = new HashSet<>();

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

    public String getCreatedDatamark() {
        return createdDatamark;
    }

    public void setCreatedDatamark(String createdDatamark) {
        this.createdDatamark = createdDatamark;
    }

    public String getModifiedDatamark() {
        return modifiedDatamark;
    }

    public void setModifiedDatamark(String modifiedDatamark) {
        this.modifiedDatamark = modifiedDatamark;
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

    public Set<HibernateMeta> getMetas() {
        return metas;
    }

    public void setMetas(Set<HibernateMeta> metas) {
        this.metas = metas;
    }

    public Set<HibernateNotifyHistory> getNotifyHistories() {
        return notifyHistories;
    }

    public void setNotifyHistories(Set<HibernateNotifyHistory> notifyHistories) {
        this.notifyHistories = notifyHistories;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "label = " + label + ", " +
                "remark = " + remark + ", " +
                "enabled = " + enabled + ", " +
                "createdDatamark = " + createdDatamark + ", " +
                "modifiedDatamark = " + modifiedDatamark + ", " +
                "routerInfo = " + routerInfo + ")";
    }
}

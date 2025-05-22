package com.dwarfeng.notify.impl.bean.entity;

import com.dwarfeng.datamark.bean.jpa.DatamarkEntityListener;
import com.dwarfeng.datamark.bean.jpa.DatamarkField;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@IdClass(HibernateStringIdKey.class)
@Table(name = "tbl_topic")
@EntityListeners(DatamarkEntityListener.class)
public class HibernateTopic implements Bean {

    private static final long serialVersionUID = -347030751421427751L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", length = Constraints.LENGTH_ID, nullable = false, unique = true)
    private String stringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "label", length = Constraints.LENGTH_LABEL)
    private String label;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "priority")
    private int priority;

    // -----------------------------------------------------------审计-----------------------------------------------------------
    @DatamarkField(handlerName = "topicDatamarkHandler")
    @Column(
            name = "created_datamark",
            length = com.dwarfeng.datamark.util.Constraints.LENGTH_DATAMARK_VALUE,
            updatable = false
    )
    private String createdDatamark;

    @DatamarkField(handlerName = "topicDatamarkHandler")
    @Column(
            name = "modified_datamark",
            length = com.dwarfeng.datamark.util.Constraints.LENGTH_DATAMARK_VALUE
    )
    private String modifiedDatamark;

    // -----------------------------------------------------------一对一-----------------------------------------------------------
    @OneToOne(cascade = CascadeType.MERGE, targetEntity = HibernateDispatcherInfo.class, mappedBy = "topic")
    private HibernateDispatcherInfo dispatcherInfo;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateSenderInfo.class, mappedBy = "topic")
    private Set<HibernateSenderInfo> senderInfos = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateMeta.class, mappedBy = "topic")
    private Set<HibernateMeta> metas = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateMetaIndicator.class, mappedBy = "topic")
    private Set<HibernateMetaIndicator> metaIndicators = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateNotifySendRecord.class, mappedBy = "topic")
    private Set<HibernateNotifySendRecord> notifySendRecords = new HashSet<>();

    public HibernateTopic() {
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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

    public HibernateDispatcherInfo getDispatcherInfo() {
        return dispatcherInfo;
    }

    public void setDispatcherInfo(HibernateDispatcherInfo dispatcherInfo) {
        this.dispatcherInfo = dispatcherInfo;
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

    public Set<HibernateMetaIndicator> getMetaIndicators() {
        return metaIndicators;
    }

    public void setMetaIndicators(Set<HibernateMetaIndicator> metaIndicators) {
        this.metaIndicators = metaIndicators;
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
                "stringId = " + stringId + ", " +
                "label = " + label + ", " +
                "remark = " + remark + ", " +
                "enabled = " + enabled + ", " +
                "priority = " + priority + ", " +
                "createdDatamark = " + createdDatamark + ", " +
                "modifiedDatamark = " + modifiedDatamark + ", " +
                "dispatcherInfo = " + dispatcherInfo + ")";
    }
}

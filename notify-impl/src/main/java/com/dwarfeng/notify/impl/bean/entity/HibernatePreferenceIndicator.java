package com.dwarfeng.notify.impl.bean.entity;

import com.dwarfeng.notify.impl.bean.entity.key.HibernatePreferenceIndicatorKey;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(HibernatePreferenceIndicatorKey.class)
@Table(name = "tbl_preference_indicator")
public class HibernatePreferenceIndicator implements Bean {

    private static final long serialVersionUID = -4304696840908858057L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "topic_id", length = Constraints.LENGTH_ID, nullable = false)
    private String topicId;

    @Id
    @Column(name = "preference_id", length = Constraints.LENGTH_ID, nullable = false)
    private String preferenceId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "label", length = Constraints.LENGTH_LABEL)
    private String label;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    @Column(name = "default_value", columnDefinition = "TEXT")
    private String defaultValue;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateTopic.class)
    @JoinColumns({ //
            @JoinColumn(name = "topic_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateTopic topic;

    public HibernatePreferenceIndicator() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernatePreferenceIndicatorKey getKey() {
        return new HibernatePreferenceIndicatorKey(topicId, preferenceId);
    }

    public void setKey(HibernatePreferenceIndicatorKey key) {
        if (Objects.isNull(key)) {
            this.topicId = null;
            this.preferenceId = null;
        } else {
            this.topicId = key.getTopicId();
            this.preferenceId = key.getPreferenceId();
        }
    }

    // -----------------------------------------------------------常规 getter&setter-----------------------------------------------------------
    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(String preferenceId) {
        this.preferenceId = preferenceId;
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

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public HibernateTopic getTopic() {
        return topic;
    }

    public void setTopic(HibernateTopic topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "topicId = " + topicId + ", " +
                "preferenceId = " + preferenceId + ", " +
                "label = " + label + ", " +
                "remark = " + remark + ", " +
                "defaultValue = " + defaultValue + ", " +
                "topic = " + topic + ")";
    }
}

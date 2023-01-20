package com.dwarfeng.notify.impl.bean.entity;

import com.dwarfeng.notify.impl.bean.key.HibernateMetaIndicatorKey;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(HibernateMetaIndicatorKey.class)
@Table(name = "tbl_meta_indicator")
public class HibernateMetaIndicator implements Bean {

    private static final long serialVersionUID = -5594489097447095272L;
    
    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "topic_id", length = Constraints.LENGTH_ID, nullable = false)
    private String topicId;

    @Id
    @Column(name = "meta_id", length = Constraints.LENGTH_ID, nullable = false)
    private String metaId;

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

    public HibernateMetaIndicator() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateMetaIndicatorKey getKey() {
        return new HibernateMetaIndicatorKey(topicId, metaId);
    }

    public void setKey(HibernateMetaIndicatorKey key) {
        if (Objects.isNull(key)) {
            this.topicId = null;
            this.metaId = null;
        } else {
            this.topicId = key.getTopicId();
            this.metaId = key.getMetaId();
        }
    }

    // -----------------------------------------------------------常规 getter&setter-----------------------------------------------------------
    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getMetaId() {
        return metaId;
    }

    public void setMetaId(String metaId) {
        this.metaId = metaId;
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
                "metaId = " + metaId + ", " +
                "label = " + label + ", " +
                "remark = " + remark + ", " +
                "defaultValue = " + defaultValue + ", " +
                "topic = " + topic + ")";
    }
}

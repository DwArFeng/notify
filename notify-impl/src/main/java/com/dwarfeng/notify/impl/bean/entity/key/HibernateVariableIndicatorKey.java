package com.dwarfeng.notify.impl.bean.entity.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 变量指示器主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class HibernateVariableIndicatorKey implements Key {

    private static final long serialVersionUID = 3153820534482046279L;
    private String topicId;
    private String variableId;

    public HibernateVariableIndicatorKey() {
    }

    public HibernateVariableIndicatorKey(String topicId, String variableId) {
        this.topicId = topicId;
        this.variableId = variableId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getVariableId() {
        return variableId;
    }

    public void setVariableId(String variableId) {
        this.variableId = variableId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HibernateVariableIndicatorKey that = (HibernateVariableIndicatorKey) o;

        if (!Objects.equals(topicId, that.topicId)) return false;
        return Objects.equals(variableId, that.variableId);
    }

    @Override
    public int hashCode() {
        int result = topicId != null ? topicId.hashCode() : 0;
        result = 31 * result + (variableId != null ? variableId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HibernateVariableIndicatorKey{" +
                "topicId='" + topicId + '\'' +
                ", variableId='" + variableId + '\'' +
                '}';
    }
}

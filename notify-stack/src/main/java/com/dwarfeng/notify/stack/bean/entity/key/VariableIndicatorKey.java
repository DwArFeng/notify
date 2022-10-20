package com.dwarfeng.notify.stack.bean.entity.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 变量指示器主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class VariableIndicatorKey implements Key {

    private static final long serialVersionUID = 7656503109356396814L;
    
    private String topicId;
    private String variableId;

    public VariableIndicatorKey() {
    }

    public VariableIndicatorKey(String topicId, String variableId) {
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

        VariableIndicatorKey that = (VariableIndicatorKey) o;

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
        return "VariableIndicatorKey{" +
                "topicId='" + topicId + '\'' +
                ", variableId='" + variableId + '\'' +
                '}';
    }
}

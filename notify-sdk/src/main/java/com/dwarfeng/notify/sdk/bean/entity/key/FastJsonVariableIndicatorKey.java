package com.dwarfeng.notify.sdk.bean.entity.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.entity.key.VariableIndicatorKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * FastJson 变量指示器主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class FastJsonVariableIndicatorKey implements Key {

    private static final long serialVersionUID = -6107802314927095762L;

    public static FastJsonVariableIndicatorKey of(VariableIndicatorKey variableIndicatorKey) {
        if (Objects.isNull(variableIndicatorKey)) {
            return null;
        } else {
            return new FastJsonVariableIndicatorKey(
                    variableIndicatorKey.getTopicId(), variableIndicatorKey.getVariableId()
            );
        }
    }

    @JSONField(name = "topic_id", ordinal = 1)
    private String topicId;

    @JSONField(name = "variable_id", ordinal = 2)
    private String variableId;

    public FastJsonVariableIndicatorKey() {
    }

    public FastJsonVariableIndicatorKey(String topicId, String variableId) {
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

        FastJsonVariableIndicatorKey that = (FastJsonVariableIndicatorKey) o;

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
        return "FastJsonVariableIndicatorKey{" +
                "topicId='" + topicId + '\'' +
                ", variableId='" + variableId + '\'' +
                '}';
    }
}

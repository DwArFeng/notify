package com.dwarfeng.notify.sdk.bean.entity.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.notify.stack.bean.entity.key.VariableIndicatorKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * FastJson 变量指示器主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class WebInputVariableIndicatorKey implements Key {

    private static final long serialVersionUID = -753706436082760817L;

    public static VariableIndicatorKey toStackBean(WebInputVariableIndicatorKey webInputVariableIndicatorKey) {
        if (Objects.isNull(webInputVariableIndicatorKey)) {
            return null;
        } else {
            return new VariableIndicatorKey(
                    webInputVariableIndicatorKey.getTopicId(), webInputVariableIndicatorKey.getVariableId()
            );
        }
    }

    @JSONField(name = "topic_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_ID)
    private String topicId;

    @JSONField(name = "variable_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_ID)
    private String variableId;

    public WebInputVariableIndicatorKey() {
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

        WebInputVariableIndicatorKey that = (WebInputVariableIndicatorKey) o;

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
        return "WebInputVariableIndicatorKey{" +
                "topicId='" + topicId + '\'' +
                ", variableId='" + variableId + '\'' +
                '}';
    }
}

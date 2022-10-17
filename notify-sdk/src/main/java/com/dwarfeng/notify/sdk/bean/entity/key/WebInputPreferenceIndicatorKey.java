package com.dwarfeng.notify.sdk.bean.entity.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.notify.stack.bean.entity.key.PreferenceIndicatorKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * FastJson 偏好指示器主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class WebInputPreferenceIndicatorKey implements Key {

    private static final long serialVersionUID = 5105624463019848340L;

    public static PreferenceIndicatorKey toStackBean(WebInputPreferenceIndicatorKey webInputPreferenceIndicatorKey) {
        if (Objects.isNull(webInputPreferenceIndicatorKey)) {
            return null;
        } else {
            return new PreferenceIndicatorKey(
                    webInputPreferenceIndicatorKey.getTopicId(), webInputPreferenceIndicatorKey.getPreferenceId()
            );
        }
    }

    @JSONField(name = "topic_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_ID)
    private String topicId;

    @JSONField(name = "preference_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_ID)
    private String preferenceId;

    public WebInputPreferenceIndicatorKey() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WebInputPreferenceIndicatorKey that = (WebInputPreferenceIndicatorKey) o;

        if (!Objects.equals(topicId, that.topicId)) return false;
        return Objects.equals(preferenceId, that.preferenceId);
    }

    @Override
    public int hashCode() {
        int result = topicId != null ? topicId.hashCode() : 0;
        result = 31 * result + (preferenceId != null ? preferenceId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WebInputPreferenceIndicatorKey{" +
                "topicId='" + topicId + '\'' +
                ", preferenceId='" + preferenceId + '\'' +
                '}';
    }
}

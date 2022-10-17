package com.dwarfeng.notify.sdk.bean.entity.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.entity.key.PreferenceIndicatorKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * FastJson 偏好指示器主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class FastJsonPreferenceIndicatorKey implements Key {

    private static final long serialVersionUID = 1035648980735410559L;

    public static FastJsonPreferenceIndicatorKey of(PreferenceIndicatorKey preferenceIndicatorKey) {
        if (Objects.isNull(preferenceIndicatorKey)) {
            return null;
        } else {
            return new FastJsonPreferenceIndicatorKey(
                    preferenceIndicatorKey.getTopicId(), preferenceIndicatorKey.getPreferenceId()
            );
        }
    }

    @JSONField(name = "topic_id", ordinal = 1)
    private String topicId;

    @JSONField(name = "preference_id", ordinal = 2)
    private String preferenceId;

    public FastJsonPreferenceIndicatorKey() {
    }

    public FastJsonPreferenceIndicatorKey(String topicId, String preferenceId) {
        this.topicId = topicId;
        this.preferenceId = preferenceId;
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

        FastJsonPreferenceIndicatorKey that = (FastJsonPreferenceIndicatorKey) o;

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
        return "FastJsonPreferenceIndicatorKey{" +
                "topicId='" + topicId + '\'' +
                ", preferenceId='" + preferenceId + '\'' +
                '}';
    }
}

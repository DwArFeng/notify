package com.dwarfeng.notify.stack.bean.entity.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 偏好指示器主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class PreferenceIndicatorKey implements Key {

    private static final long serialVersionUID = 665941252291038251L;

    private String topicId;
    private String preferenceId;

    public PreferenceIndicatorKey() {
    }

    public PreferenceIndicatorKey(String topicId, String preferenceId) {
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

        PreferenceIndicatorKey that = (PreferenceIndicatorKey) o;

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
        return "PreferenceIndicatorKey{" +
                "topicId='" + topicId + '\'' +
                ", preferenceId='" + preferenceId + '\'' +
                '}';
    }
}

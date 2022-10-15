package com.dwarfeng.notify.sdk.bean.entity.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.dwarfeng.notify.stack.bean.entity.key.PreferenceKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * JSFixed FastJson 偏好主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class JSFixedFastJsonPreferenceKey implements Key {

    private static final long serialVersionUID = 2830156207041651158L;

    public static JSFixedFastJsonPreferenceKey of(PreferenceKey preferenceKey) {
        if (Objects.isNull(preferenceKey)) {
            return null;
        } else {
            return new JSFixedFastJsonPreferenceKey(
                    preferenceKey.getNotifySettingId(), preferenceKey.getTopicId(), preferenceKey.getUserId(),
                    preferenceKey.getPreferenceId()
            );
        }
    }

    @JSONField(name = "notify_setting_id", ordinal = 1, serializeUsing = ToStringSerializer.class)
    private Long notifySettingId;

    @JSONField(name = "topic_id", ordinal = 2)
    private String topicId;

    @JSONField(name = "user_id", ordinal = 3)
    private String userId;

    @JSONField(name = "preference_id", ordinal = 4)
    private String preferenceId;

    public JSFixedFastJsonPreferenceKey() {
    }

    public JSFixedFastJsonPreferenceKey(Long notifySettingId, String topicId, String userId, String preferenceId) {
        this.notifySettingId = notifySettingId;
        this.topicId = topicId;
        this.userId = userId;
        this.preferenceId = preferenceId;
    }

    public Long getNotifySettingId() {
        return notifySettingId;
    }

    public void setNotifySettingId(Long notifySettingId) {
        this.notifySettingId = notifySettingId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

        JSFixedFastJsonPreferenceKey that = (JSFixedFastJsonPreferenceKey) o;

        if (!Objects.equals(notifySettingId, that.notifySettingId))
            return false;
        if (!Objects.equals(topicId, that.topicId)) return false;
        if (!Objects.equals(userId, that.userId)) return false;
        return Objects.equals(preferenceId, that.preferenceId);
    }

    @Override
    public int hashCode() {
        int result = notifySettingId != null ? notifySettingId.hashCode() : 0;
        result = 31 * result + (topicId != null ? topicId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (preferenceId != null ? preferenceId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "JSFixedFastJsonPreferenceKey{" +
                "notifySettingId=" + notifySettingId +
                ", topicId='" + topicId + '\'' +
                ", userId='" + userId + '\'' +
                ", preferenceId='" + preferenceId + '\'' +
                '}';
    }
}

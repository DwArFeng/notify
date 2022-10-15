package com.dwarfeng.notify.sdk.bean.entity.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.notify.stack.bean.entity.key.PreferenceKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * FastJson 偏好主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class WebInputPreferenceKey implements Key {

    private static final long serialVersionUID = -9146931119087866234L;

    public static PreferenceKey toStackBean(WebInputPreferenceKey webInputPreferenceKey) {
        if (Objects.isNull(webInputPreferenceKey)) {
            return null;
        } else {
            return new PreferenceKey(
                    webInputPreferenceKey.getNotifySettingId(), webInputPreferenceKey.getTopicId(),
                    webInputPreferenceKey.getUserId(), webInputPreferenceKey.getPreferenceId()
            );
        }
    }

    @JSONField(name = "notify_setting_id")
    @NotNull
    private Long notifySettingId;

    @JSONField(name = "topic_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_ID)
    private String topicId;

    @JSONField(name = "user_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_ID)
    private String userId;

    @JSONField(name = "preference_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_ID)
    private String preferenceId;

    public WebInputPreferenceKey() {
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

        WebInputPreferenceKey that = (WebInputPreferenceKey) o;

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
        return "WebInputPreferenceKey{" +
                "notifySettingId=" + notifySettingId +
                ", topicId='" + topicId + '\'' +
                ", userId='" + userId + '\'' +
                ", preferenceId='" + preferenceId + '\'' +
                '}';
    }
}

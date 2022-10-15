package com.dwarfeng.notify.stack.bean.entity.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 变量主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class VariableKey implements Key {

    private static final long serialVersionUID = -4884817708805355167L;

    private Long notifySettingId;
    private String topicId;
    private String userId;
    private String variableId;

    public VariableKey() {
    }

    public VariableKey(Long notifySettingId, String topicId, String userId, String variableId) {
        this.notifySettingId = notifySettingId;
        this.topicId = topicId;
        this.userId = userId;
        this.variableId = variableId;
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

        VariableKey that = (VariableKey) o;

        if (!Objects.equals(notifySettingId, that.notifySettingId))
            return false;
        if (!Objects.equals(topicId, that.topicId)) return false;
        if (!Objects.equals(userId, that.userId)) return false;
        return Objects.equals(variableId, that.variableId);
    }

    @Override
    public int hashCode() {
        int result = notifySettingId != null ? notifySettingId.hashCode() : 0;
        result = 31 * result + (topicId != null ? topicId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (variableId != null ? variableId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VariableKey{" +
                "notifySettingId=" + notifySettingId +
                ", topicId='" + topicId + '\'' +
                ", userId='" + userId + '\'' +
                ", variableId='" + variableId + '\'' +
                '}';
    }
}

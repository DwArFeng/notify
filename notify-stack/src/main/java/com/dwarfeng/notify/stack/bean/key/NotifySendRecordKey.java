package com.dwarfeng.notify.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 通知发送记录主键。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class NotifySendRecordKey implements Key {

    private static final long serialVersionUID = 3098812726078870367L;
    
    private Long notifyHistoryId;
    private String topicId;
    private String userId;

    public NotifySendRecordKey() {
    }

    public NotifySendRecordKey(Long notifyHistoryId, String topicId, String userId) {
        this.notifyHistoryId = notifyHistoryId;
        this.topicId = topicId;
        this.userId = userId;
    }

    public Long getNotifyHistoryId() {
        return notifyHistoryId;
    }

    public void setNotifyHistoryId(Long notifyHistoryId) {
        this.notifyHistoryId = notifyHistoryId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotifySendRecordKey that = (NotifySendRecordKey) o;

        if (!Objects.equals(notifyHistoryId, that.notifyHistoryId))
            return false;
        if (!Objects.equals(topicId, that.topicId)) return false;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        int result = notifyHistoryId != null ? notifyHistoryId.hashCode() : 0;
        result = 31 * result + (topicId != null ? topicId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NotifySendRecordKey{" +
                "notifyHistoryId=" + notifyHistoryId +
                ", topicId='" + topicId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}

package com.dwarfeng.notify.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.dwarfeng.notify.stack.bean.key.NotifySendRecordKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * JSFixed FastJson 通知发送记录主键。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class JSFixedFastJsonNotifySendRecordKey implements Key {

    private static final long serialVersionUID = 6027764720169920357L;

    public static JSFixedFastJsonNotifySendRecordKey of(NotifySendRecordKey notifySendRecordKey) {
        if (Objects.isNull(notifySendRecordKey)) {
            return null;
        } else {
            return new JSFixedFastJsonNotifySendRecordKey(
                    notifySendRecordKey.getNotifyHistoryId(), notifySendRecordKey.getTopicId(),
                    notifySendRecordKey.getUserId()
            );
        }
    }

    @JSONField(name = "notify_history_id", ordinal = 1, serializeUsing = ToStringSerializer.class)
    private Long notifyHistoryId;

    @JSONField(name = "topic_id", ordinal = 2)
    private String topicId;

    @JSONField(name = "user_id", ordinal = 2)
    private String userId;

    public JSFixedFastJsonNotifySendRecordKey() {
    }

    public JSFixedFastJsonNotifySendRecordKey(Long notifyHistoryId, String topicId, String userId) {
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

        JSFixedFastJsonNotifySendRecordKey that = (JSFixedFastJsonNotifySendRecordKey) o;

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
        return "JSFixedFastJsonNotifySendRecordKey{" +
                "notifyHistoryId=" + notifyHistoryId +
                ", topicId='" + topicId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}

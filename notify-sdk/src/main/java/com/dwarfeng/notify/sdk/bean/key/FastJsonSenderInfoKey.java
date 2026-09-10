package com.dwarfeng.notify.sdk.bean.key;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.key.SenderInfoKey;
import com.dwarfeng.subgrade.basic.stack.bean.key.Key;

import java.io.Serial;
import java.util.Objects;

/**
 * FastJson 发送器信息主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class FastJsonSenderInfoKey implements Key {

    @Serial
    private static final long serialVersionUID = 5580725137920589937L;

    public static FastJsonSenderInfoKey of(SenderInfoKey senderInfoKey) {
        if (Objects.isNull(senderInfoKey)) {
            return null;
        } else {
            return new FastJsonSenderInfoKey(senderInfoKey.getNotifySettingId(), senderInfoKey.getTopicId());
        }
    }

    @JSONField(name = "notify_setting_id", ordinal = 1)
    private Long notifySettingId;

    @JSONField(name = "topic_id", ordinal = 2)
    private String topicId;

    public FastJsonSenderInfoKey() {
    }

    public FastJsonSenderInfoKey(Long notifySettingId, String topicId) {
        this.notifySettingId = notifySettingId;
        this.topicId = topicId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FastJsonSenderInfoKey that = (FastJsonSenderInfoKey) o;

        if (!Objects.equals(notifySettingId, that.notifySettingId)) return false;
        return Objects.equals(topicId, that.topicId);
    }

    @Override
    public int hashCode() {
        int result = notifySettingId != null ? notifySettingId.hashCode() : 0;
        result = 31 * result + (topicId != null ? topicId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FastJsonSenderInfoKey{" +
                "notifySettingId=" + notifySettingId +
                ", topicId='" + topicId + '\'' +
                '}';
    }
}

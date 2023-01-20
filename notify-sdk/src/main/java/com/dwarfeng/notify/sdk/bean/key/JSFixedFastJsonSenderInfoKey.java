package com.dwarfeng.notify.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.dwarfeng.notify.stack.bean.key.SenderInfoKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * JSFixed FastJson 发送器信息主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class JSFixedFastJsonSenderInfoKey implements Key {

    private static final long serialVersionUID = 7501604776870790816L;

    public static JSFixedFastJsonSenderInfoKey of(SenderInfoKey senderInfoKey) {
        if (Objects.isNull(senderInfoKey)) {
            return null;
        } else {
            return new JSFixedFastJsonSenderInfoKey(
                    senderInfoKey.getNotifySettingId(), senderInfoKey.getTopicId()
            );
        }
    }

    @JSONField(name = "notify_setting_id", ordinal = 1, serializeUsing = ToStringSerializer.class)
    private Long notifySettingId;

    @JSONField(name = "topic_id", ordinal = 2)
    private String topicId;

    public JSFixedFastJsonSenderInfoKey() {
    }

    public JSFixedFastJsonSenderInfoKey(Long notifySettingId, String topicId) {
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

        JSFixedFastJsonSenderInfoKey that = (JSFixedFastJsonSenderInfoKey) o;

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
        return "JSFixedFastJsonSenderInfoKey{" +
                "notifySettingId=" + notifySettingId +
                ", topicId='" + topicId + '\'' +
                '}';
    }
}

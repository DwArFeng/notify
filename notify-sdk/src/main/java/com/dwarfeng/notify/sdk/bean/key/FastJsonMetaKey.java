package com.dwarfeng.notify.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.key.MetaKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * FastJson 元数据主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class FastJsonMetaKey implements Key {

    private static final long serialVersionUID = 6899595488597283751L;

    public static FastJsonMetaKey of(MetaKey metaKey) {
        if (Objects.isNull(metaKey)) {
            return null;
        } else {
            return new FastJsonMetaKey(
                    metaKey.getNotifySettingId(), metaKey.getTopicId(), metaKey.getUserId(),
                    metaKey.getMetaId()
            );
        }
    }

    @JSONField(name = "notify_setting_id", ordinal = 1)
    private Long notifySettingId;

    @JSONField(name = "topic_id", ordinal = 2)
    private String topicId;

    @JSONField(name = "user_id", ordinal = 3)
    private String userId;

    @JSONField(name = "meta_id", ordinal = 4)
    private String metaId;

    public FastJsonMetaKey() {
    }

    public FastJsonMetaKey(Long notifySettingId, String topicId, String userId, String metaId) {
        this.notifySettingId = notifySettingId;
        this.topicId = topicId;
        this.userId = userId;
        this.metaId = metaId;
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

    public String getMetaId() {
        return metaId;
    }

    public void setMetaId(String metaId) {
        this.metaId = metaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FastJsonMetaKey that = (FastJsonMetaKey) o;

        if (!Objects.equals(notifySettingId, that.notifySettingId))
            return false;
        if (!Objects.equals(topicId, that.topicId)) return false;
        if (!Objects.equals(userId, that.userId)) return false;
        return Objects.equals(metaId, that.metaId);
    }

    @Override
    public int hashCode() {
        int result = notifySettingId != null ? notifySettingId.hashCode() : 0;
        result = 31 * result + (topicId != null ? topicId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FastJsonMetaKey{" +
                "notifySettingId=" + notifySettingId +
                ", topicId='" + topicId + '\'' +
                ", userId='" + userId + '\'' +
                ", metaId='" + metaId + '\'' +
                '}';
    }
}

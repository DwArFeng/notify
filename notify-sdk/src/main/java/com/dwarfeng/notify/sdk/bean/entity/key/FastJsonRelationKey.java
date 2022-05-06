package com.dwarfeng.notify.sdk.bean.entity.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.entity.key.RelationKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * FastJson 关系主键。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonRelationKey implements Key {

    private static final long serialVersionUID = 4071286139261304477L;

    public static FastJsonRelationKey of(RelationKey relationKey) {
        if (Objects.isNull(relationKey)) {
            return null;
        } else {
            return new FastJsonRelationKey(relationKey.getNotifySettingId(), relationKey.getTopicId());
        }
    }

    @JSONField(name = "notify_setting_id", ordinal = 1)
    private Long notifySettingId;

    @JSONField(name = "topic_id", ordinal = 2)
    private String topicId;

    public FastJsonRelationKey() {
    }

    public FastJsonRelationKey(Long notifySettingId, String topicId) {
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

        FastJsonRelationKey that = (FastJsonRelationKey) o;

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
        return "FastJsonRelationKey{" +
                "notifySettingId=" + notifySettingId +
                ", topicId='" + topicId + '\'' +
                '}';
    }
}

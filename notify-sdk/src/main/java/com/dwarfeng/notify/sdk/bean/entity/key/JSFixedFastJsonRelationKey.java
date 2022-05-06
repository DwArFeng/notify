package com.dwarfeng.notify.sdk.bean.entity.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.dwarfeng.notify.stack.bean.entity.key.RelationKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * JSFixed FastJson 关系主键。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonRelationKey implements Key {

    private static final long serialVersionUID = 6484470269472125364L;

    public static JSFixedFastJsonRelationKey of(RelationKey relationKey) {
        if (Objects.isNull(relationKey)) {
            return null;
        } else {
            return new JSFixedFastJsonRelationKey(relationKey.getNotifySettingId(), relationKey.getTopicId());
        }
    }

    @JSONField(name = "notify_setting_id", ordinal = 1, serializeUsing = ToStringSerializer.class)
    private Long notifySettingId;

    @JSONField(name = "topic_id", ordinal = 2)
    private String topicId;

    public JSFixedFastJsonRelationKey() {
    }

    public JSFixedFastJsonRelationKey(Long notifySettingId, String topicId) {
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

        JSFixedFastJsonRelationKey that = (JSFixedFastJsonRelationKey) o;

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
        return "JSFixedFastJsonRelationKey{" +
                "notifySettingId=" + notifySettingId +
                ", topicId='" + topicId + '\'' +
                '}';
    }
}

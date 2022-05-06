package com.dwarfeng.notify.stack.bean.entity.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 关系主键。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class RelationKey implements Key {

    private static final long serialVersionUID = -2815133004629979563L;

    private Long notifySettingId;
    private String topicId;

    public RelationKey() {
    }

    public RelationKey(Long notifySettingId, String topicId) {
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

        RelationKey that = (RelationKey) o;

        if (!Objects.equals(notifySettingId, that.notifySettingId))
            return false;
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
        return "RelationKey{" +
                "notifySettingId=" + notifySettingId +
                ", topicId='" + topicId + '\'' +
                '}';
    }
}

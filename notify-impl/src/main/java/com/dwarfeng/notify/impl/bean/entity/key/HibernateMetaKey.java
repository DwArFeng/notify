package com.dwarfeng.notify.impl.bean.entity.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 元数据主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class HibernateMetaKey implements Key {

    private static final long serialVersionUID = 9211460534310272225L;
    
    private Long notifySettingId;
    private String topicId;
    private String userId;
    private String metaId;

    public HibernateMetaKey() {
    }

    public HibernateMetaKey(Long notifySettingId, String topicId, String userId, String metaId) {
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

        HibernateMetaKey that = (HibernateMetaKey) o;

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
        return "HibernateMetaKey{" +
                "notifySettingId=" + notifySettingId +
                ", topicId='" + topicId + '\'' +
                ", userId='" + userId + '\'' +
                ", metaId='" + metaId + '\'' +
                '}';
    }
}

package com.dwarfeng.notify.impl.bean.key;

import com.dwarfeng.subgrade.stack.bean.Bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * Hibernate 发送器信息主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class HibernateSenderInfoKey implements Bean, Serializable {

    private static final long serialVersionUID = 2816003000405764231L;

    private Long notifySettingId;
    private String topicId;

    public HibernateSenderInfoKey() {
    }

    public HibernateSenderInfoKey(Long notifySettingId, String topicId) {
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

        HibernateSenderInfoKey that = (HibernateSenderInfoKey) o;

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
        return "HibernateSenderInfoKey{" +
                "notifySettingId=" + notifySettingId +
                ", topicId='" + topicId + '\'' +
                '}';
    }
}

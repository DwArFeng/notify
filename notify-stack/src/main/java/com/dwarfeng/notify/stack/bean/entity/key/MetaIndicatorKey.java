package com.dwarfeng.notify.stack.bean.entity.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 元数据指示器主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class MetaIndicatorKey implements Key {

    private static final long serialVersionUID = 8954728273780452721L;
    
    private String topicId;
    private String metaId;

    public MetaIndicatorKey() {
    }

    public MetaIndicatorKey(String topicId, String metaId) {
        this.topicId = topicId;
        this.metaId = metaId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
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

        MetaIndicatorKey that = (MetaIndicatorKey) o;

        if (!Objects.equals(topicId, that.topicId)) return false;
        return Objects.equals(metaId, that.metaId);
    }

    @Override
    public int hashCode() {
        int result = topicId != null ? topicId.hashCode() : 0;
        result = 31 * result + (metaId != null ? metaId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MetaIndicatorKey{" +
                "topicId='" + topicId + '\'' +
                ", metaId='" + metaId + '\'' +
                '}';
    }
}

package com.dwarfeng.notify.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.key.MetaIndicatorKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * FastJson 元数据指示器主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class FastJsonMetaIndicatorKey implements Key {

    private static final long serialVersionUID = -2923972928221272470L;

    public static FastJsonMetaIndicatorKey of(MetaIndicatorKey metaIndicatorKey) {
        if (Objects.isNull(metaIndicatorKey)) {
            return null;
        } else {
            return new FastJsonMetaIndicatorKey(
                    metaIndicatorKey.getTopicId(), metaIndicatorKey.getMetaId()
            );
        }
    }

    @JSONField(name = "topic_id", ordinal = 1)
    private String topicId;

    @JSONField(name = "meta_id", ordinal = 2)
    private String metaId;

    public FastJsonMetaIndicatorKey() {
    }

    public FastJsonMetaIndicatorKey(String topicId, String metaId) {
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

        FastJsonMetaIndicatorKey that = (FastJsonMetaIndicatorKey) o;

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
        return "FastJsonMetaIndicatorKey{" +
                "topicId='" + topicId + '\'' +
                ", metaId='" + metaId + '\'' +
                '}';
    }
}

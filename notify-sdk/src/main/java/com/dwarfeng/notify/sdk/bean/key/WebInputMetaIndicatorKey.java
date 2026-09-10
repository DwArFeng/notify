package com.dwarfeng.notify.sdk.bean.key;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.notify.stack.bean.key.MetaIndicatorKey;
import com.dwarfeng.subgrade.basic.stack.bean.key.Key;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.util.Objects;

/**
 * FastJson 元数据指示器主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class WebInputMetaIndicatorKey implements Key {

    @Serial
    private static final long serialVersionUID = -9086104334476012430L;

    public static MetaIndicatorKey toStackBean(WebInputMetaIndicatorKey webInputMetaIndicatorKey) {
        if (Objects.isNull(webInputMetaIndicatorKey)) {
            return null;
        } else {
            return new MetaIndicatorKey(
                    webInputMetaIndicatorKey.getTopicId(), webInputMetaIndicatorKey.getMetaId()
            );
        }
    }

    @JSONField(name = "topic_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_ID)
    private String topicId;

    @JSONField(name = "meta_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_ID)
    private String metaId;

    public WebInputMetaIndicatorKey() {
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

        WebInputMetaIndicatorKey that = (WebInputMetaIndicatorKey) o;

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
        return "WebInputMetaIndicatorKey{" +
                "topicId='" + topicId + '\'' +
                ", metaId='" + metaId + '\'' +
                '}';
    }
}

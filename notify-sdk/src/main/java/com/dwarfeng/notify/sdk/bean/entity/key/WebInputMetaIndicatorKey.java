package com.dwarfeng.notify.sdk.bean.entity.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.notify.stack.bean.entity.key.MetaIndicatorKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * FastJson 元数据指示器主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class WebInputMetaIndicatorKey implements Key {

    private static final long serialVersionUID = 6520096488908692717L;

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

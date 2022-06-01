package com.dwarfeng.notify.sdk.bean.entity.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.entity.key.SenderRelationKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 发送器关系主键。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputSenderRelationKey implements Key {

    private static final long serialVersionUID = -3915986514109007538L;

    public static SenderRelationKey toStackBean(WebInputSenderRelationKey webInputSenderRelationKey) {
        if (Objects.isNull(webInputSenderRelationKey)) {
            return null;
        } else {
            return new SenderRelationKey(
                    webInputSenderRelationKey.getNotifySettingId(), webInputSenderRelationKey.getTopicId()
            );
        }
    }

    @JSONField(name = "notify_setting_id")
    @NotNull
    private Long notifySettingId;

    @JSONField(name = "topic_id")
    @NotNull
    @NotEmpty
    private String topicId;

    public WebInputSenderRelationKey() {
    }

    public WebInputSenderRelationKey(Long notifySettingId, String topicId) {
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

        WebInputSenderRelationKey that = (WebInputSenderRelationKey) o;

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
        return "WebInputSenderRelationKey{" +
                "notifySettingId=" + notifySettingId +
                ", topicId='" + topicId + '\'' +
                '}';
    }
}

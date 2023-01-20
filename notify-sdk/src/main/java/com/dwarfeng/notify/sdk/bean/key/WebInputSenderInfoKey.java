package com.dwarfeng.notify.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.key.SenderInfoKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 发送器信息主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class WebInputSenderInfoKey implements Key {

    private static final long serialVersionUID = 45962443872371671L;

    public static SenderInfoKey toStackBean(WebInputSenderInfoKey webInputSenderInfoKey) {
        if (Objects.isNull(webInputSenderInfoKey)) {
            return null;
        } else {
            return new SenderInfoKey(
                    webInputSenderInfoKey.getNotifySettingId(), webInputSenderInfoKey.getTopicId()
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

    public WebInputSenderInfoKey() {
    }

    public WebInputSenderInfoKey(Long notifySettingId, String topicId) {
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

        WebInputSenderInfoKey that = (WebInputSenderInfoKey) o;

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
        return "WebInputSenderInfoKey{" +
                "notifySettingId=" + notifySettingId +
                ", topicId='" + topicId + '\'' +
                '}';
    }
}

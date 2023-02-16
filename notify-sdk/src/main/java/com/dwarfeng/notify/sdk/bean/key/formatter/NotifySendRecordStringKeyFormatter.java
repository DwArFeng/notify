package com.dwarfeng.notify.sdk.bean.key.formatter;

import com.dwarfeng.notify.stack.bean.key.NotifySendRecordKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * NotifySendRecordKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class NotifySendRecordStringKeyFormatter implements StringKeyFormatter<NotifySendRecordKey> {

    private String prefix;

    public NotifySendRecordStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(NotifySendRecordKey key) {
        Objects.requireNonNull(key);
        return prefix + key.getNotifyHistoryId() + "_" + key.getTopicId() + "_" + key.getUserId();
    }

    @Override
    public String generalFormat() {
        return prefix + Constants.REDIS_KEY_WILDCARD_CHARACTER;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return "NotifySendRecordStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}

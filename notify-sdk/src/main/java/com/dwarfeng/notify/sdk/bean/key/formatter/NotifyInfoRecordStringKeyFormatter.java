package com.dwarfeng.notify.sdk.bean.key.formatter;

import com.dwarfeng.notify.stack.bean.key.NotifyInfoRecordKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * NotifyInfoRecordKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class NotifyInfoRecordStringKeyFormatter implements StringKeyFormatter<NotifyInfoRecordKey> {

    private String prefix;

    public NotifyInfoRecordStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(NotifyInfoRecordKey key) {
        Objects.requireNonNull(key);
        return prefix + key.getNotifyHistoryId() + "_" + key.getType() + "_" + key.getRecordId();
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
        return "NotifyInfoRecordStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}

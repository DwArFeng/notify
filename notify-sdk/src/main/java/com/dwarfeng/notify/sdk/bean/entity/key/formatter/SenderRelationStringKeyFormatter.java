package com.dwarfeng.notify.sdk.bean.entity.key.formatter;

import com.dwarfeng.notify.stack.bean.entity.key.SenderRelationKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * SenderRelationKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class SenderRelationStringKeyFormatter implements StringKeyFormatter<SenderRelationKey> {

    private String prefix;

    public SenderRelationStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(SenderRelationKey key) {
        Objects.requireNonNull(key);
        return prefix + key.getNotifySettingId() + "_" + key.getTopicId();
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
        return "SenderRelationStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}

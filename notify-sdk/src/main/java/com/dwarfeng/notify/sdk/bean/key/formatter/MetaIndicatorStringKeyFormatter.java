package com.dwarfeng.notify.sdk.bean.key.formatter;

import com.dwarfeng.notify.stack.bean.key.MetaIndicatorKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * MetaIndicatorKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class MetaIndicatorStringKeyFormatter implements StringKeyFormatter<MetaIndicatorKey> {

    private String prefix;

    public MetaIndicatorStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(MetaIndicatorKey key) {
        Objects.requireNonNull(key);
        return prefix + key.getTopicId() + "_" + key.getMetaId();
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
        return "MetaIndicatorStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}

package com.dwarfeng.notify.sdk.bean.key.formatter;

import com.dwarfeng.notify.stack.bean.key.MetaKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * MetaKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class MetaStringKeyFormatter implements StringKeyFormatter<MetaKey> {

    private String prefix;

    public MetaStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(MetaKey key) {
        Objects.requireNonNull(key);
        return prefix + key.getNotifySettingId() + "_" + key.getTopicId() + "_" + key.getUserId() + "_"
                + key.getMetaId();
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
        return "MetaStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}

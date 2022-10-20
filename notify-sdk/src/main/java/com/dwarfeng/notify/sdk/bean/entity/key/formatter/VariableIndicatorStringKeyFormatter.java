package com.dwarfeng.notify.sdk.bean.entity.key.formatter;

import com.dwarfeng.notify.stack.bean.entity.key.VariableIndicatorKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * VariableIndicatorKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class VariableIndicatorStringKeyFormatter implements StringKeyFormatter<VariableIndicatorKey> {

    private String prefix;

    public VariableIndicatorStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(VariableIndicatorKey key) {
        Objects.requireNonNull(key);
        return prefix + key.getTopicId() + "_" + key.getVariableId();
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
        return "VariableIndicatorStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}

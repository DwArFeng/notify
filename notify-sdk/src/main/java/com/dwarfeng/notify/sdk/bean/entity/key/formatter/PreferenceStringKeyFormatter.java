package com.dwarfeng.notify.sdk.bean.entity.key.formatter;

import com.dwarfeng.notify.stack.bean.entity.key.PreferenceKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * PreferenceKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class PreferenceStringKeyFormatter implements StringKeyFormatter<PreferenceKey> {

    private String prefix;

    public PreferenceStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(PreferenceKey key) {
        Objects.requireNonNull(key);
        return prefix + key.getNotifySettingId() + "_" + key.getTopicId() + "_" + key.getUserId() + "_"
                + key.getPreferenceId();
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
        return "PreferenceStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}

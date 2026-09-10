package com.dwarfeng.notify.stack.bean.entity;

import com.dwarfeng.subgrade.basic.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;

import java.io.Serial;

/**
 * 路由器支持。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class RouterSupport implements Entity<StringIdKey> {

    @Serial
    private static final long serialVersionUID = -643403242502190856L;

    private StringIdKey key;
    private String label;
    private String description;
    private String exampleParam;

    public RouterSupport() {
    }

    public RouterSupport(StringIdKey key, String label, String description, String exampleParam) {
        this.key = key;
        this.label = label;
        this.description = description;
        this.exampleParam = exampleParam;
    }

    @Override
    public StringIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(StringIdKey key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExampleParam() {
        return exampleParam;
    }

    public void setExampleParam(String exampleParam) {
        this.exampleParam = exampleParam;
    }

    @Override
    public String toString() {
        return "RouterSupport{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", exampleParam='" + exampleParam + '\'' +
                '}';
    }
}

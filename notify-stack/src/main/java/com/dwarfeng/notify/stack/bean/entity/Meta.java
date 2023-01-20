package com.dwarfeng.notify.stack.bean.entity;

import com.dwarfeng.notify.stack.bean.key.MetaKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

/**
 * 元数据。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class Meta implements Entity<MetaKey> {

    private static final long serialVersionUID = 1597526375281291335L;
    
    private MetaKey key;
    private String value;
    private String remark;

    public Meta() {
    }

    public Meta(MetaKey key, String value, String remark) {
        this.key = key;
        this.value = value;
        this.remark = remark;
    }

    @Override
    public MetaKey getKey() {
        return key;
    }

    @Override
    public void setKey(MetaKey key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

package com.dwarfeng.notify.stack.bean.entity;

import com.dwarfeng.subgrade.basic.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;

import java.io.Serial;

/**
 * 调度器信息。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class DispatcherInfo implements Entity<StringIdKey> {

    @Serial
    private static final long serialVersionUID = 7991125436337110130L;

    private StringIdKey key;
    private String label;
    private String type;
    private String param;
    private String remark;

    public DispatcherInfo() {
    }

    public DispatcherInfo(StringIdKey key, String label, String type, String param, String remark) {
        this.key = key;
        this.label = label;
        this.type = type;
        this.param = param;
        this.remark = remark;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SenderInfo{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

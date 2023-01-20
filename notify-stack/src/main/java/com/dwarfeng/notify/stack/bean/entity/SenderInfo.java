package com.dwarfeng.notify.stack.bean.entity;

import com.dwarfeng.notify.stack.bean.key.SenderInfoKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

/**
 * 发送器信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class SenderInfo implements Entity<SenderInfoKey> {

    private static final long serialVersionUID = -6590807531984924592L;
    
    private SenderInfoKey key;
    private String label;
    private String type;
    private String param;
    private String remark;

    public SenderInfo() {
    }

    public SenderInfo(SenderInfoKey key, String label, String type, String param, String remark) {
        this.key = key;
        this.label = label;
        this.type = type;
        this.param = param;
        this.remark = remark;
    }

    @Override
    public SenderInfoKey getKey() {
        return key;
    }

    @Override
    public void setKey(SenderInfoKey key) {
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

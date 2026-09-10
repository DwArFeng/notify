package com.dwarfeng.notify.impl.bean.entity;

import com.dwarfeng.datamark.sdk.jpa.DatamarkField;
import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.subgrade.basic.stack.bean.Bean;
import com.dwarfeng.subgrade.data.sdk.bean.key.hibernate.HibernateLongIdKey;
import jakarta.persistence.*;

import java.io.Serial;
import java.util.Optional;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_router_info")
public class HibernateRouterInfo implements Bean {

    @Serial
    private static final long serialVersionUID = -1266452926871141998L;

    // region 主键

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // endregion

    // region 主属性字段

    @Column(name = "label", length = Constraints.LENGTH_LABEL)
    private String label;

    @Column(name = "type", length = Constraints.LENGTH_TYPE)
    private String type;

    @Column(name = "param", columnDefinition = "TEXT")
    private String param;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // endregion

    // region 一对一

    @OneToOne(targetEntity = HibernateNotifySetting.class)
    @JoinColumns({ //
            @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateNotifySetting notifySetting;

    // endregion

    // region 审计

    @DatamarkField(handlerName = "notifySettingDatamarkHandler")
    @Column(
            name = "created_datamark",
            length = com.dwarfeng.datamark.sdk.util.Constraints.LENGTH_DATAMARK_VALUE,
            updatable = false
    )
    private String createdDatamark;

    @DatamarkField(handlerName = "notifySettingDatamarkHandler")
    @Column(
            name = "modified_datamark",
            length = com.dwarfeng.datamark.sdk.util.Constraints.LENGTH_DATAMARK_VALUE
    )
    private String modifiedDatamark;

    // endregion

    public HibernateRouterInfo() {
    }

    // region 映射用属性区

    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey key) {
        this.longId = Optional.ofNullable(key).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // endregion

    // region 常规属性区

    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
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

    public HibernateNotifySetting getNotifySetting() {
        return notifySetting;
    }

    public void setNotifySetting(HibernateNotifySetting notifySetting) {
        this.notifySetting = notifySetting;
    }

    public String getCreatedDatamark() {
        return createdDatamark;
    }

    public void setCreatedDatamark(String createdDatamark) {
        this.createdDatamark = createdDatamark;
    }

    public String getModifiedDatamark() {
        return modifiedDatamark;
    }

    public void setModifiedDatamark(String modifiedDatamark) {
        this.modifiedDatamark = modifiedDatamark;
    }

    // endregion

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "label = " + label + ", " +
                "type = " + type + ", " +
                "param = " + param + ", " +
                "remark = " + remark + ", " +
                "notifySetting = " + notifySetting + ", " +
                "createdDatamark = " + createdDatamark + ", " +
                "modifiedDatamark = " + modifiedDatamark + ")";
    }
}

package com.dwarfeng.notify.impl.bean.entity;

import com.dwarfeng.notify.sdk.util.Constraints;
import com.dwarfeng.subgrade.basic.stack.bean.Bean;
import com.dwarfeng.subgrade.data.sdk.bean.key.hibernate.HibernateStringIdKey;
import jakarta.persistence.*;

import java.io.Serial;
import java.util.Optional;

@Entity
@IdClass(HibernateStringIdKey.class)
@Table(name = "tbl_dispatcher_support")
public class HibernateDispatcherSupport implements Bean {

    @Serial
    private static final long serialVersionUID = 231327022726321314L;

    // region 主键

    @Id
    @Column(name = "id", length = Constraints.LENGTH_TYPE, nullable = false, unique = true)
    private String stringId;

    // endregion

    // region 主属性字段

    @Column(name = "label", length = 50, nullable = false)
    private String label;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "example_param", columnDefinition = "TEXT")
    private String exampleParam;

    // endregion

    public HibernateDispatcherSupport() {
    }

    // region 映射用属性区

    public HibernateStringIdKey getKey() {
        return Optional.ofNullable(stringId).map(HibernateStringIdKey::new).orElse(null);
    }

    public void setKey(HibernateStringIdKey idKey) {
        this.stringId = Optional.ofNullable(idKey).map(HibernateStringIdKey::getStringId).orElse(null);
    }

    // endregion

    // region 常规属性区

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
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

    // endregion

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "stringId = " + stringId + ", " +
                "label = " + label + ", " +
                "description = " + description + ", " +
                "exampleParam = " + exampleParam + ")";
    }
}

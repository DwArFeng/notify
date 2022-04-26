package com.dwarfeng.notify.sdk.util;

/**
 * 约束类。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public final class Constraints {

    /**
     * 类型的长度约束。
     */
    public static final int LENGTH_TYPE = 50;

    /**
     * 备注的长度约束。
     */
    public static final int LENGTH_REMARK = 100;

    private Constraints() {
        throw new IllegalStateException("禁止实例化");
    }
}

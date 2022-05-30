package com.dwarfeng.notify.sdk.util;

import com.dwarfeng.subgrade.stack.exception.ServiceException;

/**
 * 服务异常代码。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public final class ServiceExceptionCodes {

    private static int EXCEPTION_CODE_OFFSET = 5500;

    public static final ServiceException.Code ROUTER_FAILED =
            new ServiceException.Code(offset(0), "router failed");
    public static final ServiceException.Code ROUTER_EXECUTION_FAILED =
            new ServiceException.Code(offset(1), "router execution failed");
    public static final ServiceException.Code ROUTER_MAKE_FAILED =
            new ServiceException.Code(offset(2), "router make failed");
    public static final ServiceException.Code UNSUPPORTED_ROUTER_TYPE =
            new ServiceException.Code(offset(3), "unsupported router type");
    public static final ServiceException.Code SENDER_FAILED =
            new ServiceException.Code(offset(10), "sender failed");
    public static final ServiceException.Code SENDER_EXECUTION_FAILED =
            new ServiceException.Code(offset(11), "sender execution failed");
    public static final ServiceException.Code SENDER_MAKE_FAILED =
            new ServiceException.Code(offset(12), "sender make failed");
    public static final ServiceException.Code UNSUPPORTED_SENDER_TYPE =
            new ServiceException.Code(offset(13), "unsupported sender type");
    public static final ServiceException.Code NOTIFY_SETTING_NOT_EXISTED =
            new ServiceException.Code(offset(20), "notify setting not existed");
    public static final ServiceException.Code TOPIC_NOT_EXISTED =
            new ServiceException.Code(offset(30), "topic not existed");
    public static final ServiceException.Code USER_NOT_EXISTED =
            new ServiceException.Code(offset(40), "user not existed");

    private static int offset(int i) {
        return EXCEPTION_CODE_OFFSET + i;
    }

    /**
     * 获取异常代号的偏移量。
     *
     * @return 异常代号的偏移量。
     */
    public static int getExceptionCodeOffset() {
        return EXCEPTION_CODE_OFFSET;
    }

    /**
     * 设置异常代号的偏移量。
     *
     * @param exceptionCodeOffset 指定的异常代号的偏移量。
     */
    public static void setExceptionCodeOffset(int exceptionCodeOffset) {
        // 设置 EXCEPTION_CODE_OFFSET 的值。
        EXCEPTION_CODE_OFFSET = exceptionCodeOffset;

        // 以新的 EXCEPTION_CODE_OFFSET 为基准，更新异常代码的值。
        ROUTER_FAILED.setCode(offset(0));
        ROUTER_EXECUTION_FAILED.setCode(offset(1));
        ROUTER_MAKE_FAILED.setCode(offset(2));
        UNSUPPORTED_ROUTER_TYPE.setCode(offset(3));
        SENDER_FAILED.setCode(offset(10));
        SENDER_EXECUTION_FAILED.setCode(offset(11));
        SENDER_MAKE_FAILED.setCode(offset(12));
        UNSUPPORTED_SENDER_TYPE.setCode(offset(13));
        NOTIFY_SETTING_NOT_EXISTED.setCode(offset(20));
        TOPIC_NOT_EXISTED.setCode(offset(30));
        USER_NOT_EXISTED.setCode(offset(40));
    }

    private ServiceExceptionCodes() {
        throw new IllegalStateException("禁止实例化");
    }
}

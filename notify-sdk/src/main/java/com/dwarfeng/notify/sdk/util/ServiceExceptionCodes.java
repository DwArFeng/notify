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

    public static final ServiceException.Code PROMPTER_FAILED =
            new ServiceException.Code(offset(0), "prompter failed");
    public static final ServiceException.Code PROMPTER_MAKE_FAILED =
            new ServiceException.Code(offset(1), "prompter make failed");
    public static final ServiceException.Code PROMPTER_TYPE_UNSUPPORTED =
            new ServiceException.Code(offset(2), "prompter type unsupported");
    public static final ServiceException.Code NOTIFY_HANDLER_STOPPED =
            new ServiceException.Code(offset(10), "notify handler stopped");
    public static final ServiceException.Code SCHEDULED_CLEAR_HANDLER_STOPPED =
            new ServiceException.Code(offset(20), "scheduled clear handler stopped");

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
        PROMPTER_FAILED.setCode(offset(0));
        PROMPTER_MAKE_FAILED.setCode(offset(1));
        PROMPTER_TYPE_UNSUPPORTED.setCode(offset(2));
        NOTIFY_HANDLER_STOPPED.setCode(offset(10));
        SCHEDULED_CLEAR_HANDLER_STOPPED.setCode(offset(20));
    }

    private ServiceExceptionCodes() {
        throw new IllegalStateException("禁止实例化");
    }
}

package com.dwarfeng.notify.sdk.exception;

import com.dwarfeng.notify.sdk.internal.i18n.SdkMessageKey;
import com.dwarfeng.notify.sdk.internal.i18n.SdkMessages;
import com.dwarfeng.subgrade.basic.stack.exception.ServiceException;

import java.util.function.Supplier;

/**
 * Notify 模块服务异常代码供应器。
 *
 * @author DwArFeng
 * @since 4.0.0
 */
public final class ServiceExceptionCodeSuppliers {

    private static volatile int EXCEPTION_CODE_OFFSET = 5500;

    public static final Supplier<ServiceException.Code> ROUTER_FAILED = () -> new ServiceException.Code(
            offset(0), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_ROUTER_FAILED)
    );
    public static final Supplier<ServiceException.Code> ROUTER_EXECUTION_FAILED = () -> new ServiceException.Code(
            offset(1), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_ROUTER_EXECUTION_FAILED)
    );
    public static final Supplier<ServiceException.Code> ROUTER_MAKE_FAILED = () -> new ServiceException.Code(
            offset(2), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_ROUTER_MAKE_FAILED)
    );
    public static final Supplier<ServiceException.Code> UNSUPPORTED_ROUTER_TYPE = () -> new ServiceException.Code(
            offset(3), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_UNSUPPORTED_ROUTER_TYPE)
    );
    public static final Supplier<ServiceException.Code> SENDER_FAILED = () -> new ServiceException.Code(
            offset(10), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_SENDER_FAILED)
    );
    public static final Supplier<ServiceException.Code> SENDER_EXECUTION_FAILED = () -> new ServiceException.Code(
            offset(11), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_SENDER_EXECUTION_FAILED)
    );
    public static final Supplier<ServiceException.Code> SENDER_MAKE_FAILED = () -> new ServiceException.Code(
            offset(12), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_SENDER_MAKE_FAILED)
    );
    public static final Supplier<ServiceException.Code> UNSUPPORTED_SENDER_TYPE = () -> new ServiceException.Code(
            offset(13), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_UNSUPPORTED_SENDER_TYPE)
    );
    public static final Supplier<ServiceException.Code> NOTIFY_SETTING_NOT_EXISTED = () -> new ServiceException.Code(
            offset(20), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_NOTIFY_SETTING_NOT_EXISTED)
    );
    public static final Supplier<ServiceException.Code> TOPIC_NOT_EXISTED = () -> new ServiceException.Code(
            offset(30), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_TOPIC_NOT_EXISTED)
    );
    public static final Supplier<ServiceException.Code> USER_NOT_EXISTED = () -> new ServiceException.Code(
            offset(40), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_USER_NOT_EXISTED)
    );
    public static final Supplier<ServiceException.Code> NOTIFY_SETTING_DISABLED = () -> new ServiceException.Code(
            offset(50), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_NOTIFY_SETTING_DISABLED)
    );
    public static final Supplier<ServiceException.Code> DISPATCHER_FAILED = () -> new ServiceException.Code(
            offset(60), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_DISPATCHER_FAILED)
    );
    public static final Supplier<ServiceException.Code> DISPATCHER_EXECUTION_FAILED = () -> new ServiceException.Code(
            offset(61), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_DISPATCHER_EXECUTION_FAILED)
    );
    public static final Supplier<ServiceException.Code> DISPATCHER_MAKE_FAILED = () -> new ServiceException.Code(
            offset(62), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_DISPATCHER_MAKE_FAILED)
    );
    public static final Supplier<ServiceException.Code> UNSUPPORTED_DISPATCHER_TYPE = () -> new ServiceException.Code(
            offset(63), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_UNSUPPORTED_DISPATCHER_TYPE)
    );
    public static final Supplier<ServiceException.Code> ROUTER_INFO_NOT_EXISTED = () -> new ServiceException.Code(
            offset(70), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_ROUTER_INFO_NOT_EXISTED)
    );
    public static final Supplier<ServiceException.Code> DISPATCHER_INFO_NOT_EXISTED = () -> new ServiceException.Code(
            offset(80), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_DISPATCHER_INFO_NOT_EXISTED)
    );
    public static final Supplier<ServiceException.Code> SENDER_INFO_NOT_EXISTED = () -> new ServiceException.Code(
            offset(90), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_SENDER_INFO_NOT_EXISTED)
    );

    private static int offset(int code) {
        return EXCEPTION_CODE_OFFSET + code;
    }

    public static int getExceptionCodeOffset() {
        return EXCEPTION_CODE_OFFSET;
    }

    public static void setExceptionCodeOffset(int exceptionCodeOffset) {
        EXCEPTION_CODE_OFFSET = exceptionCodeOffset;
    }

    private ServiceExceptionCodeSuppliers() {
        throw new IllegalStateException("禁止实例化");
    }
}

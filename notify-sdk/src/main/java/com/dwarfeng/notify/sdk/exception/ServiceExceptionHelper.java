package com.dwarfeng.notify.sdk.exception;

import com.dwarfeng.notify.stack.exception.*;
import com.dwarfeng.subgrade.basic.stack.exception.ServiceException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Notify 模块服务异常帮助类。
 *
 * @author DwArFeng
 * @since 4.0.0
 */
public final class ServiceExceptionHelper {

    public static Map<Class<? extends Exception>, Supplier<ServiceException.Code>> putDefaultDestination(
            Map<Class<? extends Exception>, Supplier<ServiceException.Code>> map
    ) {
        if (Objects.isNull(map)) {
            map = new HashMap<>();
        }
        map.put(RouterException.class, ServiceExceptionCodeSuppliers.ROUTER_FAILED);
        map.put(RouterExecutionException.class, ServiceExceptionCodeSuppliers.ROUTER_EXECUTION_FAILED);
        map.put(RouterMakeException.class, ServiceExceptionCodeSuppliers.ROUTER_MAKE_FAILED);
        map.put(UnsupportedRouterTypeException.class, ServiceExceptionCodeSuppliers.UNSUPPORTED_ROUTER_TYPE);
        map.put(SenderException.class, ServiceExceptionCodeSuppliers.SENDER_FAILED);
        map.put(SenderExecutionException.class, ServiceExceptionCodeSuppliers.SENDER_EXECUTION_FAILED);
        map.put(SenderMakeException.class, ServiceExceptionCodeSuppliers.SENDER_MAKE_FAILED);
        map.put(UnsupportedSenderTypeException.class, ServiceExceptionCodeSuppliers.UNSUPPORTED_SENDER_TYPE);
        map.put(NotifySettingNotExistsException.class, ServiceExceptionCodeSuppliers.NOTIFY_SETTING_NOT_EXISTED);
        map.put(TopicNotExistsException.class, ServiceExceptionCodeSuppliers.TOPIC_NOT_EXISTED);
        map.put(UserNotExistsException.class, ServiceExceptionCodeSuppliers.USER_NOT_EXISTED);
        map.put(NotifySettingDisabledException.class, ServiceExceptionCodeSuppliers.NOTIFY_SETTING_DISABLED);
        map.put(DispatcherException.class, ServiceExceptionCodeSuppliers.DISPATCHER_FAILED);
        map.put(DispatcherExecutionException.class, ServiceExceptionCodeSuppliers.DISPATCHER_EXECUTION_FAILED);
        map.put(DispatcherMakeException.class, ServiceExceptionCodeSuppliers.DISPATCHER_MAKE_FAILED);
        map.put(UnsupportedDispatcherTypeException.class, ServiceExceptionCodeSuppliers.UNSUPPORTED_DISPATCHER_TYPE);
        map.put(RouterInfoNotExistsException.class, ServiceExceptionCodeSuppliers.ROUTER_INFO_NOT_EXISTED);
        map.put(DispatcherInfoNotExistsException.class, ServiceExceptionCodeSuppliers.DISPATCHER_INFO_NOT_EXISTED);
        map.put(SenderInfoNotExistsException.class, ServiceExceptionCodeSuppliers.SENDER_INFO_NOT_EXISTED);
        return map;
    }

    private ServiceExceptionHelper() {
        throw new IllegalStateException("禁止外部实例化");
    }
}

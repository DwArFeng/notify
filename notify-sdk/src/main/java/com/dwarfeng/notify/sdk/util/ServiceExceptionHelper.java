package com.dwarfeng.notify.sdk.util;

import com.dwarfeng.notify.stack.exception.*;
import com.dwarfeng.subgrade.stack.exception.ServiceException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 异常的帮助工具类。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public final class ServiceExceptionHelper {

    /**
     * 向指定的映射中添加 notify 默认的目标映射。
     *
     * <p>
     * 该方法可以在配置类中快速的搭建目标映射。
     *
     * @param map 指定的映射，允许为 <code>null</code>。
     * @return 添加了默认目标的映射。
     */
    public static Map<Class<? extends Exception>, ServiceException.Code> putDefaultDestination(
            Map<Class<? extends Exception>, ServiceException.Code> map
    ) {
        if (Objects.isNull(map)) {
            map = new HashMap<>();
        }

        map.put(RouterException.class, ServiceExceptionCodes.ROUTER_FAILED);
        map.put(RouterExecutionException.class, ServiceExceptionCodes.ROUTER_EXECUTION_FAILED);
        map.put(RouterMakeException.class, ServiceExceptionCodes.ROUTER_MAKE_FAILED);
        map.put(UnsupportedRouterTypeException.class, ServiceExceptionCodes.UNSUPPORTED_ROUTER_TYPE);
        map.put(SenderException.class, ServiceExceptionCodes.SENDER_FAILED);
        map.put(SenderExecutionException.class, ServiceExceptionCodes.SENDER_EXECUTION_FAILED);
        map.put(SenderMakeException.class, ServiceExceptionCodes.SENDER_MAKE_FAILED);
        map.put(UnsupportedSenderTypeException.class, ServiceExceptionCodes.UNSUPPORTED_SENDER_TYPE);
        map.put(NotifySettingNotExistsException.class, ServiceExceptionCodes.NOTIFY_SETTING_NOT_EXISTED);
        map.put(TopicNotExistsException.class, ServiceExceptionCodes.TOPIC_NOT_EXISTED);
        map.put(UserNotExistsException.class, ServiceExceptionCodes.USER_NOT_EXISTED);
        map.put(NotifySettingDisabledException.class, ServiceExceptionCodes.NOTIFY_SETTING_DISABLED);
        map.put(DispatcherException.class, ServiceExceptionCodes.DISPATCHER_FAILED);
        map.put(DispatcherExecutionException.class, ServiceExceptionCodes.DISPATCHER_EXECUTION_FAILED);
        map.put(DispatcherMakeException.class, ServiceExceptionCodes.DISPATCHER_MAKE_FAILED);
        map.put(UnsupportedDispatcherTypeException.class, ServiceExceptionCodes.UNSUPPORTED_DISPATCHER_TYPE);
        map.put(RouterInfoNotExistsException.class, ServiceExceptionCodes.ROUTER_INFO_NOT_EXISTED);
        map.put(DispatcherInfoNotExistsException.class, ServiceExceptionCodes.DISPATCHER_INFO_NOT_EXISTED);
        map.put(SenderInfoNotExistsException.class, ServiceExceptionCodes.SENDER_INFO_NOT_EXISTED);
        return map;
    }

    private ServiceExceptionHelper() {
        throw new IllegalStateException("禁止外部实例化");
    }
}

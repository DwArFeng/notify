package com.dwarfeng.notify.node.configuration;

import com.dwarfeng.notify.sdk.util.ServiceExceptionCodes;
import com.dwarfeng.notify.stack.exception.*;
import com.dwarfeng.subgrade.impl.exception.MapServiceExceptionMapper;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ServiceExceptionMapperConfiguration {

    @Bean
    public MapServiceExceptionMapper mapServiceExceptionMapper() {
        Map<Class<? extends Exception>, ServiceException.Code> destination = ServiceExceptionHelper.putDefaultDestination(null);
        destination.put(RouterException.class, ServiceExceptionCodes.ROUTER_FAILED);
        destination.put(RouterExecutionException.class, ServiceExceptionCodes.ROUTER_EXECUTION_FAILED);
        destination.put(RouterMakeException.class, ServiceExceptionCodes.ROUTER_MAKE_FAILED);
        destination.put(UnsupportedRouterTypeException.class, ServiceExceptionCodes.UNSUPPORTED_ROUTER_TYPE);
        destination.put(SenderException.class, ServiceExceptionCodes.SENDER_FAILED);
        destination.put(SenderExecutionException.class, ServiceExceptionCodes.SENDER_EXECUTION_FAILED);
        destination.put(SenderMakeException.class, ServiceExceptionCodes.SENDER_MAKE_FAILED);
        destination.put(UnsupportedSenderTypeException.class, ServiceExceptionCodes.UNSUPPORTED_SENDER_TYPE);
        destination.put(NotifySettingNotExistsException.class, ServiceExceptionCodes.NOTIFY_SETTING_NOT_EXISTED);
        destination.put(TopicNotExistsException.class, ServiceExceptionCodes.TOPIC_NOT_EXISTED);
        destination.put(UserNotExistsException.class, ServiceExceptionCodes.USER_NOT_EXISTED);
        destination.put(NotifySettingDisabledException.class, ServiceExceptionCodes.NOTIFY_SETTING_DISABLED);
        return new MapServiceExceptionMapper(destination, com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.UNDEFINE);
    }
}

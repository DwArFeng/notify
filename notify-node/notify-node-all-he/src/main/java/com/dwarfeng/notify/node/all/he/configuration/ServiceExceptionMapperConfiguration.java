package com.dwarfeng.notify.node.all.he.configuration;

import com.dwarfeng.notify.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.basic.impl.exception.MapServiceExceptionMapper;
import com.dwarfeng.subgrade.basic.stack.exception.ServiceException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.function.Supplier;

@Configuration
public class ServiceExceptionMapperConfiguration {

    @Bean
    public MapServiceExceptionMapper mapServiceExceptionMapper() {
        Map<Class<? extends Exception>, Supplier<ServiceException.Code>> des =
                com.dwarfeng.subgrade.basic.sdk.exception.ServiceExceptionHelper.putDefaultDestination(null);
        des = com.dwarfeng.springtelqos.sdk.exception.ServiceExceptionHelper.putDefaultDestination(des);
        des = com.dwarfeng.springterminator.sdk.exception.ServiceExceptionHelper.putDefaultDestination(des);
        des = com.dwarfeng.datamark.sdk.exception.ServiceExceptionHelper.putDefaultDestination(des);
        des = ServiceExceptionHelper.putDefaultDestination(des);
        return new MapServiceExceptionMapper(
                des, com.dwarfeng.subgrade.basic.sdk.exception.ServiceExceptionCodeSuppliers.UNDEFINED
        );
    }
}

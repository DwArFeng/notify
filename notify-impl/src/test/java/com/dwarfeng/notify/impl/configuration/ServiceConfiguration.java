package com.dwarfeng.notify.impl.configuration;

import com.dwarfeng.notify.stack.bean.entity.User;
import com.dwarfeng.notify.stack.cache.UserCache;
import com.dwarfeng.notify.stack.dao.UserDao;
import com.dwarfeng.subgrade.impl.bean.key.ExceptionKeyFetcher;
import com.dwarfeng.subgrade.impl.service.DaoOnlyEntireLookupService;
import com.dwarfeng.subgrade.impl.service.GeneralBatchCrudService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    private final ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration;

    private final UserDao userDao;
    private final UserCache userCache;

    @Value("${cache.timeout.entity.user}")
    private long userTimeout;

    public ServiceConfiguration(
            ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration,
            UserDao userDao, UserCache userCache
    ) {
        this.serviceExceptionMapperConfiguration = serviceExceptionMapperConfiguration;
        this.userDao = userDao;
        this.userCache = userCache;
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, User> userCustomBatchCrudService() {
        return new GeneralBatchCrudService<>(
                userDao,
                userCache,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                userTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<User> userDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                userDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }
}

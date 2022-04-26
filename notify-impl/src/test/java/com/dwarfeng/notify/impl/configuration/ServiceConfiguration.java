package com.dwarfeng.notify.impl.configuration;

import com.dwarfeng.notify.impl.service.operation.NotifySettingCrudOperation;
import com.dwarfeng.notify.impl.service.operation.RouterInfoCrudOperation;
import com.dwarfeng.notify.stack.bean.entity.NotifySetting;
import com.dwarfeng.notify.stack.bean.entity.RouterInfo;
import com.dwarfeng.notify.stack.bean.entity.RouterSupport;
import com.dwarfeng.notify.stack.bean.entity.User;
import com.dwarfeng.notify.stack.cache.RouterSupportCache;
import com.dwarfeng.notify.stack.cache.UserCache;
import com.dwarfeng.notify.stack.dao.NotifySettingDao;
import com.dwarfeng.notify.stack.dao.RouterInfoDao;
import com.dwarfeng.notify.stack.dao.RouterSupportDao;
import com.dwarfeng.notify.stack.dao.UserDao;
import com.dwarfeng.sfds.api.integration.subgrade.SnowFlakeLongIdKeyFetcher;
import com.dwarfeng.subgrade.impl.bean.key.ExceptionKeyFetcher;
import com.dwarfeng.subgrade.impl.service.CustomBatchCrudService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyEntireLookupService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyPresetLookupService;
import com.dwarfeng.subgrade.impl.service.GeneralBatchCrudService;
import com.dwarfeng.subgrade.stack.bean.key.KeyFetcher;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
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
    private final RouterInfoCrudOperation routerInfoCrudOperation;
    private final RouterInfoDao routerInfoDao;
    private final RouterSupportDao routerSupportDao;
    private final RouterSupportCache routerSupportCache;
    private final NotifySettingCrudOperation notifySettingCrudOperation;
    private final NotifySettingDao notifySettingDao;

    @Value("${cache.timeout.entity.user}")
    private long userTimeout;
    @Value("${cache.timeout.entity.router_support}")
    private long routerSupportTimeout;

    public ServiceConfiguration(
            ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration,
            UserDao userDao, UserCache userCache,
            RouterInfoCrudOperation routerInfoCrudOperation, RouterInfoDao routerInfoDao,
            RouterSupportDao routerSupportDao, RouterSupportCache routerSupportCache,
            NotifySettingCrudOperation notifySettingCrudOperation, NotifySettingDao notifySettingDao
    ) {
        this.serviceExceptionMapperConfiguration = serviceExceptionMapperConfiguration;
        this.userDao = userDao;
        this.userCache = userCache;
        this.routerInfoCrudOperation = routerInfoCrudOperation;
        this.routerInfoDao = routerInfoDao;
        this.routerSupportDao = routerSupportDao;
        this.routerSupportCache = routerSupportCache;
        this.notifySettingCrudOperation = notifySettingCrudOperation;
        this.notifySettingDao = notifySettingDao;
    }

    @Bean
    public KeyFetcher<LongIdKey> longIdKeyKeyFetcher() {
        return new SnowFlakeLongIdKeyFetcher();
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

    @Bean
    public CustomBatchCrudService<LongIdKey, RouterInfo> routerInfoCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                routerInfoCrudOperation,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<RouterInfo> routerInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                routerInfoDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<RouterInfo> routerInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                routerInfoDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, RouterSupport> routerSupportGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                routerSupportDao,
                routerSupportCache,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                routerSupportTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<RouterSupport> routerSupportDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                routerSupportDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<RouterSupport> routerSupportDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                routerSupportDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, NotifySetting> notifySettingCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                notifySettingCrudOperation,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<NotifySetting> notifySettingDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                notifySettingDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<NotifySetting> notifySettingDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                notifySettingDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }
}

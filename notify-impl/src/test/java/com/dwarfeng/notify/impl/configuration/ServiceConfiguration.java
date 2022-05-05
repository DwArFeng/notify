package com.dwarfeng.notify.impl.configuration;

import com.dwarfeng.notify.impl.service.operation.NotifySettingCrudOperation;
import com.dwarfeng.notify.impl.service.operation.SenderInfoCrudOperation;
import com.dwarfeng.notify.impl.service.operation.TopicCrudOperation;
import com.dwarfeng.notify.stack.bean.entity.*;
import com.dwarfeng.notify.stack.cache.RouterInfoCache;
import com.dwarfeng.notify.stack.cache.RouterSupportCache;
import com.dwarfeng.notify.stack.cache.SenderSupportCache;
import com.dwarfeng.notify.stack.cache.UserCache;
import com.dwarfeng.notify.stack.dao.*;
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
    private final RouterInfoDao routerInfoDao;
    private final RouterInfoCache routerInfoCache;
    private final RouterSupportDao routerSupportDao;
    private final RouterSupportCache routerSupportCache;
    private final NotifySettingCrudOperation notifySettingCrudOperation;
    private final NotifySettingDao notifySettingDao;
    private final SenderInfoCrudOperation senderInfoCrudOperation;
    private final SenderInfoDao senderInfoDao;
    private final SenderSupportDao senderSupportDao;
    private final SenderSupportCache senderSupportCache;
    private final TopicCrudOperation topicCrudOperation;
    private final TopicDao topicDao;

    @Value("${cache.timeout.entity.user}")
    private long userTimeout;
    @Value("${cache.timeout.entity.router_info}")
    private long routerInfoTimeout;
    @Value("${cache.timeout.entity.router_support}")
    private long routerSupportTimeout;
    @Value("${cache.timeout.entity.sender_support}")
    private long senderSupportTimeout;

    public ServiceConfiguration(
            ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration,
            UserDao userDao, UserCache userCache,
            RouterInfoDao routerInfoDao, RouterInfoCache routerInfoCache,
            RouterSupportDao routerSupportDao, RouterSupportCache routerSupportCache,
            NotifySettingCrudOperation notifySettingCrudOperation, NotifySettingDao notifySettingDao,
            SenderInfoCrudOperation senderInfoCrudOperation, SenderInfoDao senderInfoDao,
            SenderSupportDao senderSupportDao, SenderSupportCache senderSupportCache,
            TopicCrudOperation topicCrudOperation, TopicDao topicDao
    ) {
        this.serviceExceptionMapperConfiguration = serviceExceptionMapperConfiguration;
        this.userDao = userDao;
        this.userCache = userCache;
        this.routerInfoDao = routerInfoDao;
        this.routerInfoCache = routerInfoCache;
        this.routerSupportDao = routerSupportDao;
        this.routerSupportCache = routerSupportCache;
        this.notifySettingCrudOperation = notifySettingCrudOperation;
        this.notifySettingDao = notifySettingDao;
        this.senderInfoCrudOperation = senderInfoCrudOperation;
        this.senderInfoDao = senderInfoDao;
        this.senderSupportDao = senderSupportDao;
        this.senderSupportCache = senderSupportCache;
        this.topicCrudOperation = topicCrudOperation;
        this.topicDao = topicDao;
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
    public GeneralBatchCrudService<LongIdKey, RouterInfo> routerInfoGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                routerInfoDao,
                routerInfoCache,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                routerInfoTimeout
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

    @Bean
    public CustomBatchCrudService<LongIdKey, SenderInfo> senderInfoCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                senderInfoCrudOperation,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<SenderInfo> senderInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                senderInfoDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<SenderInfo> senderInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                senderInfoDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, SenderSupport> senderSupportGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                senderSupportDao,
                senderSupportCache,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                senderSupportTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<SenderSupport> senderSupportDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                senderSupportDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<SenderSupport> senderSupportDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                senderSupportDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public CustomBatchCrudService<StringIdKey, Topic> topicCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                topicCrudOperation,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Topic> topicDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                topicDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Topic> topicDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                topicDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }
}

package com.dwarfeng.notify.impl.configuration;

import com.dwarfeng.notify.impl.service.operation.NotifySettingCrudOperation;
import com.dwarfeng.notify.impl.service.operation.TopicCrudOperation;
import com.dwarfeng.notify.impl.service.operation.UserCrudOperation;
import com.dwarfeng.notify.stack.bean.entity.*;
import com.dwarfeng.notify.stack.bean.entity.key.PreferenceIndicatorKey;
import com.dwarfeng.notify.stack.bean.entity.key.PreferenceKey;
import com.dwarfeng.notify.stack.bean.entity.key.SenderInfoKey;
import com.dwarfeng.notify.stack.bean.entity.key.VariableKey;
import com.dwarfeng.notify.stack.cache.*;
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

    private final UserCrudOperation userCrudOperation;
    private final UserDao userDao;
    private final RouterInfoDao routerInfoDao;
    private final RouterInfoCache routerInfoCache;
    private final RouterSupportDao routerSupportDao;
    private final RouterSupportCache routerSupportCache;
    private final NotifySettingCrudOperation notifySettingCrudOperation;
    private final NotifySettingDao notifySettingDao;
    private final SenderInfoDao senderInfoDao;
    private final SenderInfoCache senderInfoCache;
    private final SenderSupportDao senderSupportDao;
    private final SenderSupportCache senderSupportCache;
    private final TopicCrudOperation topicCrudOperation;
    private final TopicDao topicDao;
    private final DispatcherInfoDao dispatcherInfoDao;
    private final DispatcherInfoCache dispatcherInfoCache;
    private final DispatcherSupportDao dispatcherSupportDao;
    private final DispatcherSupportCache dispatcherSupportCache;
    private final PreferenceDao preferenceDao;
    private final PreferenceCache preferenceCache;
    private final VariableDao variableDao;
    private final VariableCache variableCache;
    private final SendHistoryDao sendHistoryDao;
    private final SendHistoryCache sendHistoryCache;
    private final PreferenceIndicatorDao preferenceIndicatorDao;
    private final PreferenceIndicatorCache preferenceIndicatorCache;

    @Value("${cache.timeout.entity.router_info}")
    private long routerInfoTimeout;
    @Value("${cache.timeout.entity.router_support}")
    private long routerSupportTimeout;
    @Value("${cache.timeout.entity.sender_info}")
    private long senderInfoTimeout;
    @Value("${cache.timeout.entity.sender_support}")
    private long senderSupportTimeout;
    @Value("${cache.timeout.entity.dispatcher_info}")
    private long dispatcherInfoTimeout;
    @Value("${cache.timeout.entity.dispatcher_support}")
    private long dispatcherSupportTimeout;
    @Value("${cache.timeout.entity.preference}")
    private long preferenceTimeout;
    @Value("${cache.timeout.entity.variable}")
    private long variableTimeout;
    @Value("${cache.timeout.entity.send_history}")
    private long sendHistoryTimeout;
    @Value("${cache.timeout.entity.preference_indicator}")
    private long preferenceIndicatorTimeout;

    public ServiceConfiguration(
            ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration,
            UserCrudOperation userCrudOperation, UserDao userDao,
            RouterInfoDao routerInfoDao, RouterInfoCache routerInfoCache,
            RouterSupportDao routerSupportDao, RouterSupportCache routerSupportCache,
            NotifySettingCrudOperation notifySettingCrudOperation, NotifySettingDao notifySettingDao,
            SenderInfoDao senderInfoDao, SenderInfoCache senderInfoCache,
            SenderSupportDao senderSupportDao, SenderSupportCache senderSupportCache,
            TopicCrudOperation topicCrudOperation, TopicDao topicDao,
            DispatcherInfoDao dispatcherInfoDao, DispatcherInfoCache dispatcherInfoCache,
            DispatcherSupportDao dispatcherSupportDao, DispatcherSupportCache dispatcherSupportCache,
            PreferenceDao preferenceDao, PreferenceCache preferenceCache,
            VariableDao variableDao, VariableCache variableCache,
            SendHistoryDao sendHistoryDao, SendHistoryCache sendHistoryCache,
            PreferenceIndicatorDao preferenceIndicatorDao, PreferenceIndicatorCache preferenceIndicatorCache
    ) {
        this.serviceExceptionMapperConfiguration = serviceExceptionMapperConfiguration;
        this.userCrudOperation = userCrudOperation;
        this.userDao = userDao;
        this.routerInfoDao = routerInfoDao;
        this.routerInfoCache = routerInfoCache;
        this.routerSupportDao = routerSupportDao;
        this.routerSupportCache = routerSupportCache;
        this.notifySettingCrudOperation = notifySettingCrudOperation;
        this.notifySettingDao = notifySettingDao;
        this.senderInfoDao = senderInfoDao;
        this.senderInfoCache = senderInfoCache;
        this.senderSupportDao = senderSupportDao;
        this.senderSupportCache = senderSupportCache;
        this.topicCrudOperation = topicCrudOperation;
        this.topicDao = topicDao;
        this.dispatcherInfoDao = dispatcherInfoDao;
        this.dispatcherInfoCache = dispatcherInfoCache;
        this.dispatcherSupportDao = dispatcherSupportDao;
        this.dispatcherSupportCache = dispatcherSupportCache;
        this.preferenceDao = preferenceDao;
        this.preferenceCache = preferenceCache;
        this.variableDao = variableDao;
        this.variableCache = variableCache;
        this.sendHistoryDao = sendHistoryDao;
        this.sendHistoryCache = sendHistoryCache;
        this.preferenceIndicatorDao = preferenceIndicatorDao;
        this.preferenceIndicatorCache = preferenceIndicatorCache;
    }

    @Bean
    public KeyFetcher<LongIdKey> longIdKeyKeyFetcher() {
        return new SnowFlakeLongIdKeyFetcher();
    }

    @Bean
    public CustomBatchCrudService<StringIdKey, User> userCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                userCrudOperation,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
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
    public DaoOnlyPresetLookupService<User> userDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
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
    public GeneralBatchCrudService<SenderInfoKey, SenderInfo> senderInfoCustomBatchCrudService() {
        return new GeneralBatchCrudService<>(
                senderInfoDao,
                senderInfoCache,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                senderInfoTimeout
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

    @Bean
    public GeneralBatchCrudService<StringIdKey, DispatcherInfo> dispatcherInfoCustomBatchCrudService() {
        return new GeneralBatchCrudService<>(
                dispatcherInfoDao,
                dispatcherInfoCache,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                dispatcherInfoTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<DispatcherInfo> dispatcherInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                dispatcherInfoDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<DispatcherInfo> dispatcherInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                dispatcherInfoDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, DispatcherSupport> dispatcherSupportGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                dispatcherSupportDao,
                dispatcherSupportCache,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                dispatcherSupportTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<DispatcherSupport> dispatcherSupportDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                dispatcherSupportDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<DispatcherSupport> dispatcherSupportDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                dispatcherSupportDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<PreferenceKey, Preference> preferenceGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                preferenceDao,
                preferenceCache,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                preferenceTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Preference> preferenceDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                preferenceDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Preference> preferenceDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                preferenceDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<VariableKey, Variable> variableGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                variableDao,
                variableCache,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                variableTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Variable> variableDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                variableDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Variable> variableDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                variableDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<LongIdKey, SendHistory> sendHistoryGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                sendHistoryDao,
                sendHistoryCache,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                sendHistoryTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<SendHistory> sendHistoryDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                sendHistoryDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<SendHistory> sendHistoryDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                sendHistoryDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<PreferenceIndicatorKey, PreferenceIndicator>
    preferenceIndicatorGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                preferenceIndicatorDao,
                preferenceIndicatorCache,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                preferenceIndicatorTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<PreferenceIndicator> preferenceIndicatorDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                preferenceIndicatorDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<PreferenceIndicator> preferenceIndicatorDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                preferenceIndicatorDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }
}

package com.dwarfeng.notify.impl.configuration;

import com.dwarfeng.notify.impl.bean.entity.*;
import com.dwarfeng.notify.impl.bean.entity.key.*;
import com.dwarfeng.notify.impl.dao.preset.*;
import com.dwarfeng.notify.stack.bean.entity.*;
import com.dwarfeng.notify.stack.bean.entity.key.*;
import com.dwarfeng.subgrade.impl.bean.DozerBeanTransformer;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchBaseDao;
import com.dwarfeng.subgrade.impl.dao.HibernateEntireLookupDao;
import com.dwarfeng.subgrade.impl.dao.HibernatePresetLookupDao;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.sdk.hibernate.modification.DefaultDeletionMod;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;

@Configuration
public class DaoConfiguration {

    private final HibernateTemplate hibernateTemplate;

    private final UserPresetCriteriaMaker userPresetCriteriaMaker;
    private final RouterInfoPresetCriteriaMaker routerInfoPresetCriteriaMaker;
    private final RouterSupportPresetCriteriaMaker routerSupportPresetCriteriaMaker;
    private final NotifySettingPresetCriteriaMaker notifySettingPresetCriteriaMaker;
    private final SenderInfoPresetCriteriaMaker senderInfoPresetCriteriaMaker;
    private final SenderSupportPresetCriteriaMaker senderSupportPresetCriteriaMaker;
    private final TopicPresetCriteriaMaker topicPresetCriteriaMaker;
    private final DispatcherInfoPresetCriteriaMaker dispatcherInfoPresetCriteriaMaker;
    private final DispatcherSupportPresetCriteriaMaker dispatcherSupportPresetCriteriaMaker;
    private final MetaPresetCriteriaMaker metaPresetCriteriaMaker;
    private final VariablePresetCriteriaMaker variablePresetCriteriaMaker;
    private final SendHistoryPresetCriteriaMaker sendHistoryPresetCriteriaMaker;
    private final MetaIndicatorPresetCriteriaMaker metaIndicatorPresetCriteriaMaker;
    private final VariableIndicatorPresetCriteriaMaker variableIndicatorPresetCriteriaMaker;

    @Autowired
    private Mapper mapper;

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    public DaoConfiguration(
            HibernateTemplate hibernateTemplate,
            UserPresetCriteriaMaker userPresetCriteriaMaker,
            RouterInfoPresetCriteriaMaker routerInfoPresetCriteriaMaker,
            RouterSupportPresetCriteriaMaker routerSupportPresetCriteriaMaker,
            NotifySettingPresetCriteriaMaker notifySettingPresetCriteriaMaker,
            SenderInfoPresetCriteriaMaker senderInfoPresetCriteriaMaker,
            SenderSupportPresetCriteriaMaker senderSupportPresetCriteriaMaker,
            TopicPresetCriteriaMaker topicPresetCriteriaMaker,
            DispatcherInfoPresetCriteriaMaker dispatcherInfoPresetCriteriaMaker,
            DispatcherSupportPresetCriteriaMaker dispatcherSupportPresetCriteriaMaker,
            MetaPresetCriteriaMaker metaPresetCriteriaMaker,
            VariablePresetCriteriaMaker variablePresetCriteriaMaker,
            SendHistoryPresetCriteriaMaker sendHistoryPresetCriteriaMaker,
            MetaIndicatorPresetCriteriaMaker metaIndicatorPresetCriteriaMaker,
            VariableIndicatorPresetCriteriaMaker variableIndicatorPresetCriteriaMaker
    ) {
        this.hibernateTemplate = hibernateTemplate;
        this.userPresetCriteriaMaker = userPresetCriteriaMaker;
        this.routerInfoPresetCriteriaMaker = routerInfoPresetCriteriaMaker;
        this.routerSupportPresetCriteriaMaker = routerSupportPresetCriteriaMaker;
        this.notifySettingPresetCriteriaMaker = notifySettingPresetCriteriaMaker;
        this.senderInfoPresetCriteriaMaker = senderInfoPresetCriteriaMaker;
        this.senderSupportPresetCriteriaMaker = senderSupportPresetCriteriaMaker;
        this.topicPresetCriteriaMaker = topicPresetCriteriaMaker;
        this.dispatcherInfoPresetCriteriaMaker = dispatcherInfoPresetCriteriaMaker;
        this.dispatcherSupportPresetCriteriaMaker = dispatcherSupportPresetCriteriaMaker;
        this.metaPresetCriteriaMaker = metaPresetCriteriaMaker;
        this.variablePresetCriteriaMaker = variablePresetCriteriaMaker;
        this.sendHistoryPresetCriteriaMaker = sendHistoryPresetCriteriaMaker;
        this.metaIndicatorPresetCriteriaMaker = metaIndicatorPresetCriteriaMaker;
        this.variableIndicatorPresetCriteriaMaker = variableIndicatorPresetCriteriaMaker;
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, User, HibernateUser>
    userHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, mapper),
                new DozerBeanTransformer<>(User.class, HibernateUser.class, mapper),
                HibernateUser.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<User, HibernateUser> userHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(User.class, HibernateUser.class, mapper),
                HibernateUser.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<User, HibernateUser> userHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(User.class, HibernateUser.class, mapper),
                HibernateUser.class,
                userPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, RouterInfo, HibernateRouterInfo>
    routerInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(RouterInfo.class, HibernateRouterInfo.class, mapper),
                HibernateRouterInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<RouterInfo, HibernateRouterInfo> routerInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(RouterInfo.class, HibernateRouterInfo.class, mapper),
                HibernateRouterInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<RouterInfo, HibernateRouterInfo> routerInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(RouterInfo.class, HibernateRouterInfo.class, mapper),
                HibernateRouterInfo.class,
                routerInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, RouterSupport, HibernateRouterSupport>
    routerSupportHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, mapper),
                new DozerBeanTransformer<>(RouterSupport.class, HibernateRouterSupport.class, mapper),
                HibernateRouterSupport.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<RouterSupport, HibernateRouterSupport> routerSupportHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(RouterSupport.class, HibernateRouterSupport.class, mapper),
                HibernateRouterSupport.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<RouterSupport, HibernateRouterSupport> routerSupportHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(RouterSupport.class, HibernateRouterSupport.class, mapper),
                HibernateRouterSupport.class,
                routerSupportPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, NotifySetting, HibernateNotifySetting>
    notifySettingHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(NotifySetting.class, HibernateNotifySetting.class, mapper),
                HibernateNotifySetting.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<NotifySetting, HibernateNotifySetting> notifySettingHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(NotifySetting.class, HibernateNotifySetting.class, mapper),
                HibernateNotifySetting.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<NotifySetting, HibernateNotifySetting> notifySettingHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(NotifySetting.class, HibernateNotifySetting.class, mapper),
                HibernateNotifySetting.class,
                notifySettingPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<SenderInfoKey, HibernateSenderInfoKey, SenderInfo, HibernateSenderInfo>
    senderInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(SenderInfoKey.class, HibernateSenderInfoKey.class, mapper),
                new DozerBeanTransformer<>(SenderInfo.class, HibernateSenderInfo.class, mapper),
                HibernateSenderInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<SenderInfo, HibernateSenderInfo> senderInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(SenderInfo.class, HibernateSenderInfo.class, mapper),
                HibernateSenderInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<SenderInfo, HibernateSenderInfo> senderInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(SenderInfo.class, HibernateSenderInfo.class, mapper),
                HibernateSenderInfo.class,
                senderInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, SenderSupport, HibernateSenderSupport>
    senderSupportHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, mapper),
                new DozerBeanTransformer<>(SenderSupport.class, HibernateSenderSupport.class, mapper),
                HibernateSenderSupport.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<SenderSupport, HibernateSenderSupport> senderSupportHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(SenderSupport.class, HibernateSenderSupport.class, mapper),
                HibernateSenderSupport.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<SenderSupport, HibernateSenderSupport> senderSupportHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(SenderSupport.class, HibernateSenderSupport.class, mapper),
                HibernateSenderSupport.class,
                senderSupportPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, Topic, HibernateTopic>
    topicHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, mapper),
                new DozerBeanTransformer<>(Topic.class, HibernateTopic.class, mapper),
                HibernateTopic.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Topic, HibernateTopic> topicHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(Topic.class, HibernateTopic.class, mapper),
                HibernateTopic.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Topic, HibernateTopic> topicHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(Topic.class, HibernateTopic.class, mapper),
                HibernateTopic.class,
                topicPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, DispatcherInfo, HibernateDispatcherInfo>
    dispatcherInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, mapper),
                new DozerBeanTransformer<>(DispatcherInfo.class, HibernateDispatcherInfo.class, mapper),
                HibernateDispatcherInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<DispatcherInfo, HibernateDispatcherInfo> dispatcherInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(DispatcherInfo.class, HibernateDispatcherInfo.class, mapper),
                HibernateDispatcherInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<DispatcherInfo, HibernateDispatcherInfo> dispatcherInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(DispatcherInfo.class, HibernateDispatcherInfo.class, mapper),
                HibernateDispatcherInfo.class,
                dispatcherInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, DispatcherSupport, HibernateDispatcherSupport>
    dispatcherSupportHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, mapper),
                new DozerBeanTransformer<>(DispatcherSupport.class, HibernateDispatcherSupport.class, mapper),
                HibernateDispatcherSupport.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<DispatcherSupport, HibernateDispatcherSupport>
    dispatcherSupportHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(DispatcherSupport.class, HibernateDispatcherSupport.class, mapper),
                HibernateDispatcherSupport.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<DispatcherSupport, HibernateDispatcherSupport>
    dispatcherSupportHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(DispatcherSupport.class, HibernateDispatcherSupport.class, mapper),
                HibernateDispatcherSupport.class,
                dispatcherSupportPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<MetaKey, HibernateMetaKey, Meta, HibernateMeta>
    metaHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(MetaKey.class, HibernateMetaKey.class, mapper),
                new DozerBeanTransformer<>(Meta.class, HibernateMeta.class, mapper),
                HibernateMeta.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Meta, HibernateMeta> metaHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(Meta.class, HibernateMeta.class, mapper),
                HibernateMeta.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Meta, HibernateMeta> metaHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(Meta.class, HibernateMeta.class, mapper),
                HibernateMeta.class,
                metaPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<VariableKey, HibernateVariableKey, Variable, HibernateVariable>
    variableHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(VariableKey.class, HibernateVariableKey.class, mapper),
                new DozerBeanTransformer<>(Variable.class, HibernateVariable.class, mapper),
                HibernateVariable.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Variable, HibernateVariable> variableHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(Variable.class, HibernateVariable.class, mapper),
                HibernateVariable.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Variable, HibernateVariable> variableHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(Variable.class, HibernateVariable.class, mapper),
                HibernateVariable.class,
                variablePresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, SendHistory, HibernateSendHistory>
    sendHistoryHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(SendHistory.class, HibernateSendHistory.class, mapper),
                HibernateSendHistory.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<SendHistory, HibernateSendHistory> sendHistoryHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(SendHistory.class, HibernateSendHistory.class, mapper),
                HibernateSendHistory.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<SendHistory, HibernateSendHistory> sendHistoryHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(SendHistory.class, HibernateSendHistory.class, mapper),
                HibernateSendHistory.class,
                sendHistoryPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<MetaIndicatorKey, HibernateMetaIndicatorKey, MetaIndicator,
            HibernateMetaIndicator> metaIndicatorHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(MetaIndicatorKey.class, HibernateMetaIndicatorKey.class, mapper),
                new DozerBeanTransformer<>(MetaIndicator.class, HibernateMetaIndicator.class, mapper),
                HibernateMetaIndicator.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<MetaIndicator, HibernateMetaIndicator>
    metaIndicatorHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(MetaIndicator.class, HibernateMetaIndicator.class, mapper),
                HibernateMetaIndicator.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<MetaIndicator, HibernateMetaIndicator>
    metaIndicatorHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(MetaIndicator.class, HibernateMetaIndicator.class, mapper),
                HibernateMetaIndicator.class,
                metaIndicatorPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<VariableIndicatorKey, HibernateVariableIndicatorKey, VariableIndicator,
            HibernateVariableIndicator> variableIndicatorHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(VariableIndicatorKey.class, HibernateVariableIndicatorKey.class, mapper),
                new DozerBeanTransformer<>(VariableIndicator.class, HibernateVariableIndicator.class, mapper),
                HibernateVariableIndicator.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<VariableIndicator, HibernateVariableIndicator>
    variableIndicatorHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(VariableIndicator.class, HibernateVariableIndicator.class, mapper),
                HibernateVariableIndicator.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<VariableIndicator, HibernateVariableIndicator>
    variableIndicatorHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(VariableIndicator.class, HibernateVariableIndicator.class, mapper),
                HibernateVariableIndicator.class,
                variableIndicatorPresetCriteriaMaker
        );
    }
}

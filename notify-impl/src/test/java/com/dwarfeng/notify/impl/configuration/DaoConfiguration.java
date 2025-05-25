package com.dwarfeng.notify.impl.configuration;

import com.dwarfeng.notify.impl.bean.BeanMapper;
import com.dwarfeng.notify.impl.bean.entity.*;
import com.dwarfeng.notify.impl.bean.key.*;
import com.dwarfeng.notify.impl.dao.preset.*;
import com.dwarfeng.notify.stack.bean.entity.*;
import com.dwarfeng.notify.stack.bean.key.*;
import com.dwarfeng.subgrade.impl.bean.MapStructBeanTransformer;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchBaseDao;
import com.dwarfeng.subgrade.impl.dao.HibernateEntireLookupDao;
import com.dwarfeng.subgrade.impl.dao.HibernatePresetLookupDao;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.sdk.hibernate.modification.DefaultDeletionMod;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
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
    private final MetaIndicatorPresetCriteriaMaker metaIndicatorPresetCriteriaMaker;
    private final NotifyHistoryPresetCriteriaMaker notifyHistoryPresetCriteriaMaker;
    private final NotifyInfoRecordPresetCriteriaMaker notifyInfoRecordPresetCriteriaMaker;
    private final NotifySendRecordPresetCriteriaMaker notifySendRecordPresetCriteriaMaker;

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
            MetaIndicatorPresetCriteriaMaker metaIndicatorPresetCriteriaMaker,
            NotifyHistoryPresetCriteriaMaker notifyHistoryPresetCriteriaMaker,
            NotifyInfoRecordPresetCriteriaMaker notifyInfoRecordPresetCriteriaMaker,
            NotifySendRecordPresetCriteriaMaker notifySendRecordPresetCriteriaMaker
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
        this.metaIndicatorPresetCriteriaMaker = metaIndicatorPresetCriteriaMaker;
        this.notifyHistoryPresetCriteriaMaker = notifyHistoryPresetCriteriaMaker;
        this.notifyInfoRecordPresetCriteriaMaker = notifyInfoRecordPresetCriteriaMaker;
        this.notifySendRecordPresetCriteriaMaker = notifySendRecordPresetCriteriaMaker;
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, User, HibernateUser>
    userHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(User.class, HibernateUser.class, BeanMapper.class),
                HibernateUser.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<User, HibernateUser> userHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(User.class, HibernateUser.class, BeanMapper.class),
                HibernateUser.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<User, HibernateUser> userHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(User.class, HibernateUser.class, BeanMapper.class),
                HibernateUser.class,
                userPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, RouterInfo, HibernateRouterInfo>
    routerInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(RouterInfo.class, HibernateRouterInfo.class, BeanMapper.class),
                HibernateRouterInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<RouterInfo, HibernateRouterInfo> routerInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(RouterInfo.class, HibernateRouterInfo.class, BeanMapper.class),
                HibernateRouterInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<RouterInfo, HibernateRouterInfo> routerInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(RouterInfo.class, HibernateRouterInfo.class, BeanMapper.class),
                HibernateRouterInfo.class,
                routerInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, RouterSupport, HibernateRouterSupport>
    routerSupportHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(
                        RouterSupport.class, HibernateRouterSupport.class, BeanMapper.class
                ),
                HibernateRouterSupport.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<RouterSupport, HibernateRouterSupport> routerSupportHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        RouterSupport.class, HibernateRouterSupport.class, BeanMapper.class
                ),
                HibernateRouterSupport.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<RouterSupport, HibernateRouterSupport> routerSupportHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        RouterSupport.class, HibernateRouterSupport.class, BeanMapper.class
                ),
                HibernateRouterSupport.class,
                routerSupportPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, NotifySetting, HibernateNotifySetting>
    notifySettingHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(
                        NotifySetting.class, HibernateNotifySetting.class, BeanMapper.class
                ),
                HibernateNotifySetting.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<NotifySetting, HibernateNotifySetting> notifySettingHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        NotifySetting.class, HibernateNotifySetting.class, BeanMapper.class
                ),
                HibernateNotifySetting.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<NotifySetting, HibernateNotifySetting> notifySettingHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        NotifySetting.class, HibernateNotifySetting.class, BeanMapper.class
                ),
                HibernateNotifySetting.class,
                notifySettingPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<SenderInfoKey, HibernateSenderInfoKey, SenderInfo, HibernateSenderInfo>
    senderInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        SenderInfoKey.class, HibernateSenderInfoKey.class, BeanMapper.class
                ),
                new MapStructBeanTransformer<>(SenderInfo.class, HibernateSenderInfo.class, BeanMapper.class),
                HibernateSenderInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<SenderInfo, HibernateSenderInfo> senderInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(SenderInfo.class, HibernateSenderInfo.class, BeanMapper.class),
                HibernateSenderInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<SenderInfo, HibernateSenderInfo> senderInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(SenderInfo.class, HibernateSenderInfo.class, BeanMapper.class),
                HibernateSenderInfo.class,
                senderInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, SenderSupport, HibernateSenderSupport>
    senderSupportHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(
                        SenderSupport.class, HibernateSenderSupport.class, BeanMapper.class
                ),
                HibernateSenderSupport.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<SenderSupport, HibernateSenderSupport> senderSupportHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        SenderSupport.class, HibernateSenderSupport.class, BeanMapper.class
                ),
                HibernateSenderSupport.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<SenderSupport, HibernateSenderSupport> senderSupportHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        SenderSupport.class, HibernateSenderSupport.class, BeanMapper.class
                ),
                HibernateSenderSupport.class,
                senderSupportPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, Topic, HibernateTopic>
    topicHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(Topic.class, HibernateTopic.class, BeanMapper.class),
                HibernateTopic.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Topic, HibernateTopic> topicHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(Topic.class, HibernateTopic.class, BeanMapper.class),
                HibernateTopic.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Topic, HibernateTopic> topicHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(Topic.class, HibernateTopic.class, BeanMapper.class),
                HibernateTopic.class,
                topicPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, DispatcherInfo, HibernateDispatcherInfo>
    dispatcherInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(
                        DispatcherInfo.class, HibernateDispatcherInfo.class, BeanMapper.class
                ),
                HibernateDispatcherInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<DispatcherInfo, HibernateDispatcherInfo> dispatcherInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        DispatcherInfo.class, HibernateDispatcherInfo.class, BeanMapper.class
                ),
                HibernateDispatcherInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<DispatcherInfo, HibernateDispatcherInfo> dispatcherInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        DispatcherInfo.class, HibernateDispatcherInfo.class, BeanMapper.class
                ),
                HibernateDispatcherInfo.class,
                dispatcherInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, DispatcherSupport, HibernateDispatcherSupport>
    dispatcherSupportHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(
                        DispatcherSupport.class, HibernateDispatcherSupport.class, BeanMapper.class
                ),
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
                new MapStructBeanTransformer<>(
                        DispatcherSupport.class, HibernateDispatcherSupport.class, BeanMapper.class
                ),
                HibernateDispatcherSupport.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<DispatcherSupport, HibernateDispatcherSupport>
    dispatcherSupportHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        DispatcherSupport.class, HibernateDispatcherSupport.class, BeanMapper.class
                ),
                HibernateDispatcherSupport.class,
                dispatcherSupportPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<MetaKey, HibernateMetaKey, Meta, HibernateMeta>
    metaHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(MetaKey.class, HibernateMetaKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(Meta.class, HibernateMeta.class, BeanMapper.class),
                HibernateMeta.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Meta, HibernateMeta> metaHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(Meta.class, HibernateMeta.class, BeanMapper.class),
                HibernateMeta.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Meta, HibernateMeta> metaHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(Meta.class, HibernateMeta.class, BeanMapper.class),
                HibernateMeta.class,
                metaPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<MetaIndicatorKey, HibernateMetaIndicatorKey, MetaIndicator,
            HibernateMetaIndicator> metaIndicatorHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        MetaIndicatorKey.class, HibernateMetaIndicatorKey.class, BeanMapper.class
                ),
                new MapStructBeanTransformer<>(
                        MetaIndicator.class, HibernateMetaIndicator.class, BeanMapper.class
                ),
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
                new MapStructBeanTransformer<>(
                        MetaIndicator.class, HibernateMetaIndicator.class, BeanMapper.class
                ),
                HibernateMetaIndicator.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<MetaIndicator, HibernateMetaIndicator>
    metaIndicatorHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        MetaIndicator.class, HibernateMetaIndicator.class, BeanMapper.class
                ),
                HibernateMetaIndicator.class,
                metaIndicatorPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, NotifyHistory, HibernateNotifyHistory>
    notifyHistoryHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(
                        NotifyHistory.class, HibernateNotifyHistory.class, BeanMapper.class
                ),
                HibernateNotifyHistory.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<NotifyHistory, HibernateNotifyHistory> notifyHistoryHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        NotifyHistory.class, HibernateNotifyHistory.class, BeanMapper.class
                ),
                HibernateNotifyHistory.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<NotifyHistory, HibernateNotifyHistory> notifyHistoryHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        NotifyHistory.class, HibernateNotifyHistory.class, BeanMapper.class
                ),
                HibernateNotifyHistory.class,
                notifyHistoryPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<NotifyInfoRecordKey, HibernateNotifyInfoRecordKey, NotifyInfoRecord,
            HibernateNotifyInfoRecord> notifyInfoRecordHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        NotifyInfoRecordKey.class, HibernateNotifyInfoRecordKey.class, BeanMapper.class
                ),
                new MapStructBeanTransformer<>(
                        NotifyInfoRecord.class, HibernateNotifyInfoRecord.class, BeanMapper.class
                ),
                HibernateNotifyInfoRecord.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<NotifyInfoRecord, HibernateNotifyInfoRecord>
    notifyInfoRecordHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        NotifyInfoRecord.class, HibernateNotifyInfoRecord.class, BeanMapper.class
                ),
                HibernateNotifyInfoRecord.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<NotifyInfoRecord, HibernateNotifyInfoRecord>
    notifyInfoRecordHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        NotifyInfoRecord.class, HibernateNotifyInfoRecord.class, BeanMapper.class
                ),
                HibernateNotifyInfoRecord.class,
                notifyInfoRecordPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<NotifySendRecordKey, HibernateNotifySendRecordKey, NotifySendRecord,
            HibernateNotifySendRecord> notifySendRecordHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        NotifySendRecordKey.class, HibernateNotifySendRecordKey.class, BeanMapper.class
                ),
                new MapStructBeanTransformer<>(
                        NotifySendRecord.class, HibernateNotifySendRecord.class, BeanMapper.class
                ),
                HibernateNotifySendRecord.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<NotifySendRecord, HibernateNotifySendRecord>
    notifySendRecordHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        NotifySendRecord.class, HibernateNotifySendRecord.class, BeanMapper.class
                ),
                HibernateNotifySendRecord.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<NotifySendRecord, HibernateNotifySendRecord>
    notifySendRecordHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        NotifySendRecord.class, HibernateNotifySendRecord.class, BeanMapper.class
                ),
                HibernateNotifySendRecord.class,
                notifySendRecordPresetCriteriaMaker
        );
    }
}

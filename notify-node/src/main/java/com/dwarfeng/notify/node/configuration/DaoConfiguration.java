package com.dwarfeng.notify.node.configuration;

import com.dwarfeng.notify.impl.bean.entity.*;
import com.dwarfeng.notify.impl.bean.entity.key.HibernateRelationKey;
import com.dwarfeng.notify.impl.dao.preset.*;
import com.dwarfeng.notify.stack.bean.entity.*;
import com.dwarfeng.notify.stack.bean.entity.key.RelationKey;
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

    private final RouterInfoPresetCriteriaMaker routerInfoPresetCriteriaMaker;
    private final RouterSupportPresetCriteriaMaker routerSupportPresetCriteriaMaker;
    private final NotifySettingPresetCriteriaMaker notifySettingPresetCriteriaMaker;
    private final SenderInfoPresetCriteriaMaker senderInfoPresetCriteriaMaker;
    private final SenderSupportPresetCriteriaMaker senderSupportPresetCriteriaMaker;
    private final TopicPresetCriteriaMaker topicPresetCriteriaMaker;
    private final RelationPresetCriteriaMaker relationPresetCriteriaMaker;

    @Autowired
    private Mapper mapper;

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    public DaoConfiguration(
            HibernateTemplate hibernateTemplate,
            RouterInfoPresetCriteriaMaker routerInfoPresetCriteriaMaker,
            RouterSupportPresetCriteriaMaker routerSupportPresetCriteriaMaker,
            NotifySettingPresetCriteriaMaker notifySettingPresetCriteriaMaker,
            SenderInfoPresetCriteriaMaker senderInfoPresetCriteriaMaker,
            SenderSupportPresetCriteriaMaker senderSupportPresetCriteriaMaker,
            TopicPresetCriteriaMaker topicPresetCriteriaMaker,
            RelationPresetCriteriaMaker relationPresetCriteriaMaker
    ) {
        this.hibernateTemplate = hibernateTemplate;
        this.routerInfoPresetCriteriaMaker = routerInfoPresetCriteriaMaker;
        this.routerSupportPresetCriteriaMaker = routerSupportPresetCriteriaMaker;
        this.notifySettingPresetCriteriaMaker = notifySettingPresetCriteriaMaker;
        this.senderInfoPresetCriteriaMaker = senderInfoPresetCriteriaMaker;
        this.senderSupportPresetCriteriaMaker = senderSupportPresetCriteriaMaker;
        this.topicPresetCriteriaMaker = topicPresetCriteriaMaker;
        this.relationPresetCriteriaMaker = relationPresetCriteriaMaker;
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, User, HibernateUser> userHibernateBatchBaseDao() {
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
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, SenderInfo, HibernateSenderInfo>
    senderInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
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
    public HibernateBatchBaseDao<RelationKey, HibernateRelationKey, Relation, HibernateRelation>
    relationHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(RelationKey.class, HibernateRelationKey.class, mapper),
                new DozerBeanTransformer<>(Relation.class, HibernateRelation.class, mapper),
                HibernateRelation.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Relation, HibernateRelation> relationHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(Relation.class, HibernateRelation.class, mapper),
                HibernateRelation.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Relation, HibernateRelation> relationHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(Relation.class, HibernateRelation.class, mapper),
                HibernateRelation.class,
                relationPresetCriteriaMaker
        );
    }
}

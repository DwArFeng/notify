package com.dwarfeng.notify.impl.configuration;

import com.dwarfeng.notify.impl.bean.entity.HibernateNotifySetting;
import com.dwarfeng.notify.impl.bean.entity.HibernateRouterInfo;
import com.dwarfeng.notify.impl.bean.entity.HibernateRouterSupport;
import com.dwarfeng.notify.impl.bean.entity.HibernateUser;
import com.dwarfeng.notify.impl.dao.preset.NotifySettingPresetCriteriaMaker;
import com.dwarfeng.notify.impl.dao.preset.RouterInfoPresetCriteriaMaker;
import com.dwarfeng.notify.impl.dao.preset.RouterSupportPresetCriteriaMaker;
import com.dwarfeng.notify.stack.bean.entity.NotifySetting;
import com.dwarfeng.notify.stack.bean.entity.RouterInfo;
import com.dwarfeng.notify.stack.bean.entity.RouterSupport;
import com.dwarfeng.notify.stack.bean.entity.User;
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

    @Autowired
    private Mapper mapper;

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    public DaoConfiguration(
            HibernateTemplate hibernateTemplate,
            RouterInfoPresetCriteriaMaker routerInfoPresetCriteriaMaker,
            RouterSupportPresetCriteriaMaker routerSupportPresetCriteriaMaker,
            NotifySettingPresetCriteriaMaker notifySettingPresetCriteriaMaker
    ) {
        this.hibernateTemplate = hibernateTemplate;
        this.routerInfoPresetCriteriaMaker = routerInfoPresetCriteriaMaker;
        this.routerSupportPresetCriteriaMaker = routerSupportPresetCriteriaMaker;
        this.notifySettingPresetCriteriaMaker = notifySettingPresetCriteriaMaker;
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
}

package com.dwarfeng.notify.impl.configuration;

import com.dwarfeng.notify.sdk.bean.BeanMapper;
import com.dwarfeng.notify.sdk.bean.entity.*;
import com.dwarfeng.notify.sdk.bean.key.formatter.*;
import com.dwarfeng.notify.stack.bean.entity.*;
import com.dwarfeng.notify.stack.bean.key.*;
import com.dwarfeng.subgrade.impl.bean.MapStructBeanTransformer;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.redis.formatter.LongIdStringKeyFormatter;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringIdStringKeyFormatter;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CacheConfiguration {

    private final RedisTemplate<String, ?> template;

    @Value("${cache.prefix.entity.user}")
    private String userPrefix;
    @Value("${cache.prefix.entity.router_info}")
    private String routerInfoPrefix;
    @Value("${cache.prefix.entity.router_support}")
    private String routerSupportPrefix;
    @Value("${cache.prefix.entity.notify_setting}")
    private String notifySettingPrefix;
    @Value("${cache.prefix.entity.sender_info}")
    private String senderInfoPrefix;
    @Value("${cache.prefix.entity.sender_support}")
    private String senderSupportPrefix;
    @Value("${cache.prefix.entity.topic}")
    private String topicPrefix;
    @Value("${cache.prefix.entity.dispatcher_info}")
    private String dispatcherInfoPrefix;
    @Value("${cache.prefix.entity.dispatcher_support}")
    private String dispatcherSupportPrefix;
    @Value("${cache.prefix.entity.meta}")
    private String metaPrefix;
    @Value("${cache.prefix.entity.meta_indicator}")
    private String metaIndicatorPrefix;
    @Value("${cache.prefix.entity.notify_history}")
    private String notifyHistoryPrefix;
    @Value("${cache.prefix.entity.notify_info_record}")
    private String notifyInfoRecordPrefix;
    @Value("${cache.prefix.entity.notify_send_record}")
    private String notifySendRecordPrefix;

    public CacheConfiguration(RedisTemplate<String, ?> template) {
        this.template = template;
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, User, FastJsonUser> userRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonUser>) template,
                new StringIdStringKeyFormatter(userPrefix),
                new MapStructBeanTransformer<>(User.class, FastJsonUser.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, RouterInfo, FastJsonRouterInfo> routerInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonRouterInfo>) template,
                new LongIdStringKeyFormatter(routerInfoPrefix),
                new MapStructBeanTransformer<>(RouterInfo.class, FastJsonRouterInfo.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, RouterSupport, FastJsonRouterSupport> routerSupportRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonRouterSupport>) template,
                new StringIdStringKeyFormatter(routerSupportPrefix),
                new MapStructBeanTransformer<>(RouterSupport.class, FastJsonRouterSupport.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, NotifySetting, FastJsonNotifySetting> notifySettingRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonNotifySetting>) template,
                new LongIdStringKeyFormatter(notifySettingPrefix),
                new MapStructBeanTransformer<>(NotifySetting.class, FastJsonNotifySetting.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<SenderInfoKey, SenderInfo, FastJsonSenderInfo> senderInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonSenderInfo>) template,
                new SenderInfoStringKeyFormatter(senderInfoPrefix),
                new MapStructBeanTransformer<>(SenderInfo.class, FastJsonSenderInfo.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, SenderSupport, FastJsonSenderSupport> senderSupportRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonSenderSupport>) template,
                new StringIdStringKeyFormatter(senderSupportPrefix),
                new MapStructBeanTransformer<>(SenderSupport.class, FastJsonSenderSupport.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, Topic, FastJsonTopic> topicRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonTopic>) template,
                new StringIdStringKeyFormatter(topicPrefix),
                new MapStructBeanTransformer<>(Topic.class, FastJsonTopic.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, DispatcherInfo, FastJsonDispatcherInfo>
    dispatcherInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonDispatcherInfo>) template,
                new StringIdStringKeyFormatter(dispatcherInfoPrefix),
                new MapStructBeanTransformer<>(DispatcherInfo.class, FastJsonDispatcherInfo.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, DispatcherSupport, FastJsonDispatcherSupport>
    dispatcherSupportRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonDispatcherSupport>) template,
                new StringIdStringKeyFormatter(dispatcherSupportPrefix),
                new MapStructBeanTransformer<>(
                        DispatcherSupport.class, FastJsonDispatcherSupport.class, BeanMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<MetaKey, Meta, FastJsonMeta> metaRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonMeta>) template,
                new MetaStringKeyFormatter(metaPrefix),
                new MapStructBeanTransformer<>(Meta.class, FastJsonMeta.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<MetaIndicatorKey, MetaIndicator, FastJsonMetaIndicator>
    metaIndicatorRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonMetaIndicator>) template,
                new MetaIndicatorStringKeyFormatter(metaIndicatorPrefix),
                new MapStructBeanTransformer<>(MetaIndicator.class, FastJsonMetaIndicator.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, NotifyHistory, FastJsonNotifyHistory> notifyHistoryRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonNotifyHistory>) template,
                new LongIdStringKeyFormatter(notifyHistoryPrefix),
                new MapStructBeanTransformer<>(NotifyHistory.class, FastJsonNotifyHistory.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<NotifyInfoRecordKey, NotifyInfoRecord, FastJsonNotifyInfoRecord>
    notifyInfoRecordRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonNotifyInfoRecord>) template,
                new NotifyInfoRecordStringKeyFormatter(notifyInfoRecordPrefix),
                new MapStructBeanTransformer<>(
                        NotifyInfoRecord.class, FastJsonNotifyInfoRecord.class, BeanMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<NotifySendRecordKey, NotifySendRecord, FastJsonNotifySendRecord>
    notifySendRecordRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonNotifySendRecord>) template,
                new NotifySendRecordStringKeyFormatter(notifySendRecordPrefix),
                new MapStructBeanTransformer<>(
                        NotifySendRecord.class, FastJsonNotifySendRecord.class, BeanMapper.class
                )
        );
    }
}

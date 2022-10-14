package com.dwarfeng.notify.node.configuration;

import com.dwarfeng.notify.sdk.bean.entity.*;
import com.dwarfeng.notify.sdk.bean.entity.key.formatter.RelationStringKeyFormatter;
import com.dwarfeng.notify.stack.bean.entity.*;
import com.dwarfeng.notify.stack.bean.entity.key.RelationKey;
import com.dwarfeng.subgrade.impl.bean.DozerBeanTransformer;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.redis.formatter.LongIdStringKeyFormatter;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringIdStringKeyFormatter;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CacheConfiguration {

    private final RedisTemplate<String, ?> template;
    private final Mapper mapper;

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
    @Value("${cache.prefix.entity.relation}")
    private String relationPrefix;
    @Value("${cache.prefix.entity.dispatcher_info}")
    private String dispatcherInfoPrefix;
    @Value("${cache.prefix.entity.dispatcher_support}")
    private String dispatcherSupportPrefix;

    public CacheConfiguration(RedisTemplate<String, ?> template, Mapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, User, FastJsonUser> userRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonUser>) template,
                new StringIdStringKeyFormatter(userPrefix),
                new DozerBeanTransformer<>(User.class, FastJsonUser.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, RouterInfo, FastJsonRouterInfo> routerInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonRouterInfo>) template,
                new LongIdStringKeyFormatter(routerInfoPrefix),
                new DozerBeanTransformer<>(RouterInfo.class, FastJsonRouterInfo.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, RouterSupport, FastJsonRouterSupport> routerSupportRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonRouterSupport>) template,
                new StringIdStringKeyFormatter(routerSupportPrefix),
                new DozerBeanTransformer<>(RouterSupport.class, FastJsonRouterSupport.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, NotifySetting, FastJsonNotifySetting> notifySettingRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonNotifySetting>) template,
                new LongIdStringKeyFormatter(notifySettingPrefix),
                new DozerBeanTransformer<>(NotifySetting.class, FastJsonNotifySetting.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, SenderInfo, FastJsonSenderInfo> senderInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonSenderInfo>) template,
                new LongIdStringKeyFormatter(senderInfoPrefix),
                new DozerBeanTransformer<>(SenderInfo.class, FastJsonSenderInfo.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, SenderSupport, FastJsonSenderSupport> senderSupportRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonSenderSupport>) template,
                new StringIdStringKeyFormatter(senderSupportPrefix),
                new DozerBeanTransformer<>(SenderSupport.class, FastJsonSenderSupport.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, Topic, FastJsonTopic> topicRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonTopic>) template,
                new StringIdStringKeyFormatter(topicPrefix),
                new DozerBeanTransformer<>(Topic.class, FastJsonTopic.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<RelationKey, Relation, FastJsonRelation> relationRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonRelation>) template,
                new RelationStringKeyFormatter(relationPrefix),
                new DozerBeanTransformer<>(Relation.class, FastJsonRelation.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, DispatcherInfo, FastJsonDispatcherInfo> dispatcherInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonDispatcherInfo>) template,
                new LongIdStringKeyFormatter(dispatcherInfoPrefix),
                new DozerBeanTransformer<>(DispatcherInfo.class, FastJsonDispatcherInfo.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, DispatcherSupport, FastJsonDispatcherSupport>
    dispatcherSupportRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonDispatcherSupport>) template,
                new StringIdStringKeyFormatter(dispatcherSupportPrefix),
                new DozerBeanTransformer<>(DispatcherSupport.class, FastJsonDispatcherSupport.class, mapper)
        );
    }
}

package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.sdk.handler.RouterMaker;
import com.dwarfeng.notify.stack.bean.entity.Meta;
import com.dwarfeng.notify.stack.bean.entity.MetaIndicator;
import com.dwarfeng.notify.stack.bean.entity.Topic;
import com.dwarfeng.notify.stack.bean.entity.User;
import com.dwarfeng.notify.stack.bean.key.MetaIndicatorKey;
import com.dwarfeng.notify.stack.bean.key.MetaKey;
import com.dwarfeng.notify.stack.exception.RouterException;
import com.dwarfeng.notify.stack.exception.UnsupportedRouterTypeException;
import com.dwarfeng.notify.stack.handler.Router;
import com.dwarfeng.notify.stack.handler.RouterHandler;
import com.dwarfeng.notify.stack.service.MetaIndicatorMaintainService;
import com.dwarfeng.notify.stack.service.MetaMaintainService;
import com.dwarfeng.notify.stack.service.TopicMaintainService;
import com.dwarfeng.notify.stack.service.UserMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class RouterHandlerImpl implements RouterHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouterHandlerImpl.class);

    private final TopicMaintainService topicMaintainService;
    private final MetaMaintainService metaMaintainService;
    private final MetaIndicatorMaintainService metaIndicatorMaintainService;
    private final UserMaintainService userMaintainService;

    private final HandlerValidator handlerValidator;

    private final List<RouterMaker> routerMakers;

    private final InternalRouterContext routerContext = new InternalRouterContext();

    public RouterHandlerImpl(
            TopicMaintainService topicMaintainService,
            MetaMaintainService metaMaintainService,
            MetaIndicatorMaintainService metaIndicatorMaintainService,
            UserMaintainService userMaintainService,
            HandlerValidator handlerValidator,
            List<RouterMaker> routerMakers
    ) {
        this.topicMaintainService = topicMaintainService;
        this.metaMaintainService = metaMaintainService;
        this.metaIndicatorMaintainService = metaIndicatorMaintainService;
        this.userMaintainService = userMaintainService;
        this.handlerValidator = handlerValidator;
        this.routerMakers = Optional.ofNullable(routerMakers).orElse(Collections.emptyList());
    }

    @Override
    public Router make(String type, String param) throws RouterException {
        try {
            // 生成路由器。
            LOGGER.debug("通过路由器信息构建新的的路由器...");
            RouterMaker routerMaker = routerMakers.stream().filter(maker -> maker.supportType(type))
                    .findFirst().orElseThrow(() -> new UnsupportedRouterTypeException(type));
            Router router = routerMaker.makeRouter(type, param);
            LOGGER.debug("路由器构建成功!");
            router.init(routerContext);
            LOGGER.debug("路由器初始化成功!");
            LOGGER.debug("路由器: {}", router);
            return router;
        } catch (RouterException e) {
            throw e;
        } catch (Exception e) {
            throw new RouterException(e);
        }
    }

    private class InternalRouterContext implements Router.Context {

        @Override
        public List<StringIdKey> availableTopicKeys() throws RouterException {
            try {
                return topicMaintainService.lookupAsList(
                        TopicMaintainService.ENABLED_SORTED, new Object[0]
                ).stream().map(Topic::getKey).collect(Collectors.toList());
            } catch (Exception e) {
                throw new RouterException(e);
            }
        }

        @Override
        public boolean existsMeta(LongIdKey notifySettingKey, StringIdKey topicKey, StringIdKey userKey, String metaId)
                throws RouterException {
            try {
                return metaMaintainService.exists(new MetaKey(
                        notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(), metaId
                ));
            } catch (Exception e) {
                throw new RouterException(e);
            }
        }

        @Override
        public String getMeta(LongIdKey notifySettingKey, StringIdKey topicKey, StringIdKey userKey, String metaId)
                throws RouterException {
            try {
                // 参数有效性验证。
                handlerValidator.makeSureTopicExists(topicKey);
                handlerValidator.makeSureUserExists(userKey);

                Meta meta = metaMaintainService.getIfExists(new MetaKey(
                        notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(), metaId
                ));
                if (Objects.isNull(meta)) {
                    return null;
                }
                return meta.getValue();
            } catch (Exception e) {
                throw new RouterException(e);
            }
        }

        @Override
        public String getDefaultMeta(LongIdKey notifySettingKey, StringIdKey topicKey, String metaId)
                throws RouterException {
            try {
                // 参数有效性验证。
                handlerValidator.makeSureTopicExists(topicKey);

                MetaIndicator metaIndicator = metaIndicatorMaintainService.getIfExists(
                        new MetaIndicatorKey(topicKey.getStringId(), metaId)
                );
                if (Objects.isNull(metaIndicator)) {
                    return null;
                }
                return metaIndicator.getDefaultValue();
            } catch (Exception e) {
                throw new RouterException(e);
            }
        }

        @Override
        public void putMeta(
                LongIdKey notifySettingKey, StringIdKey topicKey, StringIdKey userKey, String metaId, String value
        ) throws RouterException {
            try {
                // 参数有效性验证。
                handlerValidator.makeSureTopicExists(topicKey);
                handlerValidator.makeSureUserExists(userKey);

                Meta meta = new Meta(
                        new MetaKey(
                                notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(),
                                metaId
                        ),
                        value, "通过 InternalRouterContext 更新, 更新日期: " + new Date()
                );
                metaMaintainService.insertOrUpdate(meta);
            } catch (Exception e) {
                throw new RouterException(e);
            }
        }

        @Override
        public List<StringIdKey> filterUser(List<StringIdKey> userKeys) throws RouterException {
            try {
                // 遍历用户主键，将合法的用户添加到结果列表。
                List<StringIdKey> filteredUserKeys = new ArrayList<>();
                for (StringIdKey userKey : userKeys) {
                    if (!userMaintainService.exists(userKey)) {
                        continue;
                    }
                    User user = userMaintainService.get(userKey);
                    if (!user.isEnabled()) {
                        continue;
                    }
                    filteredUserKeys.add(userKey);
                }

                // 返回结果列表。
                return filteredUserKeys;
            } catch (Exception e) {
                throw new RouterException(e);
            }
        }
    }
}

package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.stack.bean.dto.NotifyInfo;
import com.dwarfeng.notify.stack.bean.dto.Routing;
import com.dwarfeng.notify.stack.bean.entity.*;
import com.dwarfeng.notify.stack.bean.entity.key.SenderRelationKey;
import com.dwarfeng.notify.stack.exception.RouterException;
import com.dwarfeng.notify.stack.exception.SenderException;
import com.dwarfeng.notify.stack.handler.*;
import com.dwarfeng.notify.stack.service.*;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class NotifyHandlerImpl implements NotifyHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotifyHandlerImpl.class);

    private final NotifySettingMaintainService notifySettingMaintainService;
    private final RouterInfoMaintainService routerInfoMaintainService;
    private final SenderInfoMaintainService senderInfoMaintainService;
    private final SenderRelationMaintainService senderRelationMaintainService;
    private final TopicMaintainService topicMaintainService;
    private final UserMaintainService userMaintainService;

    private final RouteLocalCacheHandler routeLocalCacheHandler;
    private final SendLocalCacheHandler sendLocalCacheHandler;

    private final PushHandler pushHandler;

    private final HandlerValidator handlerValidator;

    public NotifyHandlerImpl(
            NotifySettingMaintainService notifySettingMaintainService,
            RouterInfoMaintainService routerInfoMaintainService,
            SenderInfoMaintainService senderInfoMaintainService,
            SenderRelationMaintainService senderRelationMaintainService,
            TopicMaintainService topicMaintainService,
            UserMaintainService userMaintainService,
            RouteLocalCacheHandler routeLocalCacheHandler,
            SendLocalCacheHandler sendLocalCacheHandler,
            PushHandler pushHandler,
            HandlerValidator handlerValidator
    ) {
        this.notifySettingMaintainService = notifySettingMaintainService;
        this.routerInfoMaintainService = routerInfoMaintainService;
        this.senderInfoMaintainService = senderInfoMaintainService;
        this.senderRelationMaintainService = senderRelationMaintainService;
        this.topicMaintainService = topicMaintainService;
        this.userMaintainService = userMaintainService;
        this.routeLocalCacheHandler = routeLocalCacheHandler;
        this.sendLocalCacheHandler = sendLocalCacheHandler;
        this.pushHandler = pushHandler;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public void notify(NotifyInfo notifyInfo) throws HandlerException {
        try {
            LongIdKey notifySettingKey = notifyInfo.getNotifySettingKey();
            Object[] context = notifyInfo.getContext();

            // 确认 notifySettingKey 存在。
            handlerValidator.makeSureNotifySettingExists(notifySettingKey);
            NotifySetting notifySetting = notifySettingMaintainService.get(notifySettingKey);

            // 查找 notifySettingKey 对应的所有路由器信息，并通过本地缓存拿出路由器。
            List<RouterInfo> routerInfos = routerInfoMaintainService.lookupAsList(
                    RouterInfoMaintainService.CHILD_FOR_NOTIFY_SETTING, new Object[]{notifySettingKey}
            );
            List<Router> routers = new ArrayList<>();
            for (RouterInfo routerInfo : routerInfos) {
                Router router = routeLocalCacheHandler.getRouter(routerInfo.getKey());
                if (Objects.isNull(router)) {
                    LOGGER.warn("未能获取路由器信息 {} 对应的路由器，请检查配置", routerInfo);
                } else {
                    routers.add(router);
                }
            }

            // 对每个 router 调用解析方法，获得路径的集合（去重）。
            Set<Routing> routingSet = new HashSet<>();
            for (Router router : routers) {
                try {
                    List<Routing> routings = router.parseRouting(context);
                    routingSet.addAll(routings);
                } catch (RouterException e) {
                    LOGGER.warn("路由 " + router + " 解析路径时发生异常，部分路径将不会发送通知，异常信息如下: ", e);
                }
            }

            // 以主题为键，将路径汇聚为映射。
            Map<StringIdKey, List<StringIdKey>> routingMap = new HashMap<>();
            for (Routing routing : routingSet) {
                StringIdKey topicKey = routing.getTopicKey();
                StringIdKey userKey = routing.getUserKey();
                if (routingMap.containsKey(topicKey)) {
                    routingMap.get(topicKey).add(userKey);
                } else {
                    List<StringIdKey> userKeys = new ArrayList<>();
                    userKeys.add(userKey);
                    routingMap.put(topicKey, userKeys);
                }
            }

            // 临时定义本地映射，这些映射缓存了主键与实体的对应关系，避免后续代码频繁操作数据访问层进行读取。
            Map<StringIdKey, Topic> topicMap = new HashMap<>();
            Map<StringIdKey, User> userMap = new HashMap<>();

            // 遍历映射键，确认主题全部存在，查出实体并存放在本地映射中。
            for (StringIdKey topicKey : routingMap.keySet()) {
                handlerValidator.makeSureTopicExists(topicKey);
                Topic topic = topicMaintainService.get(topicKey);
                topicMap.put(topicKey, topic);
            }
            // 遍历映射值，确认用户全部存在，查出实体并存放在本地映射中。
            for (List<StringIdKey> userKeys : routingMap.values()) {
                for (StringIdKey userKey : userKeys) {
                    if (userMap.containsKey(userKey)) {
                        continue;
                    }
                    handlerValidator.makeSureUserExists(userKey);
                    User user = userMaintainService.get(userKey);
                    userMap.put(userKey, user);
                }
            }

            // 遍历映射入口，发送数据。
            for (Map.Entry<StringIdKey, List<StringIdKey>> entry : routingMap.entrySet()) {
                StringIdKey topicKey = entry.getKey();
                List<StringIdKey> userKeys = entry.getValue();
                Topic topic = topicMap.get(topicKey);
                List<User> users = userKeys.stream().map(userMap::get).collect(Collectors.toList());
                notifySingleTopic(notifySettingKey, topicKey, userKeys, context, notifySetting, topic, users);
            }
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    private void notifySingleTopic(
            LongIdKey notifySettingKey, StringIdKey topicKey, List<StringIdKey> userKeys, Object[] context,
            NotifySetting notifySetting, Topic topic, List<User> users
    ) throws Exception {
        // 根据通知设置和主题找到对应的发送器，如果找不到，则警告并退出方法。
        SenderRelationKey senderRelationKey = new SenderRelationKey(
                notifySettingKey.getLongId(), topicKey.getStringId()
        );
        if (!senderRelationMaintainService.exists(senderRelationKey)) {
            LOGGER.warn("找不到通知设置 {} 和主题 {} 对应的发送器", notifySettingKey, topicKey);
            return;
        }

        // 拿到对应的发送器主键。
        SenderRelation senderRelation = senderRelationMaintainService.get(senderRelationKey);
        LongIdKey senderInfoKey = senderRelation.getSenderInfoKey();

        // 从本地缓存中拿到发送器，如果为 null，则警告并退出方法。
        Sender sender = sendLocalCacheHandler.getSender(senderInfoKey);
        if (Objects.isNull(sender)) {
            SenderInfo senderInfo = senderInfoMaintainService.get(senderInfoKey);
            LOGGER.warn("未能获取发送器信息 {} 对应的发送器，请检查配置", senderInfo);
            return;
        }

        // 调用发送器的批量发送方法。
        try {
            sender.batchSend(userKeys, context);
            pushHandler.notifyHappened(notifySetting, topic, users, context);
        } catch (SenderException e) {
            LOGGER.warn("发送器 " + sender + " 发送信息失败，异常信息如下: ", e);
        }
    }
}

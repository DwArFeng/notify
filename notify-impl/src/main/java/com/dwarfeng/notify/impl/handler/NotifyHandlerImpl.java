package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.stack.bean.dto.NotifyInfo;
import com.dwarfeng.notify.stack.bean.entity.*;
import com.dwarfeng.notify.stack.bean.entity.key.*;
import com.dwarfeng.notify.stack.exception.DispatcherException;
import com.dwarfeng.notify.stack.exception.RouterException;
import com.dwarfeng.notify.stack.exception.SenderException;
import com.dwarfeng.notify.stack.handler.*;
import com.dwarfeng.notify.stack.service.*;
import com.dwarfeng.subgrade.stack.bean.key.KeyFetcher;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class NotifyHandlerImpl implements NotifyHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotifyHandlerImpl.class);

    private final ApplicationContext ctx;

    private final TopicMaintainService topicMaintainService;
    private final UserMaintainService userMaintainService;
    private final MetaMaintainService metaMaintainService;
    private final MetaIndicatorMaintainService metaIndicatorMaintainService;
    private final VariableMaintainService variableMaintainService;
    private final VariableIndicatorMaintainService variableIndicatorMaintainService;
    private final SendHistoryMaintainService sendHistoryMaintainService;
    private final RouterInfoMaintainService routerInfoMaintainService;
    private final DispatcherInfoMaintainService dispatcherInfoMaintainService;
    private final SenderInfoMaintainService senderInfoMaintainService;

    private final RouteLocalCacheHandler routeLocalCacheHandler;
    private final DispatchLocalCacheHandler dispatchLocalCacheHandler;
    private final SendLocalCacheHandler sendLocalCacheHandler;

    private final PushHandler pushHandler;

    private final HandlerValidator handlerValidator;

    private final KeyFetcher<LongIdKey> keyFetcher;

    public NotifyHandlerImpl(
            ApplicationContext ctx,
            TopicMaintainService topicMaintainService,
            UserMaintainService userMaintainService,
            MetaMaintainService metaMaintainService,
            MetaIndicatorMaintainService metaIndicatorMaintainService,
            VariableMaintainService variableMaintainService,
            VariableIndicatorMaintainService variableIndicatorMaintainService,
            SendHistoryMaintainService sendHistoryMaintainService,
            RouterInfoMaintainService routerInfoMaintainService,
            DispatcherInfoMaintainService dispatcherInfoMaintainService,
            SenderInfoMaintainService senderInfoMaintainService,
            RouteLocalCacheHandler routeLocalCacheHandler,
            DispatchLocalCacheHandler dispatchLocalCacheHandler,
            SendLocalCacheHandler sendLocalCacheHandler,
            PushHandler pushHandler,
            HandlerValidator handlerValidator,
            KeyFetcher<LongIdKey> keyFetcher
    ) {
        this.ctx = ctx;
        this.topicMaintainService = topicMaintainService;
        this.userMaintainService = userMaintainService;
        this.metaMaintainService = metaMaintainService;
        this.metaIndicatorMaintainService = metaIndicatorMaintainService;
        this.variableMaintainService = variableMaintainService;
        this.variableIndicatorMaintainService = variableIndicatorMaintainService;
        this.sendHistoryMaintainService = sendHistoryMaintainService;
        this.routerInfoMaintainService = routerInfoMaintainService;
        this.dispatcherInfoMaintainService = dispatcherInfoMaintainService;
        this.senderInfoMaintainService = senderInfoMaintainService;
        this.routeLocalCacheHandler = routeLocalCacheHandler;
        this.dispatchLocalCacheHandler = dispatchLocalCacheHandler;
        this.sendLocalCacheHandler = sendLocalCacheHandler;
        this.pushHandler = pushHandler;
        this.handlerValidator = handlerValidator;
        this.keyFetcher = keyFetcher;
    }

    @Override
    public void notify(NotifyInfo notifyInfo) throws HandlerException {
        try {
            // 定义缓存，增加程序的运行速度。
            Map<LongIdKey, String> cachedRouteInfo = new HashMap<>();
            Map<StringIdKey, String> cachedDispatchInfo = new HashMap<>();
            Map<SenderInfoKey, String> cachedSendInfo = new HashMap<>();

            // 获取并处理 notifyInfo 中的字段。
            LongIdKey notifySettingKey = notifyInfo.getNotifySettingKey();
            Map<String, String> routeInfoMap = notifyInfo.getRouteInfoDetails().stream().collect(
                    Collectors.toMap(NotifyInfo.InfoDetail::getType, NotifyInfo.InfoDetail::getInfo)
            );
            Map<String, String> dispatchInfoMap = notifyInfo.getDispatchInfoDetails().stream().collect(
                    Collectors.toMap(NotifyInfo.InfoDetail::getType, NotifyInfo.InfoDetail::getInfo)
            );
            Map<String, String> sendInfoMap = notifyInfo.getSendInfoDetails().stream().collect(
                    Collectors.toMap(NotifyInfo.InfoDetail::getType, NotifyInfo.InfoDetail::getInfo)
            );

            // 确认通知设置有效。
            handlerValidator.makeSureNotifySettingValid(notifySettingKey);

            // 进行路由操作。
            List<StringIdKey> routedUserKeys = routing(notifySettingKey, routeInfoMap, cachedRouteInfo);

            // 进行调度操作。
            List<DispatchedItem> dispatchedItems = dispatching(
                    notifySettingKey, dispatchInfoMap, routedUserKeys, cachedDispatchInfo
            );

            // 进行发送操作。
            List<SentItem> sentItems = sending(notifySettingKey, sendInfoMap, dispatchedItems, cachedSendInfo);

            // 进行后处理操作。
            postprocessing(notifySettingKey, sentItems, cachedRouteInfo, cachedDispatchInfo, cachedSendInfo);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    private List<StringIdKey> routing(
            LongIdKey notifySettingKey, Map<String, String> routeInfoMap, Map<LongIdKey, String> cachedRouteInfo
    ) throws Exception {
        // 通过本地缓存获取路由器。
        Router router = routeLocalCacheHandler.getRouter(notifySettingKey);

        // 根据信息映射以及路由器信息获取路由器的路由信息，并存储到缓存中。
        RouterInfo routerInfo = routerInfoMaintainService.get(notifySettingKey);
        String routeInfo = routeInfoMap.getOrDefault(routerInfo.getType(), null);
        cachedRouteInfo.put(notifySettingKey, routeInfo);

        // 调用路由方法，获取与通知相关的用户主键。
        InternalRouterContext routerContext = ctx.getBean(InternalRouterContext.class, this);
        routerContext.setNotifySettingKey(notifySettingKey);

        // 返回结果列表。
        return router.route(routeInfo, routerContext);
    }

    private List<DispatchedItem> dispatching(
            LongIdKey notifySettingKey, Map<String, String> dispatchInfoMap, List<StringIdKey> routedUserKeys,
            Map<StringIdKey, String> cachedDispatchInfo
    ) throws Exception {
        // 查询所有使能的主题。
        List<StringIdKey> topicKeys = topicMaintainService.lookupAsList(
                TopicMaintainService.ENABLED_SORTED, new Object[0]
        ).stream().map(Topic::getKey).collect(Collectors.toList());

        // 对所有调度器执行启动调度方法，获取每个主题的目标用户，并转换为 Item 结构体。
        List<DispatchedItem> dispatchedItems = new ArrayList<>();
        InternalDispatcherContext dispatcherContext = ctx.getBean(InternalDispatcherContext.class, this);
        dispatcherContext.setNotifySettingKey(notifySettingKey);
        dispatcherContext.setRoutedUserKeys(new HashSet<>(routedUserKeys));
        for (StringIdKey topicKey : topicKeys) {
            try {
                // 对上下文设置当前主题。
                dispatcherContext.setTopicKey(topicKey);

                // 获取主题的调度器。
                Dispatcher dispatcher = dispatchLocalCacheHandler.getDispatcher(topicKey);

                // 根据信息映射以及调度器信息获取调度器的调度信息，并存储到缓存中。
                DispatcherInfo dispatcherInfo = dispatcherInfoMaintainService.get(topicKey);
                String dispatchInfo = dispatchInfoMap.getOrDefault(dispatcherInfo.getType(), null);
                cachedDispatchInfo.put(topicKey, dispatchInfo);

                // 调用调度器，获取需要通过此主题发送通知的用户列表。
                List<StringIdKey> dispatchedUserKeys = dispatcher.dispatch(
                        dispatchInfo, routedUserKeys, dispatcherContext
                );

                // 生成 Item 结构体，并添加到结果列表。
                dispatchedItems.add(new DispatchedItem(topicKey, dispatchedUserKeys));
            } catch (DispatcherException e) {
                LOGGER.warn("主题 " + topicKey + " 调度失败, 将不参与发送, 异常信息如下: ", e);
            }
        }

        // 返回 Item 结构体。
        return dispatchedItems;
    }

    private List<SentItem> sending(
            LongIdKey notifySettingKey, Map<String, String> sendInfoMap, List<DispatchedItem> dispatchedItems,
            Map<SenderInfoKey, String> cachedSendInfo
    )
            throws Exception {
        // 定义 Item 结构体列表。
        List<SentItem> sentItems = new ArrayList<>();

        // 遍历 Item 结构体，对每个主题发送通知，生成发送响应并添加到发送列表中。
        InternalSenderContext senderContext = ctx.getBean(InternalSenderContext.class, this);
        senderContext.setNotifySettingKey(notifySettingKey);
        for (DispatchedItem item : dispatchedItems) {
            // 获取结构体的参数。
            StringIdKey topicKey = item.getTopicKey();
            List<StringIdKey> userKeys = item.getUserKeys();

            try {
                // 对上下文设置当前主题。
                senderContext.setTopicKey(topicKey);

                // 构建 SenderInfoKey;
                SenderInfoKey senderInfoKey = new SenderInfoKey(notifySettingKey.getLongId(), topicKey.getStringId());

                // 获取当前通知设置与当前主题下的发送器。
                Sender sender = sendLocalCacheHandler.getSender(senderInfoKey);

                // 根据信息映射以及发送器信息获取发送器的发送信息，并存储到缓存中。
                SenderInfo senderInfo = senderInfoMaintainService.get(senderInfoKey);
                String sendInfo = sendInfoMap.getOrDefault(senderInfo.getType(), null);
                cachedSendInfo.put(senderInfoKey, sendInfo);

                // 执行发生动作。
                List<Sender.Response> senderResponse = sender.send(sendInfo, userKeys, senderContext);

                // 构建发送结构体，添加到结构体列表中。
                sentItems.add(new SentItem(topicKey, senderResponse, new Date()));
            } catch (SenderException e) {
                LOGGER.warn("主题 " + topicKey + " 发送失败, 异常信息如下: ", e);
            }
        }

        // 返回 Item 结构体。
        return sentItems;
    }

    private void postprocessing(
            LongIdKey notifySettingKey, List<SentItem> sentItems, Map<LongIdKey, String> cachedRouteInfo,
            Map<StringIdKey, String> cachedDispatchInfo, Map<SenderInfoKey, String> cachedSendInfo
    ) throws Exception {
        // 构造发送历史实体。
        List<SendHistory> sendHistories = new ArrayList<>();
        for (SentItem item : sentItems) {
            // 获取 Item 结构体中的参数。
            StringIdKey topicKey = item.getTopicKey();
            List<Sender.Response> senderResponses = item.getSenderResults();
            Date happenedDate = item.getHappenedDate();

            for (Sender.Response senderResponse : senderResponses) {
                StringIdKey userKey = senderResponse.getUserKey();
                boolean succeedFlag = senderResponse.isSucceedFlag();
                String senderMessage = senderResponse.getMessage();
                String routeInfo = cachedRouteInfo.get(notifySettingKey);
                String dispatchInfo = cachedDispatchInfo.get(topicKey);
                SenderInfoKey senderInfoKey = new SenderInfoKey(notifySettingKey.getLongId(), topicKey.getStringId());
                String sendInfo = cachedSendInfo.get(senderInfoKey);
                SendHistory sendHistory = new SendHistory(
                        keyFetcher.fetchKey(), notifySettingKey, topicKey, userKey, happenedDate,
                        routeInfo, dispatchInfo, sendInfo, succeedFlag, senderMessage, "通过 NotifyHandlerImpl 生成"
                );
                sendHistories.add(sendHistory);
            }
        }

        // 记录数据。
        insertSendHistories(sendHistories);

        // 推送数据。
        pushSendHistories(sendHistories);
    }

    private void insertSendHistories(List<SendHistory> sendHistories) {
        try {
            sendHistoryMaintainService.batchInsert(sendHistories);
            return;
        } catch (Exception e) {
            LOGGER.warn("数据插入失败, 试图使用不同的策略进行插入: 逐条插入", e);
        }

        List<SendHistory> failedList = new ArrayList<>();

        for (SendHistory sendHistory : sendHistories) {
            try {
                sendHistoryMaintainService.insert(sendHistory);
            } catch (Exception e) {
                LOGGER.warn("数据插入失败, 放弃对数据的插入: " + sendHistory, e);
                failedList.add(sendHistory);
            }
        }

        if (!failedList.isEmpty()) {
            LOGGER.warn("记录数据时发生异常, 最多 " + failedList.size() + " 个数据信息丢失");
            failedList.forEach(sendHistory -> LOGGER.debug(sendHistory + ""));
        }
    }

    private void pushSendHistories(List<SendHistory> sendHistories) {
        try {
            pushHandler.notifySent(sendHistories);
            return;
        } catch (Exception e) {
            LOGGER.warn("数据推送失败, 试图使用不同的策略进行推送: 逐条推送", e);
        }

        List<SendHistory> failedList = new ArrayList<>();

        for (SendHistory sendHistory : sendHistories) {
            try {
                pushHandler.notifySent(sendHistory);
            } catch (Exception e) {
                LOGGER.warn("数据推送失败, 放弃对数据的推送: " + sendHistory, e);
                failedList.add(sendHistory);
            }
        }

        if (!failedList.isEmpty()) {
            LOGGER.warn("推送数据时发生异常, 最多 " + failedList.size() + " 个数据信息丢失");
            failedList.forEach(sendHistory -> LOGGER.debug(sendHistory + ""));
        }
    }

    @Override
    public Router getRouter(LongIdKey routerInfoKey) throws HandlerException {
        return routeLocalCacheHandler.getRouter(routerInfoKey);
    }

    @Override
    public void clearRouterLocalCache() throws HandlerException {
        routeLocalCacheHandler.clear();
    }

    @Override
    public Dispatcher getDispatcher(StringIdKey dispatcherInfoKey) throws HandlerException {
        return dispatchLocalCacheHandler.getDispatcher(dispatcherInfoKey);
    }

    @Override
    public void clearDispatcherLocalCache() throws HandlerException {
        dispatchLocalCacheHandler.clear();
    }

    @Override
    public Sender getSender(SenderInfoKey senderInfoKey) throws HandlerException {
        return sendLocalCacheHandler.getSender(senderInfoKey);
    }

    @Override
    public void clearSenderLocalCache() throws HandlerException {
        sendLocalCacheHandler.clear();
    }

    private static class DispatchedItem {

        private final StringIdKey topicKey;
        private final List<StringIdKey> userKeys;

        public DispatchedItem(StringIdKey topicKey, List<StringIdKey> userKeys) {
            this.topicKey = topicKey;
            this.userKeys = userKeys;
        }

        public StringIdKey getTopicKey() {
            return topicKey;
        }

        public List<StringIdKey> getUserKeys() {
            return userKeys;
        }

        @Override
        public String toString() {
            return "DispatchedItem{" +
                    "topicKey=" + topicKey +
                    ", userKeys=" + userKeys +
                    '}';
        }
    }

    private static class SentItem {

        private final StringIdKey topicKey;
        private final List<Sender.Response> senderResponses;
        private final Date happenedDate;

        public SentItem(StringIdKey topicKey, List<Sender.Response> senderResponses, Date happenedDate) {
            this.topicKey = topicKey;
            this.senderResponses = senderResponses;
            this.happenedDate = happenedDate;
        }

        public StringIdKey getTopicKey() {
            return topicKey;
        }

        public List<Sender.Response> getSenderResults() {
            return senderResponses;
        }

        public Date getHappenedDate() {
            return happenedDate;
        }

        @Override
        public String toString() {
            return "SentItem{" +
                    "topicKey=" + topicKey +
                    ", senderResults=" + senderResponses +
                    ", happenedDate=" + happenedDate +
                    '}';
        }
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    class InternalRouterContext implements Router.Context {

        private LongIdKey notifySettingKey;

        @Override
        public LongIdKey getNotifySettingKey() {
            return notifySettingKey;
        }

        public void setNotifySettingKey(LongIdKey notifySettingKey) {
            this.notifySettingKey = notifySettingKey;
        }

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
        public boolean existsMeta(StringIdKey topicKey, StringIdKey userKey, String metaId)
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
        public String getMeta(StringIdKey topicKey, StringIdKey userKey, String metaId)
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
        public String getDefaultMeta(StringIdKey topicKey, String metaId) throws RouterException {
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
        public boolean existsVariable(StringIdKey topicKey, StringIdKey userKey, String variableId)
                throws RouterException {
            try {
                return variableMaintainService.exists(new VariableKey(
                        notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(), variableId
                ));
            } catch (Exception e) {
                throw new RouterException(e);
            }
        }

        @Override
        public String getVariable(StringIdKey topicKey, StringIdKey userKey, String variableId)
                throws RouterException {
            try {
                // 参数有效性验证。
                handlerValidator.makeSureTopicExists(topicKey);
                handlerValidator.makeSureUserExists(userKey);

                Variable variable = variableMaintainService.getIfExists(new VariableKey(
                        notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(), variableId
                ));
                if (Objects.isNull(variable)) {
                    return null;
                }
                return variable.getValue();
            } catch (Exception e) {
                throw new RouterException(e);
            }
        }

        @Override
        public String getDefaultVariable(StringIdKey topicKey, String variableId) throws RouterException {
            try {
                // 参数有效性验证。
                handlerValidator.makeSureTopicExists(topicKey);

                VariableIndicator variableIndicator = variableIndicatorMaintainService.getIfExists(
                        new VariableIndicatorKey(topicKey.getStringId(), variableId)
                );
                if (Objects.isNull(variableIndicator)) {
                    return null;
                }
                return variableIndicator.getDefaultValue();
            } catch (Exception e) {
                throw new RouterException(e);
            }
        }

        @Override
        public void putVariable(StringIdKey topicKey, StringIdKey userKey, String variableId, String value)
                throws RouterException {
            try {
                // 参数有效性验证。
                handlerValidator.makeSureTopicExists(topicKey);
                handlerValidator.makeSureUserExists(userKey);

                Variable variable = new Variable(
                        new VariableKey(
                                notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(),
                                variableId
                        ),
                        value, "通过 InternalRouterContext 更新, 更新日期: " + new Date()
                );
                variableMaintainService.insertOrUpdate(variable);
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

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    class InternalDispatcherContext implements Dispatcher.Context {

        private LongIdKey notifySettingKey;
        private StringIdKey topicKey;
        private Set<StringIdKey> routedUserKeys;

        @Override
        public LongIdKey getNotifySettingKey() {
            return notifySettingKey;
        }

        public void setNotifySettingKey(LongIdKey notifySettingKey) {
            this.notifySettingKey = notifySettingKey;
        }

        @Override
        public StringIdKey getTopicKey() {
            return topicKey;
        }

        public void setTopicKey(StringIdKey topicKey) {
            this.topicKey = topicKey;
        }

        public Set<StringIdKey> getRoutedUserKeys() {
            return routedUserKeys;
        }

        public void setRoutedUserKeys(Set<StringIdKey> routedUserKeys) {
            this.routedUserKeys = routedUserKeys;
        }

        @Override
        public boolean existsMeta(StringIdKey userKey, String metaId) throws DispatcherException {
            try {
                return metaMaintainService.exists(new MetaKey(
                        notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(), metaId
                ));
            } catch (Exception e) {
                throw new DispatcherException(e);
            }
        }

        @Override
        public String getMeta(StringIdKey userKey, String metaId) throws DispatcherException {
            try {
                // 参数有效性验证。
                handlerValidator.makeSureUserExists(userKey);

                Meta meta = metaMaintainService.getIfExists(new MetaKey(
                        notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(), metaId
                ));
                if (Objects.isNull(meta)) {
                    return null;
                }
                return meta.getValue();
            } catch (Exception e) {
                throw new DispatcherException(e);
            }
        }

        @Override
        public String getDefaultMeta(String metaId) throws DispatcherException {
            try {
                MetaIndicator metaIndicator = metaIndicatorMaintainService.getIfExists(
                        new MetaIndicatorKey(topicKey.getStringId(), metaId)
                );
                if (Objects.isNull(metaIndicator)) {
                    return null;
                }
                return metaIndicator.getDefaultValue();
            } catch (Exception e) {
                throw new DispatcherException(e);
            }
        }

        @Override
        public boolean existsVariable(StringIdKey userKey, String variableId) throws DispatcherException {
            try {
                return variableMaintainService.exists(new VariableKey(
                        notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(), variableId
                ));
            } catch (Exception e) {
                throw new DispatcherException(e);
            }
        }

        @Override
        public String getVariable(StringIdKey userKey, String variableId) throws DispatcherException {
            try {
                // 参数有效性验证。
                handlerValidator.makeSureUserExists(userKey);

                Variable variable = variableMaintainService.getIfExists(new VariableKey(
                        notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(), variableId
                ));
                if (Objects.isNull(variable)) {
                    return null;
                }
                return variable.getValue();
            } catch (Exception e) {
                throw new DispatcherException(e);
            }
        }

        @Override
        public String getDefaultVariable(String variableId) throws DispatcherException {
            try {
                VariableIndicator variableIndicator = variableIndicatorMaintainService.getIfExists(
                        new VariableIndicatorKey(topicKey.getStringId(), variableId)
                );
                if (Objects.isNull(variableIndicator)) {
                    return null;
                }
                return variableIndicator.getDefaultValue();
            } catch (Exception e) {
                throw new DispatcherException(e);
            }
        }

        @Override
        public void putVariable(StringIdKey userKey, String variableId, String value) throws DispatcherException {
            try {
                // 参数有效性验证。
                handlerValidator.makeSureUserExists(userKey);

                Variable variable = new Variable(
                        new VariableKey(
                                notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(),
                                variableId
                        ),
                        value, "通过 InternalRouterContext 更新, 更新日期: " + new Date()
                );
                variableMaintainService.insertOrUpdate(variable);
            } catch (Exception e) {
                throw new DispatcherException(e);
            }
        }

        @Override
        public List<StringIdKey> filterUser(List<StringIdKey> userKeys) throws DispatcherException {
            try {
                // 遍历用户主键，将合法的用户添加到结果列表。
                List<StringIdKey> filteredUserKeys = new ArrayList<>();
                for (StringIdKey userKey : userKeys) {
                    if (!routedUserKeys.contains(userKey)) {
                        continue;
                    }
                    filteredUserKeys.add(userKey);
                }

                // 返回结果列表。
                return filteredUserKeys;
            } catch (Exception e) {
                throw new DispatcherException(e);
            }
        }
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    class InternalSenderContext implements Sender.Context {

        private LongIdKey notifySettingKey;
        private StringIdKey topicKey;

        @Override
        public LongIdKey getNotifySettingKey() {
            return notifySettingKey;
        }

        public void setNotifySettingKey(LongIdKey notifySettingKey) {
            this.notifySettingKey = notifySettingKey;
        }

        @Override
        public StringIdKey getTopicKey() {
            return topicKey;
        }

        public void setTopicKey(StringIdKey topicKey) {
            this.topicKey = topicKey;
        }

        @Override
        public boolean existsMeta(StringIdKey userKey, String metaId) throws SenderException {
            try {
                return metaMaintainService.exists(new MetaKey(
                        notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(), metaId
                ));
            } catch (Exception e) {
                throw new SenderException(e);
            }
        }

        @Override
        public String getMeta(StringIdKey userKey, String metaId) throws SenderException {
            try {
                // 参数有效性验证。
                handlerValidator.makeSureUserExists(userKey);

                Meta meta = metaMaintainService.getIfExists(new MetaKey(
                        notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(), metaId
                ));
                if (Objects.isNull(meta)) {
                    return null;
                }
                return meta.getValue();
            } catch (Exception e) {
                throw new SenderException(e);
            }
        }

        @Override
        public String getDefaultMeta(String metaId) throws SenderException {
            try {
                MetaIndicator metaIndicator = metaIndicatorMaintainService.getIfExists(
                        new MetaIndicatorKey(topicKey.getStringId(), metaId)
                );
                if (Objects.isNull(metaIndicator)) {
                    return null;
                }
                return metaIndicator.getDefaultValue();
            } catch (Exception e) {
                throw new SenderException(e);
            }
        }

        @Override
        public boolean existsVariable(StringIdKey userKey, String variableId) throws SenderException {
            try {
                return variableMaintainService.exists(new VariableKey(
                        notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(), variableId
                ));
            } catch (Exception e) {
                throw new SenderException(e);
            }
        }

        @Override
        public String getVariable(StringIdKey userKey, String variableId) throws SenderException {
            try {
                // 参数有效性验证。
                handlerValidator.makeSureUserExists(userKey);

                Variable variable = variableMaintainService.getIfExists(new VariableKey(
                        notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(), variableId
                ));
                if (Objects.isNull(variable)) {
                    return null;
                }
                return variable.getValue();
            } catch (Exception e) {
                throw new SenderException(e);
            }
        }

        @Override
        public String getDefaultVariable(String variableId) throws SenderException {
            try {
                VariableIndicator variableIndicator = variableIndicatorMaintainService.getIfExists(
                        new VariableIndicatorKey(topicKey.getStringId(), variableId)
                );
                if (Objects.isNull(variableIndicator)) {
                    return null;
                }
                return variableIndicator.getDefaultValue();
            } catch (Exception e) {
                throw new SenderException(e);
            }
        }

        @Override
        public void putVariable(StringIdKey userKey, String variableId, String value) throws SenderException {
            try {
                // 参数有效性验证。
                handlerValidator.makeSureUserExists(userKey);

                Variable variable = new Variable(
                        new VariableKey(
                                notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(),
                                variableId
                        ),
                        value, "通过 InternalRouterContext 更新, 更新日期: " + new Date()
                );
                variableMaintainService.insertOrUpdate(variable);
            } catch (Exception e) {
                throw new SenderException(e);
            }
        }
    }
}

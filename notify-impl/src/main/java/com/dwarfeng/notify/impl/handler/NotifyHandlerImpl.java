package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.stack.bean.dto.NotifyInfo;
import com.dwarfeng.notify.stack.bean.entity.*;
import com.dwarfeng.notify.stack.bean.entity.key.PreferenceIndicatorKey;
import com.dwarfeng.notify.stack.bean.entity.key.PreferenceKey;
import com.dwarfeng.notify.stack.bean.entity.key.SenderInfoKey;
import com.dwarfeng.notify.stack.bean.entity.key.VariableKey;
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
    private final PreferenceMaintainService preferenceMaintainService;
    private final PreferenceIndicatorMaintainService preferenceIndicatorMaintainService;
    private final VariableMaintainService variableMaintainService;
    private final SendHistoryMaintainService sendHistoryMaintainService;

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
            PreferenceMaintainService preferenceMaintainService,
            PreferenceIndicatorMaintainService preferenceIndicatorMaintainService,
            VariableMaintainService variableMaintainService,
            SendHistoryMaintainService sendHistoryMaintainService, RouteLocalCacheHandler routeLocalCacheHandler,
            DispatchLocalCacheHandler dispatchLocalCacheHandler,
            SendLocalCacheHandler sendLocalCacheHandler,
            PushHandler pushHandler,
            HandlerValidator handlerValidator,
            KeyFetcher<LongIdKey> keyFetcher
    ) {
        this.ctx = ctx;
        this.topicMaintainService = topicMaintainService;
        this.userMaintainService = userMaintainService;
        this.preferenceMaintainService = preferenceMaintainService;
        this.preferenceIndicatorMaintainService = preferenceIndicatorMaintainService;
        this.variableMaintainService = variableMaintainService;
        this.sendHistoryMaintainService = sendHistoryMaintainService;
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
            LongIdKey notifySettingKey = notifyInfo.getNotifySettingKey();
            String routeInfo = notifyInfo.getRouteInfo();
            String dispatchInfo = notifyInfo.getDispatchInfo();
            String sendInfo = notifyInfo.getSendInfo();

            // 确认通知设置有效。
            handlerValidator.makeSureNotifySettingValid(notifySettingKey);

            // 进行路由操作。
            List<StringIdKey> routedUserKeys = routing(notifySettingKey, routeInfo);

            // 进行调度操作。
            List<DispatchedItem> dispatchedItems = dispatching(notifySettingKey, dispatchInfo, routedUserKeys);

            // 进行发送操作。
            List<SentItem> sentItems = sending(notifySettingKey, sendInfo, dispatchedItems);

            // 进行后处理操作。
            postprocessing(notifySettingKey, routeInfo, dispatchInfo, sendInfo, sentItems);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    private List<StringIdKey> routing(LongIdKey notifySettingKey, String routeInfo) throws Exception {
        // 通过本地缓存获取路由器。
        Router router = routeLocalCacheHandler.getRouter(notifySettingKey);

        // 调用路由方法，获取与通知相关的用户主键。
        InternalRouterContext routerContext = ctx.getBean(InternalRouterContext.class, this);
        routerContext.setNotifySettingKey(notifySettingKey);

        // 返回结果列表。
        return router.route(routeInfo, routerContext);
    }

    private List<DispatchedItem> dispatching(
            LongIdKey notifySettingKey, String dispatchInfo, List<StringIdKey> routedUserKeys
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

    private List<SentItem> sending(LongIdKey notifySettingKey, String sendInfo, List<DispatchedItem> dispatchedItems)
            throws Exception {
        // 定义 Item 结构体列表。
        List<SentItem> sentItems = new ArrayList<>();

        // 遍历 Item 结构体，对每个主题发送通知，生成发送结果并添加到发送列表中。
        InternalSenderContext senderContext = ctx.getBean(InternalSenderContext.class, this);
        senderContext.setNotifySettingKey(notifySettingKey);
        for (DispatchedItem item : dispatchedItems) {
            // 获取结构体的参数。
            StringIdKey topicKey = item.getTopicKey();
            List<StringIdKey> userKeys = item.getUserKeys();

            try {
                // 对上下文设置当前主题。
                senderContext.setTopicKey(topicKey);

                // 获取当前通知设置与当前主题下的发送器。
                Sender sender = sendLocalCacheHandler.getSender(
                        new SenderInfoKey(notifySettingKey.getLongId(), topicKey.getStringId())
                );

                // 执行发生动作。
                List<Sender.Result> senderResult = sender.send(sendInfo, userKeys, senderContext);

                // 构建发送结构体，添加到结构体列表中。
                sentItems.add(new SentItem(topicKey, senderResult, new Date()));
            } catch (SenderException e) {
                LOGGER.warn("主题 " + topicKey + " 发送失败, 异常信息如下: ", e);
            }
        }

        // 返回 Item 结构体。
        return sentItems;
    }

    private void postprocessing(
            LongIdKey notifySettingKey, String routeInfo, String dispatchInfo, String sendInfo,
            List<SentItem> sentItems
    ) throws Exception {
        // 构造发送历史实体。
        List<SendHistory> sendHistories = new ArrayList<>();
        for (SentItem item : sentItems) {
            // 获取 Item 结构体中的参数。
            StringIdKey topicKey = item.getTopicKey();
            List<Sender.Result> senderResults = item.getSenderResults();
            Date happenedDate = item.getHappenedDate();

            for (Sender.Result senderResult : senderResults) {
                StringIdKey userKey = senderResult.getUserKey();
                boolean succeedFlag = senderResult.isSucceedFlag();
                String senderMessage = senderResult.getMessage();
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
        private final List<Sender.Result> senderResults;
        private final Date happenedDate;

        public SentItem(StringIdKey topicKey, List<Sender.Result> senderResults, Date happenedDate) {
            this.topicKey = topicKey;
            this.senderResults = senderResults;
            this.happenedDate = happenedDate;
        }

        public StringIdKey getTopicKey() {
            return topicKey;
        }

        public List<Sender.Result> getSenderResults() {
            return senderResults;
        }

        public Date getHappenedDate() {
            return happenedDate;
        }

        @Override
        public String toString() {
            return "SentItem{" +
                    "topicKey=" + topicKey +
                    ", senderResults=" + senderResults +
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
        public boolean existsPreference(StringIdKey topicKey, StringIdKey userKey, String preferenceId)
                throws RouterException {
            try {
                return preferenceMaintainService.exists(new PreferenceKey(
                        notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(), preferenceId
                ));
            } catch (Exception e) {
                throw new RouterException(e);
            }
        }

        @Override
        public String getPreference(StringIdKey topicKey, StringIdKey userKey, String preferenceId)
                throws RouterException {
            try {
                Preference preference = preferenceMaintainService.getIfExists(new PreferenceKey(
                        notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(), preferenceId
                ));
                if (Objects.isNull(preference)) {
                    return null;
                }
                return preference.getValue();
            } catch (Exception e) {
                throw new RouterException(e);
            }
        }

        @Override
        public String getDefaultPreference(StringIdKey topicKey, String preferenceId) throws RouterException {
            try {
                PreferenceIndicator preferenceIndicator = preferenceIndicatorMaintainService.getIfExists(
                        new PreferenceIndicatorKey(topicKey.getStringId(), preferenceId)
                );
                if (Objects.isNull(preferenceIndicator)) {
                    return null;
                }
                return preferenceIndicator.getDefaultValue();
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
        public void putVariable(StringIdKey topicKey, StringIdKey userKey, String variableId, String value)
                throws RouterException {
            try {
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
        public boolean existsPreference(StringIdKey userKey, String preferenceId) throws DispatcherException {
            try {
                return preferenceMaintainService.exists(new PreferenceKey(
                        notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(), preferenceId
                ));
            } catch (Exception e) {
                throw new DispatcherException(e);
            }
        }

        @Override
        public String getPreference(StringIdKey userKey, String preferenceId) throws DispatcherException {
            try {
                Preference preference = preferenceMaintainService.getIfExists(new PreferenceKey(
                        notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(), preferenceId
                ));
                if (Objects.isNull(preference)) {
                    return null;
                }
                return preference.getValue();
            } catch (Exception e) {
                throw new DispatcherException(e);
            }
        }

        @Override
        public String getDefaultPreference(StringIdKey userKey, String preferenceId) throws DispatcherException {
            try {
                PreferenceIndicator preferenceIndicator = preferenceIndicatorMaintainService.getIfExists(
                        new PreferenceIndicatorKey(topicKey.getStringId(), preferenceId)
                );
                if (Objects.isNull(preferenceIndicator)) {
                    return null;
                }
                return preferenceIndicator.getDefaultValue();
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
        public void putVariable(StringIdKey userKey, String variableId, String value) throws DispatcherException {
            try {
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
        public boolean existsPreference(StringIdKey userKey, String preferenceId) throws SenderException {
            try {
                return preferenceMaintainService.exists(new PreferenceKey(
                        notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(), preferenceId
                ));
            } catch (Exception e) {
                throw new SenderException(e);
            }
        }

        @Override
        public String getPreference(StringIdKey userKey, String preferenceId) throws SenderException {
            try {
                Preference preference = preferenceMaintainService.getIfExists(new PreferenceKey(
                        notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(), preferenceId
                ));
                if (Objects.isNull(preference)) {
                    return null;
                }
                return preference.getValue();
            } catch (Exception e) {
                throw new SenderException(e);
            }
        }

        @Override
        public String getDefaultPreference(StringIdKey userKey, String preferenceId) throws SenderException {
            try {
                PreferenceIndicator preferenceIndicator = preferenceIndicatorMaintainService.getIfExists(
                        new PreferenceIndicatorKey(topicKey.getStringId(), preferenceId)
                );
                if (Objects.isNull(preferenceIndicator)) {
                    return null;
                }
                return preferenceIndicator.getDefaultValue();
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
        public void putVariable(StringIdKey userKey, String variableId, String value) throws SenderException {
            try {
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

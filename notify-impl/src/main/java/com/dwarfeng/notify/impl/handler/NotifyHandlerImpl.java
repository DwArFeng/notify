package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.sdk.util.Constants;
import com.dwarfeng.notify.stack.bean.dto.NotifyHistoryRecordInfo;
import com.dwarfeng.notify.stack.bean.dto.NotifyInfo;
import com.dwarfeng.notify.stack.bean.entity.NotifyHistory;
import com.dwarfeng.notify.stack.bean.entity.NotifyInfoRecord;
import com.dwarfeng.notify.stack.bean.entity.NotifySendRecord;
import com.dwarfeng.notify.stack.bean.entity.Topic;
import com.dwarfeng.notify.stack.bean.key.NotifyInfoRecordKey;
import com.dwarfeng.notify.stack.bean.key.NotifySendRecordKey;
import com.dwarfeng.notify.stack.bean.key.SenderInfoKey;
import com.dwarfeng.notify.stack.exception.DispatcherException;
import com.dwarfeng.notify.stack.exception.SenderException;
import com.dwarfeng.notify.stack.handler.*;
import com.dwarfeng.notify.stack.service.NotifyHistoryMaintainService;
import com.dwarfeng.notify.stack.service.NotifyInfoRecordMaintainService;
import com.dwarfeng.notify.stack.service.NotifySendRecordMaintainService;
import com.dwarfeng.notify.stack.service.TopicMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.generation.KeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class NotifyHandlerImpl implements NotifyHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotifyHandlerImpl.class);

    private final TopicMaintainService topicMaintainService;
    private final NotifyHistoryMaintainService notifyHistoryMaintainService;
    private final NotifyInfoRecordMaintainService notifyInfoRecordMaintainService;
    private final NotifySendRecordMaintainService notifySendRecordMaintainService;

    private final RouteLocalCacheHandler routeLocalCacheHandler;
    private final DispatchLocalCacheHandler dispatchLocalCacheHandler;
    private final SendLocalCacheHandler sendLocalCacheHandler;

    private final PushHandler pushHandler;

    private final HandlerValidator handlerValidator;

    private final KeyGenerator<LongIdKey> keyGenerator;

    public NotifyHandlerImpl(
            TopicMaintainService topicMaintainService,
            NotifyHistoryMaintainService notifyHistoryMaintainService,
            NotifyInfoRecordMaintainService notifyInfoRecordMaintainService,
            NotifySendRecordMaintainService notifySendRecordMaintainService,
            RouteLocalCacheHandler routeLocalCacheHandler,
            DispatchLocalCacheHandler dispatchLocalCacheHandler,
            SendLocalCacheHandler sendLocalCacheHandler,
            PushHandler pushHandler,
            HandlerValidator handlerValidator,
            KeyGenerator<LongIdKey> keyGenerator
    ) {
        this.topicMaintainService = topicMaintainService;
        this.notifyHistoryMaintainService = notifyHistoryMaintainService;
        this.notifyInfoRecordMaintainService = notifyInfoRecordMaintainService;
        this.notifySendRecordMaintainService = notifySendRecordMaintainService;
        this.routeLocalCacheHandler = routeLocalCacheHandler;
        this.dispatchLocalCacheHandler = dispatchLocalCacheHandler;
        this.sendLocalCacheHandler = sendLocalCacheHandler;
        this.pushHandler = pushHandler;
        this.handlerValidator = handlerValidator;
        this.keyGenerator = keyGenerator;
    }

    @Override
    public void notify(NotifyInfo notifyInfo) throws HandlerException {
        try {
            // 获取并处理 notifyInfo 中的字段。
            LongIdKey notifySettingKey = notifyInfo.getNotifySettingKey();
            Map<String, String> routeInfoMap = notifyInfo.getRouteInfoMap();
            Map<String, String> dispatchInfoMap = notifyInfo.getDispatchInfoMap();
            Map<String, String> sendInfoMap = notifyInfo.getSendInfoMap();

            // 确认通知设置有效。
            handlerValidator.makeSureNotifySettingValid(notifySettingKey);

            // 进行路由操作。
            List<StringIdKey> routedUserKeys = routing(notifySettingKey, routeInfoMap);

            // 进行调度操作。
            List<DispatchedItem> dispatchedItems = dispatching(notifySettingKey, dispatchInfoMap, routedUserKeys);

            // 进行发送操作。
            List<SentItem> sentItems = sending(notifySettingKey, sendInfoMap, dispatchedItems);

            // 进行后处理操作。
            postprocessing(notifySettingKey, sentItems, routeInfoMap, dispatchInfoMap, sendInfoMap);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private List<StringIdKey> routing(
            LongIdKey notifySettingKey, Map<String, String> routeInfoMap
    ) throws Exception {
        // 通过本地缓存获取路由器及其类型。
        Router router = routeLocalCacheHandler.get(notifySettingKey);

        // 调用路由方法，获取与通知相关的用户主键。
        Router.ContextInfo routerContextInfo = new Router.ContextInfo(notifySettingKey);
        return router.route(routerContextInfo, routeInfoMap);
    }

    private List<DispatchedItem> dispatching(
            LongIdKey notifySettingKey, Map<String, String> dispatchInfoMap, List<StringIdKey> routedUserKeys
    ) throws Exception {
        // 查询所有使能的主题。
        List<StringIdKey> topicKeys = topicMaintainService.lookupAsList(
                TopicMaintainService.ENABLED_SORTED, new Object[0]
        ).stream().map(Topic::getKey).collect(Collectors.toList());

        // 对所有调度器执行启动调度方法，获取每个主题的目标用户，并转换为 Item 结构体。
        List<DispatchedItem> dispatchedItems = new ArrayList<>();
        for (StringIdKey topicKey : topicKeys) {
            try {
                // 获取主题的调度器及其类型。
                Dispatcher dispatcher = dispatchLocalCacheHandler.get(topicKey);

                // 调用调度器，获取需要通过此主题发送通知的用户列表。
                Dispatcher.ContextInfo dispatcherContextInfo = new Dispatcher.ContextInfo(notifySettingKey, topicKey);
                List<StringIdKey> dispatchedUserKeys = dispatcher.dispatch(
                        dispatcherContextInfo, dispatchInfoMap, routedUserKeys
                );

                // 生成 Item 结构体，并添加到结果列表。
                dispatchedItems.add(new DispatchedItem(topicKey, dispatchedUserKeys));
            } catch (DispatcherException e) {
                LOGGER.warn("主题 {} 调度失败, 将不参与发送, 异常信息如下: ", topicKey, e);
            }
        }

        // 返回 Item 结构体。
        return dispatchedItems;
    }

    private List<SentItem> sending(
            LongIdKey notifySettingKey, Map<String, String> sendInfoMap, List<DispatchedItem> dispatchedItems
    ) throws Exception {
        // 定义 Item 结构体列表。
        List<SentItem> sentItems = new ArrayList<>();

        // 遍历 Item 结构体，对每个主题发送通知，生成发送响应并添加到发送列表中。
        for (DispatchedItem item : dispatchedItems) {
            // 获取结构体的参数。
            StringIdKey topicKey = item.getTopicKey();
            List<StringIdKey> userKeys = item.getUserKeys();

            try {
                // 获取当前通知设置与当前主题下的发送器及其类型。
                Sender sender = sendLocalCacheHandler.get(
                        new SenderInfoKey(notifySettingKey.getLongId(), topicKey.getStringId())
                );

                // 执行发生动作。
                Sender.ContextInfo senderContextInfo = new Sender.ContextInfo(notifySettingKey, topicKey);
                List<Sender.Response> senderResponse = sender.send(senderContextInfo, sendInfoMap, userKeys);

                // 构建发送结构体，添加到结构体列表中。
                sentItems.add(new SentItem(topicKey, senderResponse));
            } catch (SenderException e) {
                LOGGER.warn("主题 {} 发送失败, 异常信息如下: ", topicKey, e);
            }
        }

        // 返回 Item 结构体。
        return sentItems;
    }

    private void postprocessing(
            LongIdKey notifySettingKey, List<SentItem> sentItems, Map<String, String> routeInfoMap,
            Map<String, String> dispatchInfoMap, Map<String, String> sendInfoMap
    ) throws Exception {
        // 实体定义。
        NotifyHistory persistNotifyHistory;
        List<NotifyInfoRecord> persistNotifyInfoRecords = new ArrayList<>();
        List<NotifySendRecord> persistNotifySendRecords = new ArrayList<>();
        List<NotifyHistoryRecordInfo.InfoRecord> pushInfoRecords = new ArrayList<>();
        List<NotifyHistoryRecordInfo.SendRecord> pushSendRecords = new ArrayList<>();
        NotifyHistoryRecordInfo pushNotifyHistoryRecordInfo;

        // 构造相关实体。
        Date currentDate = new Date();
        LongIdKey notifyHistoryKey = keyGenerator.generate();
        long notifyHistoryId = notifyHistoryKey.getLongId();
        persistNotifyHistory = new NotifyHistory(
                notifyHistoryKey, notifySettingKey, currentDate, "通过 NotifyHandlerImpl 生成"
        );
        for (Map.Entry<String, String> entry : routeInfoMap.entrySet()) {
            persistNotifyInfoRecords.add(new NotifyInfoRecord(
                    new NotifyInfoRecordKey(
                            notifyHistoryId, Constants.NOTIFY_INFO_RECORD_TYPE_ROUTE_INFO, entry.getKey()
                    ),
                    entry.getValue()
            ));
            pushInfoRecords.add(new NotifyHistoryRecordInfo.InfoRecord(
                    Constants.NOTIFY_INFO_RECORD_TYPE_ROUTE_INFO, entry.getKey(), entry.getValue()
            ));
        }
        for (Map.Entry<String, String> entry : dispatchInfoMap.entrySet()) {
            persistNotifyInfoRecords.add(new NotifyInfoRecord(
                    new NotifyInfoRecordKey(
                            notifyHistoryId, Constants.NOTIFY_INFO_RECORD_TYPE_DISPATCH_INFO, entry.getKey()
                    ),
                    entry.getValue()
            ));
            pushInfoRecords.add(new NotifyHistoryRecordInfo.InfoRecord(
                    Constants.NOTIFY_INFO_RECORD_TYPE_DISPATCH_INFO, entry.getKey(), entry.getValue()
            ));
        }
        for (Map.Entry<String, String> entry : sendInfoMap.entrySet()) {
            persistNotifyInfoRecords.add(new NotifyInfoRecord(
                    new NotifyInfoRecordKey(
                            notifyHistoryId, Constants.NOTIFY_INFO_RECORD_TYPE_SEND_INFO, entry.getKey()
                    ),
                    entry.getValue()
            ));
            pushInfoRecords.add(new NotifyHistoryRecordInfo.InfoRecord(
                    Constants.NOTIFY_INFO_RECORD_TYPE_SEND_INFO, entry.getKey(), entry.getValue()
            ));
        }
        for (SentItem item : sentItems) {
            for (Sender.Response response : item.getResponses()) {
                persistNotifySendRecords.add(new NotifySendRecord(
                        new NotifySendRecordKey(
                                notifyHistoryId, item.getTopicKey().getStringId(), response.getUserKey().getStringId()
                        ),
                        response.isSucceedFlag(),
                        response.getMessage()
                ));
                pushSendRecords.add(new NotifyHistoryRecordInfo.SendRecord(
                        item.getTopicKey(), response.getUserKey(), response.isSucceedFlag(), response.getMessage()
                ));
            }
        }
        pushNotifyHistoryRecordInfo = new NotifyHistoryRecordInfo(
                notifyHistoryKey, notifySettingKey, currentDate, "通过 NotifyHandlerImpl 生成",
                pushInfoRecords, pushSendRecords
        );

        // 插入历史记录信息。
        notifyHistoryMaintainService.insert(persistNotifyHistory);
        notifyInfoRecordMaintainService.batchInsert(persistNotifyInfoRecords);
        notifySendRecordMaintainService.batchInsert(persistNotifySendRecords);

        // 推送历史记录信息插入事件。
        try {
            pushHandler.notifyHistoryRecorded(pushNotifyHistoryRecordInfo);
        } catch (Exception e) {
            LOGGER.warn("历史记录信息插入事件推送失败, 放弃对数据的推送: {}", pushNotifyHistoryRecordInfo, e);
        }
    }

    @Override
    public Router getRouter(LongIdKey routerInfoKey) throws HandlerException {
        return routeLocalCacheHandler.get(routerInfoKey);
    }

    @Override
    public void clearRouterLocalCache() throws HandlerException {
        routeLocalCacheHandler.clear();
    }

    @Override
    public Dispatcher getDispatcher(StringIdKey dispatcherInfoKey) throws HandlerException {
        return dispatchLocalCacheHandler.get(dispatcherInfoKey);
    }

    @Override
    public void clearDispatcherLocalCache() throws HandlerException {
        dispatchLocalCacheHandler.clear();
    }

    @Override
    public Sender getSender(SenderInfoKey senderInfoKey) throws HandlerException {
        return sendLocalCacheHandler.get(senderInfoKey);
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
        private final List<Sender.Response> responses;

        public SentItem(StringIdKey topicKey, List<Sender.Response> responses) {
            this.topicKey = topicKey;
            this.responses = responses;
        }

        public StringIdKey getTopicKey() {
            return topicKey;
        }

        public List<Sender.Response> getResponses() {
            return responses;
        }

        @Override
        public String toString() {
            return "SentItem{" +
                    "topicKey=" + topicKey +
                    ", responses=" + responses +
                    '}';
        }
    }
}

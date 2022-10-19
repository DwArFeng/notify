package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.stack.bean.entity.NotifySetting;
import com.dwarfeng.notify.stack.bean.entity.Topic;
import com.dwarfeng.notify.stack.bean.entity.key.SenderInfoKey;
import com.dwarfeng.notify.stack.exception.*;
import com.dwarfeng.notify.stack.service.*;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 处理器验证器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
@Component
public class HandlerValidator {

    private final NotifySettingMaintainService notifySettingMaintainService;
    private final RouterInfoMaintainService routerInfoMaintainService;
    private final TopicMaintainService topicMaintainService;
    private final DispatcherInfoMaintainService dispatcherInfoMaintainService;
    private final SenderInfoMaintainService senderInfoMaintainService;
    private final UserMaintainService userMaintainService;

    public HandlerValidator(
            NotifySettingMaintainService notifySettingMaintainService,
            RouterInfoMaintainService routerInfoMaintainService,
            TopicMaintainService topicMaintainService,
            DispatcherInfoMaintainService dispatcherInfoMaintainService,
            SenderInfoMaintainService senderInfoMaintainService,
            UserMaintainService userMaintainService
    ) {
        this.notifySettingMaintainService = notifySettingMaintainService;
        this.routerInfoMaintainService = routerInfoMaintainService;
        this.topicMaintainService = topicMaintainService;
        this.dispatcherInfoMaintainService = dispatcherInfoMaintainService;
        this.senderInfoMaintainService = senderInfoMaintainService;
        this.userMaintainService = userMaintainService;
    }

    public void makeSureNotifySettingExists(LongIdKey notifySettingKey) throws HandlerException {
        try {
            if (Objects.isNull(notifySettingKey) || !notifySettingMaintainService.exists(notifySettingKey)) {
                throw new NotifySettingNotExistsException(notifySettingKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureRouterInfoExists(LongIdKey routerInfoKey) throws HandlerException {
        try {
            if (Objects.isNull(routerInfoKey) || !routerInfoMaintainService.exists(routerInfoKey)) {
                throw new RouterInfoNotExistsException(routerInfoKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureTopicExists(StringIdKey topicKey) throws HandlerException {
        try {
            if (Objects.isNull(topicKey) || !topicMaintainService.exists(topicKey)) {
                throw new TopicNotExistsException(topicKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureDispatcherInfoExists(StringIdKey dispatcherInfoKey) throws HandlerException {
        try {
            if (Objects.isNull(dispatcherInfoKey) || !dispatcherInfoMaintainService.exists(dispatcherInfoKey)) {
                throw new DispatcherInfoNotExistsException(dispatcherInfoKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureSenderInfoExists(SenderInfoKey senderInfoKey) throws HandlerException {
        try {
            if (Objects.isNull(senderInfoKey) || !senderInfoMaintainService.exists(senderInfoKey)) {
                throw new SenderInfoNotExistsException(senderInfoKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureUserExists(StringIdKey userKey) throws HandlerException {
        try {
            if (Objects.isNull(userKey) || !userMaintainService.exists(userKey)) {
                throw new UserNotExistsException(userKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureNotifySettingEnabled(LongIdKey notifySettingKey) throws HandlerException {
        try {
            if (Objects.isNull(notifySettingKey) || !notifySettingMaintainService.exists(notifySettingKey)) {
                throw new NotifySettingNotExistsException(notifySettingKey);
            }
            NotifySetting notifySetting = notifySettingMaintainService.get(notifySettingKey);
            if (!notifySetting.isEnabled()) {
                throw new NotifySettingDisabledException(notifySettingKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureNotifySettingValid(LongIdKey notifySettingKey) throws HandlerException {
        try {
            // 确认通知设置存在。
            makeSureNotifySettingEnabled(notifySettingKey);

            // 确认通知设置使能。
            makeSureNotifySettingEnabled(notifySettingKey);

            // 确认通知设置对应的路由信息存在。
            makeSureRouterInfoExists(notifySettingKey);

            // 查询所有使能的主题。
            List<StringIdKey> topicKeys = topicMaintainService.lookupAsList(
                    TopicMaintainService.ENABLED, new Object[0]
            ).stream().map(Topic::getKey).collect(Collectors.toList());

            // 确认所有的主题的调度器存在。
            for (StringIdKey topicKey : topicKeys) {
                makeSureDispatcherInfoExists(topicKey);
            }

            // 确认通知设置与所有主题的发送器存在。
            for (StringIdKey topicKey : topicKeys) {
                makeSureSenderInfoExists(new SenderInfoKey(notifySettingKey.getLongId(), topicKey.getStringId()));
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }
}

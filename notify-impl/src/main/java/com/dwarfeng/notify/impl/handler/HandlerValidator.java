package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.stack.exception.NotifySettingNotExistsException;
import com.dwarfeng.notify.stack.exception.TopicNotExistsException;
import com.dwarfeng.notify.stack.exception.UserNotExistsException;
import com.dwarfeng.notify.stack.service.NotifySettingMaintainService;
import com.dwarfeng.notify.stack.service.TopicMaintainService;
import com.dwarfeng.notify.stack.service.UserMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 处理器验证器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
@Component
public class HandlerValidator {

    private final NotifySettingMaintainService notifySettingMaintainService;
    private final TopicMaintainService topicMaintainService;
    private final UserMaintainService userMaintainService;

    public HandlerValidator(
            NotifySettingMaintainService notifySettingMaintainService,
            TopicMaintainService topicMaintainService,
            UserMaintainService userMaintainService
    ) {
        this.notifySettingMaintainService = notifySettingMaintainService;
        this.topicMaintainService = topicMaintainService;
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

    public void makeSureTopicExists(StringIdKey topicKey) throws HandlerException {
        try {
            if (Objects.isNull(topicKey) || !topicMaintainService.exists(topicKey)) {
                throw new TopicNotExistsException(topicKey);
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
}

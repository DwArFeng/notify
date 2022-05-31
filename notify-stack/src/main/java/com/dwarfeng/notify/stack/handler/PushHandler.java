package com.dwarfeng.notify.stack.handler;

import com.dwarfeng.notify.stack.bean.entity.NotifySetting;
import com.dwarfeng.notify.stack.bean.entity.Topic;
import com.dwarfeng.notify.stack.bean.entity.User;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

import java.util.List;

/**
 * 推送处理器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface PushHandler extends Handler {

    /**
     * 通知发生时执行的推送动作。
     *
     * @param notifySetting 通知设置。
     * @param topic         主题。
     * @param user          通知的用户。
     * @param context       上下文对象。
     * @throws HandlerException 处理器异常。
     */
    void notifyHappened(NotifySetting notifySetting, Topic topic, User user, Object[] context) throws HandlerException;

    /**
     * 通知发生时执行的推送动作。
     *
     * @param notifySetting 通知设置。
     * @param topic         主题。
     * @param users         通知的用户组成的列表。
     * @param context       上下文对象。
     * @throws HandlerException 处理器异常。
     */
    default void notifyHappened(NotifySetting notifySetting, Topic topic, List<User> users, Object[] context)
            throws HandlerException {
        for (User user : users) {
            notifyHappened(notifySetting, topic, user, context);
        }
    }
}

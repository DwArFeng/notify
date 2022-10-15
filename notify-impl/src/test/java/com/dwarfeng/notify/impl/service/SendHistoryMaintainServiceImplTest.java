package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.NotifySetting;
import com.dwarfeng.notify.stack.bean.entity.SendHistory;
import com.dwarfeng.notify.stack.bean.entity.Topic;
import com.dwarfeng.notify.stack.bean.entity.User;
import com.dwarfeng.notify.stack.service.NotifySettingMaintainService;
import com.dwarfeng.notify.stack.service.SendHistoryMaintainService;
import com.dwarfeng.notify.stack.service.TopicMaintainService;
import com.dwarfeng.notify.stack.service.UserMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class SendHistoryMaintainServiceImplTest {

    private static final long NOTIFY_SETTING_ID = 12450L;
    private static final String TOPIC_ID = "test.topic";
    private static final String USER_ID = "test.user";

    @Autowired
    private NotifySettingMaintainService notifySettingMaintainService;
    @Autowired
    private TopicMaintainService topicMaintainService;
    @Autowired
    private UserMaintainService userMaintainService;
    @Autowired
    private SendHistoryMaintainService sendHistoryMaintainService;

    private NotifySetting notifySetting;
    private Topic topic;
    private User user;
    private final List<SendHistory> sendHistories = new ArrayList<>();

    @Before
    public void setUp() {
        notifySetting = new NotifySetting(new LongIdKey(NOTIFY_SETTING_ID), "label", "remark", true);
        topic = new Topic(new StringIdKey(TOPIC_ID), "label", "remark", true, 12450);
        user = new User(new StringIdKey(USER_ID), "remark");
        for (int i = 0; i < 5; i++) {
            SendHistory sendHistory = new SendHistory(
                    null, new LongIdKey(NOTIFY_SETTING_ID), new StringIdKey(TOPIC_ID), new StringIdKey(USER_ID),
                    new Date(), "routeInfo", "sendInfo", true, "remark"
            );
            sendHistories.add(sendHistory);
        }
    }

    @After
    public void tearDown() {
        notifySetting = null;
        topic = null;
        user = null;
        sendHistories.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            notifySettingMaintainService.insertOrUpdate(notifySetting);
            topicMaintainService.insertOrUpdate(topic);
            userMaintainService.insertOrUpdate(user);

            for (SendHistory sendHistory : sendHistories) {
                sendHistory.setKey(sendHistoryMaintainService.insertOrUpdate(sendHistory));
                sendHistoryMaintainService.update(sendHistory);
                SendHistory testSendHistory = sendHistoryMaintainService.get(sendHistory.getKey());
                assertEquals(BeanUtils.describe(sendHistory), BeanUtils.describe(testSendHistory));
            }
        } finally {
            for (SendHistory sendHistory : sendHistories) {
                sendHistoryMaintainService.deleteIfExists(sendHistory.getKey());
            }
            userMaintainService.deleteIfExists(user.getKey());
            topicMaintainService.deleteIfExists(topic.getKey());
            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
        }
    }

    @Test
    public void testForNotifySettingCascade() throws Exception {
        try {
            notifySettingMaintainService.insertOrUpdate(notifySetting);
            topicMaintainService.insertOrUpdate(topic);
            userMaintainService.insertOrUpdate(user);
            for (SendHistory sendHistory : sendHistories) {
                sendHistory.setKey(sendHistoryMaintainService.insertOrUpdate(sendHistory));
            }

            assertEquals(sendHistories.size(), sendHistoryMaintainService.lookup(
                    SendHistoryMaintainService.CHILD_FOR_NOTIFY_SETTING,
                    new Object[]{notifySetting.getKey()}).getCount()
            );

            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());

            assertEquals(0, sendHistoryMaintainService.lookup(
                    SendHistoryMaintainService.CHILD_FOR_NOTIFY_SETTING,
                    new Object[]{notifySetting.getKey()}).getCount()
            );
            assertTrue(sendHistoryMaintainService.allExists(
                    sendHistories.stream().map(SendHistory::getKey).collect(Collectors.toList())
            ));
            for (SendHistory sendHistory : sendHistories) {
                SendHistory testSendHistory = sendHistoryMaintainService.get(sendHistory.getKey());
                assertNull(testSendHistory.getNotifySettingKey());
            }
        } finally {
            for (SendHistory sendHistory : sendHistories) {
                sendHistoryMaintainService.deleteIfExists(sendHistory.getKey());
            }
            userMaintainService.deleteIfExists(user.getKey());
            topicMaintainService.deleteIfExists(topic.getKey());
            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
        }
    }

    @Test
    public void testForTopicCascade() throws Exception {
        try {
            notifySettingMaintainService.insertOrUpdate(notifySetting);
            topicMaintainService.insertOrUpdate(topic);
            userMaintainService.insertOrUpdate(user);
            for (SendHistory sendHistory : sendHistories) {
                sendHistory.setKey(sendHistoryMaintainService.insertOrUpdate(sendHistory));
            }

            assertEquals(sendHistories.size(), sendHistoryMaintainService.lookup(
                    SendHistoryMaintainService.CHILD_FOR_TOPIC,
                    new Object[]{topic.getKey()}).getCount()
            );

            topicMaintainService.deleteIfExists(topic.getKey());

            assertEquals(0, sendHistoryMaintainService.lookup(
                    SendHistoryMaintainService.CHILD_FOR_TOPIC,
                    new Object[]{topic.getKey()}).getCount()
            );
            assertTrue(sendHistoryMaintainService.allExists(
                    sendHistories.stream().map(SendHistory::getKey).collect(Collectors.toList())
            ));
            for (SendHistory sendHistory : sendHistories) {
                SendHistory testSendHistory = sendHistoryMaintainService.get(sendHistory.getKey());
                assertNull(testSendHistory.getTopicKey());
            }
        } finally {
            for (SendHistory sendHistory : sendHistories) {
                sendHistoryMaintainService.deleteIfExists(sendHistory.getKey());
            }
            userMaintainService.deleteIfExists(user.getKey());
            topicMaintainService.deleteIfExists(topic.getKey());
            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
        }
    }

    @Test
    public void testForUserCascade() throws Exception {
        try {
            notifySettingMaintainService.insertOrUpdate(notifySetting);
            topicMaintainService.insertOrUpdate(topic);
            userMaintainService.insertOrUpdate(user);
            for (SendHistory sendHistory : sendHistories) {
                sendHistory.setKey(sendHistoryMaintainService.insertOrUpdate(sendHistory));
            }

            assertEquals(sendHistories.size(), sendHistoryMaintainService.lookup(
                    SendHistoryMaintainService.CHILD_FOR_USER,
                    new Object[]{user.getKey()}).getCount()
            );

            userMaintainService.deleteIfExists(user.getKey());

            assertEquals(0, sendHistoryMaintainService.lookup(
                    SendHistoryMaintainService.CHILD_FOR_USER,
                    new Object[]{user.getKey()}).getCount()
            );
            assertTrue(sendHistoryMaintainService.allExists(
                    sendHistories.stream().map(SendHistory::getKey).collect(Collectors.toList())
            ));
            for (SendHistory sendHistory : sendHistories) {
                SendHistory testSendHistory = sendHistoryMaintainService.get(sendHistory.getKey());
                assertNull(testSendHistory.getUserKey());
            }
        } finally {
            for (SendHistory sendHistory : sendHistories) {
                sendHistoryMaintainService.deleteIfExists(sendHistory.getKey());
            }
            userMaintainService.deleteIfExists(user.getKey());
            topicMaintainService.deleteIfExists(topic.getKey());
            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
        }
    }
}

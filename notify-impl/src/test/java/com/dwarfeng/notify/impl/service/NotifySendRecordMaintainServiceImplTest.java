package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.NotifyHistory;
import com.dwarfeng.notify.stack.bean.entity.NotifySendRecord;
import com.dwarfeng.notify.stack.bean.entity.Topic;
import com.dwarfeng.notify.stack.bean.entity.User;
import com.dwarfeng.notify.stack.service.NotifyHistoryMaintainService;
import com.dwarfeng.notify.stack.service.NotifySendRecordMaintainService;
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
public class NotifySendRecordMaintainServiceImplTest {

    private static final long NOTIFY_HISTORY_ID = 12450L;
    private static final String TOPIC_ID = "test.topic";
    private static final String USER_ID = "test.user";

    @Autowired
    private NotifyHistoryMaintainService notifyHistoryMaintainService;
    @Autowired
    private TopicMaintainService topicMaintainService;
    @Autowired
    private UserMaintainService userMaintainService;
    @Autowired
    private NotifySendRecordMaintainService notifySendRecordMaintainService;

    private NotifyHistory notifyHistory;
    private Topic topic;
    private User user;
    private final List<NotifySendRecord> notifySendRecords = new ArrayList<>();

    @Before
    public void setUp() {
        notifyHistory = new NotifyHistory(new LongIdKey(NOTIFY_HISTORY_ID), null, new Date(), "remark");
        topic = new Topic(new StringIdKey(TOPIC_ID), "label", "remark", true, 12450);
        user = new User(new StringIdKey(USER_ID), "remark", true);
        for (int i = 0; i < 5; i++) {
            NotifySendRecord notifySendRecord = new NotifySendRecord(
                    null, new LongIdKey(NOTIFY_HISTORY_ID), new StringIdKey(TOPIC_ID), new StringIdKey(USER_ID),
                    true, "senderMessage"
            );
            notifySendRecords.add(notifySendRecord);
        }
    }

    @After
    public void tearDown() {
        notifyHistory = null;
        topic = null;
        user = null;
        notifySendRecords.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            notifyHistoryMaintainService.insertOrUpdate(notifyHistory);
            topicMaintainService.insertOrUpdate(topic);
            userMaintainService.insertOrUpdate(user);

            for (NotifySendRecord notifySendRecord : notifySendRecords) {
                notifySendRecord.setKey(notifySendRecordMaintainService.insertOrUpdate(notifySendRecord));
                notifySendRecordMaintainService.update(notifySendRecord);
                NotifySendRecord testNotifySendRecord = notifySendRecordMaintainService.get(notifySendRecord.getKey());
                assertEquals(BeanUtils.describe(notifySendRecord), BeanUtils.describe(testNotifySendRecord));
            }
        } finally {
            for (NotifySendRecord notifySendRecord : notifySendRecords) {
                notifySendRecordMaintainService.deleteIfExists(notifySendRecord.getKey());
            }
            userMaintainService.deleteIfExists(user.getKey());
            topicMaintainService.deleteIfExists(topic.getKey());
            notifyHistoryMaintainService.deleteIfExists(notifyHistory.getKey());
        }
    }

    @Test
    public void testForNotifyHistoryCascade() throws Exception {
        try {
            notifyHistoryMaintainService.insertOrUpdate(notifyHistory);
            topicMaintainService.insertOrUpdate(topic);
            userMaintainService.insertOrUpdate(user);
            for (NotifySendRecord notifySendRecord : notifySendRecords) {
                notifySendRecord.setKey(notifySendRecordMaintainService.insertOrUpdate(notifySendRecord));
            }

            assertEquals(notifySendRecords.size(), notifySendRecordMaintainService.lookup(
                    NotifySendRecordMaintainService.CHILD_FOR_NOTIFY_HISTORY,
                    new Object[]{notifyHistory.getKey()}).getCount()
            );

            notifyHistoryMaintainService.deleteIfExists(notifyHistory.getKey());

            assertEquals(0, notifySendRecordMaintainService.lookup(
                    NotifySendRecordMaintainService.CHILD_FOR_NOTIFY_HISTORY,
                    new Object[]{notifyHistory.getKey()}).getCount()
            );
            assertTrue(notifySendRecordMaintainService.nonExists(
                    notifySendRecords.stream().map(NotifySendRecord::getKey).collect(Collectors.toList())
            ));
        } finally {
            for (NotifySendRecord notifySendRecord : notifySendRecords) {
                notifySendRecordMaintainService.deleteIfExists(notifySendRecord.getKey());
            }
            userMaintainService.deleteIfExists(user.getKey());
            topicMaintainService.deleteIfExists(topic.getKey());
            notifyHistoryMaintainService.deleteIfExists(notifyHistory.getKey());
        }
    }

    @Test
    public void testForTopicCascade() throws Exception {
        try {
            notifyHistoryMaintainService.insertOrUpdate(notifyHistory);
            topicMaintainService.insertOrUpdate(topic);
            userMaintainService.insertOrUpdate(user);
            for (NotifySendRecord notifySendRecord : notifySendRecords) {
                notifySendRecord.setKey(notifySendRecordMaintainService.insertOrUpdate(notifySendRecord));
            }

            assertEquals(notifySendRecords.size(), notifySendRecordMaintainService.lookup(
                    NotifySendRecordMaintainService.CHILD_FOR_TOPIC,
                    new Object[]{topic.getKey()}).getCount()
            );

            topicMaintainService.deleteIfExists(topic.getKey());

            assertEquals(0, notifySendRecordMaintainService.lookup(
                    NotifySendRecordMaintainService.CHILD_FOR_TOPIC,
                    new Object[]{topic.getKey()}).getCount()
            );
            assertTrue(notifySendRecordMaintainService.allExists(
                    notifySendRecords.stream().map(NotifySendRecord::getKey).collect(Collectors.toList())
            ));
            for (NotifySendRecord notifySendRecord : notifySendRecords) {
                NotifySendRecord testNotifySendRecord = notifySendRecordMaintainService.get(notifySendRecord.getKey());
                assertNull(testNotifySendRecord.getTopicKey());
            }
        } finally {
            for (NotifySendRecord notifySendRecord : notifySendRecords) {
                notifySendRecordMaintainService.deleteIfExists(notifySendRecord.getKey());
            }
            userMaintainService.deleteIfExists(user.getKey());
            topicMaintainService.deleteIfExists(topic.getKey());
            notifyHistoryMaintainService.deleteIfExists(notifyHistory.getKey());
        }
    }

    @Test
    public void testForUserCascade() throws Exception {
        try {
            notifyHistoryMaintainService.insertOrUpdate(notifyHistory);
            topicMaintainService.insertOrUpdate(topic);
            userMaintainService.insertOrUpdate(user);
            for (NotifySendRecord notifySendRecord : notifySendRecords) {
                notifySendRecord.setKey(notifySendRecordMaintainService.insertOrUpdate(notifySendRecord));
            }

            assertEquals(notifySendRecords.size(), notifySendRecordMaintainService.lookup(
                    NotifySendRecordMaintainService.CHILD_FOR_USER,
                    new Object[]{user.getKey()}).getCount()
            );

            userMaintainService.deleteIfExists(user.getKey());

            assertEquals(0, notifySendRecordMaintainService.lookup(
                    NotifySendRecordMaintainService.CHILD_FOR_USER,
                    new Object[]{user.getKey()}).getCount()
            );
            assertTrue(notifySendRecordMaintainService.allExists(
                    notifySendRecords.stream().map(NotifySendRecord::getKey).collect(Collectors.toList())
            ));
            for (NotifySendRecord notifySendRecord : notifySendRecords) {
                NotifySendRecord testNotifySendRecord = notifySendRecordMaintainService.get(notifySendRecord.getKey());
                assertNull(testNotifySendRecord.getUserKey());
            }
        } finally {
            for (NotifySendRecord notifySendRecord : notifySendRecords) {
                notifySendRecordMaintainService.deleteIfExists(notifySendRecord.getKey());
            }
            userMaintainService.deleteIfExists(user.getKey());
            topicMaintainService.deleteIfExists(topic.getKey());
            notifyHistoryMaintainService.deleteIfExists(notifyHistory.getKey());
        }
    }
}

package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.NotifyHistory;
import com.dwarfeng.notify.stack.bean.entity.NotifySendRecord;
import com.dwarfeng.notify.stack.bean.entity.Topic;
import com.dwarfeng.notify.stack.bean.entity.User;
import com.dwarfeng.notify.stack.bean.key.NotifySendRecordKey;
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

import java.util.Date;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
    private NotifySendRecord notifySendRecord;

    @Before
    public void setUp() {
        notifyHistory = new NotifyHistory(new LongIdKey(NOTIFY_HISTORY_ID), null, new Date(), "remark");
        topic = new Topic(new StringIdKey(TOPIC_ID), "label", "remark", true, 12450);
        user = new User(new StringIdKey(USER_ID), "remark", true);
        notifySendRecord = new NotifySendRecord(
                new NotifySendRecordKey(NOTIFY_HISTORY_ID, TOPIC_ID, USER_ID), true, "senderMessage"
        );
    }

    @After
    public void tearDown() {
        notifyHistory = null;
        topic = null;
        user = null;
        notifySendRecord = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            notifyHistoryMaintainService.insertOrUpdate(notifyHistory);
            topicMaintainService.insertOrUpdate(topic);
            userMaintainService.insertOrUpdate(user);

            notifySendRecord.setKey(notifySendRecordMaintainService.insertOrUpdate(notifySendRecord));
            notifySendRecordMaintainService.update(notifySendRecord);
            NotifySendRecord testNotifySendRecord = notifySendRecordMaintainService.get(notifySendRecord.getKey());
            assertEquals(BeanUtils.describe(notifySendRecord), BeanUtils.describe(testNotifySendRecord));
        } finally {
            if (Objects.nonNull(notifySendRecord.getKey())) {
                notifySendRecordMaintainService.deleteIfExists(notifySendRecord.getKey());
            }
            if (Objects.nonNull(user.getKey())) {
                userMaintainService.deleteIfExists(user.getKey());
            }
            if (Objects.nonNull(topic.getKey())) {
                topicMaintainService.deleteIfExists(topic.getKey());
            }
            if (Objects.nonNull(notifyHistory.getKey())) {
                notifyHistoryMaintainService.deleteIfExists(notifyHistory.getKey());
            }
        }
    }

    @Test
    public void testForNotifyHistoryCascade() throws Exception {
        try {
            notifyHistoryMaintainService.insertOrUpdate(notifyHistory);
            topicMaintainService.insertOrUpdate(topic);
            userMaintainService.insertOrUpdate(user);
            notifySendRecord.setKey(notifySendRecordMaintainService.insertOrUpdate(notifySendRecord));

            assertEquals(1, notifySendRecordMaintainService.lookup(
                    NotifySendRecordMaintainService.CHILD_FOR_NOTIFY_HISTORY,
                    new Object[]{notifyHistory.getKey()}).getCount()
            );

            notifyHistoryMaintainService.deleteIfExists(notifyHistory.getKey());

            assertEquals(0, notifySendRecordMaintainService.lookup(
                    NotifySendRecordMaintainService.CHILD_FOR_NOTIFY_HISTORY,
                    new Object[]{notifyHistory.getKey()}).getCount()
            );
            assertFalse(notifySendRecordMaintainService.exists(notifySendRecord.getKey()));
        } finally {
            if (Objects.nonNull(notifySendRecord.getKey())) {
                notifySendRecordMaintainService.deleteIfExists(notifySendRecord.getKey());
            }
            if (Objects.nonNull(user.getKey())) {
                userMaintainService.deleteIfExists(user.getKey());
            }
            if (Objects.nonNull(topic.getKey())) {
                topicMaintainService.deleteIfExists(topic.getKey());
            }
            if (Objects.nonNull(notifyHistory.getKey())) {
                notifyHistoryMaintainService.deleteIfExists(notifyHistory.getKey());
            }
        }
    }

    @Test
    public void testForTopicCascade() throws Exception {
        try {
            notifyHistoryMaintainService.insertOrUpdate(notifyHistory);
            topicMaintainService.insertOrUpdate(topic);
            userMaintainService.insertOrUpdate(user);
            notifySendRecord.setKey(notifySendRecordMaintainService.insertOrUpdate(notifySendRecord));

            assertEquals(1, notifySendRecordMaintainService.lookup(
                    NotifySendRecordMaintainService.CHILD_FOR_TOPIC,
                    new Object[]{topic.getKey()}).getCount()
            );

            topicMaintainService.deleteIfExists(topic.getKey());

            assertEquals(0, notifySendRecordMaintainService.lookup(
                    NotifySendRecordMaintainService.CHILD_FOR_TOPIC,
                    new Object[]{topic.getKey()}).getCount()
            );
            assertFalse(notifySendRecordMaintainService.exists(notifySendRecord.getKey()));
        } finally {
            if (Objects.nonNull(notifySendRecord.getKey())) {
                notifySendRecordMaintainService.deleteIfExists(notifySendRecord.getKey());
            }
            if (Objects.nonNull(user.getKey())) {
                userMaintainService.deleteIfExists(user.getKey());
            }
            if (Objects.nonNull(topic.getKey())) {
                topicMaintainService.deleteIfExists(topic.getKey());
            }
            if (Objects.nonNull(notifyHistory.getKey())) {
                notifyHistoryMaintainService.deleteIfExists(notifyHistory.getKey());
            }
        }
    }

    @Test
    public void testForUserCascade() throws Exception {
        try {
            notifyHistoryMaintainService.insertOrUpdate(notifyHistory);
            topicMaintainService.insertOrUpdate(topic);
            userMaintainService.insertOrUpdate(user);
            notifySendRecord.setKey(notifySendRecordMaintainService.insertOrUpdate(notifySendRecord));

            assertEquals(1, notifySendRecordMaintainService.lookup(
                    NotifySendRecordMaintainService.CHILD_FOR_USER,
                    new Object[]{user.getKey()}).getCount()
            );

            userMaintainService.deleteIfExists(user.getKey());

            assertEquals(0, notifySendRecordMaintainService.lookup(
                    NotifySendRecordMaintainService.CHILD_FOR_USER,
                    new Object[]{user.getKey()}).getCount()
            );
            assertFalse(notifySendRecordMaintainService.exists(notifySendRecord.getKey()));
        } finally {
            if (Objects.nonNull(notifySendRecord.getKey())) {
                notifySendRecordMaintainService.deleteIfExists(notifySendRecord.getKey());
            }
            if (Objects.nonNull(user.getKey())) {
                userMaintainService.deleteIfExists(user.getKey());
            }
            if (Objects.nonNull(topic.getKey())) {
                topicMaintainService.deleteIfExists(topic.getKey());
            }
            if (Objects.nonNull(notifyHistory.getKey())) {
                notifyHistoryMaintainService.deleteIfExists(notifyHistory.getKey());
            }
        }
    }
}

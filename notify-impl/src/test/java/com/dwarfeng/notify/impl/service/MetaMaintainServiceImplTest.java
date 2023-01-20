package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.Meta;
import com.dwarfeng.notify.stack.bean.entity.NotifySetting;
import com.dwarfeng.notify.stack.bean.entity.Topic;
import com.dwarfeng.notify.stack.bean.entity.User;
import com.dwarfeng.notify.stack.bean.key.MetaKey;
import com.dwarfeng.notify.stack.service.MetaMaintainService;
import com.dwarfeng.notify.stack.service.NotifySettingMaintainService;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class MetaMaintainServiceImplTest {

    private static final long NOTIFY_SETTING_ID = 12450L;
    private static final String TOPIC_ID = "test.topic";
    private static final String USER_ID = "test.user";
    private static final String META_ID = "test.meta";

    @Autowired
    private NotifySettingMaintainService notifySettingMaintainService;
    @Autowired
    private TopicMaintainService topicMaintainService;
    @Autowired
    private UserMaintainService userMaintainService;
    @Autowired
    private MetaMaintainService metaMaintainService;

    private NotifySetting notifySetting;
    private Topic topic;
    private User user;
    private Meta meta;

    @Before
    public void setUp() {
        notifySetting = new NotifySetting(new LongIdKey(NOTIFY_SETTING_ID), "label", "remark", true);
        topic = new Topic(new StringIdKey(TOPIC_ID), "label", "remark", true, 12450);
        user = new User(new StringIdKey(USER_ID), "remark", true);
        meta = new Meta(
                new MetaKey(NOTIFY_SETTING_ID, TOPIC_ID, USER_ID, META_ID), "value", "remark"
        );
    }

    @After
    public void tearDown() {
        notifySetting = null;
        topic = null;
        user = null;
        meta = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            notifySettingMaintainService.insertOrUpdate(notifySetting);
            topicMaintainService.insertOrUpdate(topic);
            userMaintainService.insertOrUpdate(user);

            metaMaintainService.insertOrUpdate(meta);
            metaMaintainService.update(meta);
            Meta testMeta = metaMaintainService.get(meta.getKey());
            assertEquals(BeanUtils.describe(meta), BeanUtils.describe(testMeta));
        } finally {
            metaMaintainService.deleteIfExists(meta.getKey());
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
            metaMaintainService.insertOrUpdate(meta);

            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
            assertFalse(metaMaintainService.exists(meta.getKey()));
        } finally {
            metaMaintainService.deleteIfExists(meta.getKey());
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
            metaMaintainService.insertOrUpdate(meta);

            topicMaintainService.deleteIfExists(topic.getKey());
            assertFalse(metaMaintainService.exists(meta.getKey()));
        } finally {
            metaMaintainService.deleteIfExists(meta.getKey());
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
            metaMaintainService.insertOrUpdate(meta);

            userMaintainService.deleteIfExists(user.getKey());
            assertFalse(metaMaintainService.exists(meta.getKey()));
        } finally {
            metaMaintainService.deleteIfExists(meta.getKey());
            userMaintainService.deleteIfExists(user.getKey());
            topicMaintainService.deleteIfExists(topic.getKey());
            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
        }
    }
}

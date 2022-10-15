package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.NotifySetting;
import com.dwarfeng.notify.stack.bean.entity.Preference;
import com.dwarfeng.notify.stack.bean.entity.Topic;
import com.dwarfeng.notify.stack.bean.entity.User;
import com.dwarfeng.notify.stack.bean.entity.key.PreferenceKey;
import com.dwarfeng.notify.stack.service.NotifySettingMaintainService;
import com.dwarfeng.notify.stack.service.PreferenceMaintainService;
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
public class PreferenceMaintainServiceImplTest {

    private static final long NOTIFY_SETTING_ID = 12450L;
    private static final String TOPIC_ID = "test.topic";
    private static final String USER_ID = "test.user";
    private static final String PREFERENCE_ID = "test.preference";

    @Autowired
    private NotifySettingMaintainService notifySettingMaintainService;
    @Autowired
    private TopicMaintainService topicMaintainService;
    @Autowired
    private UserMaintainService userMaintainService;
    @Autowired
    private PreferenceMaintainService preferenceMaintainService;

    private NotifySetting notifySetting;
    private Topic topic;
    private User user;
    private Preference preference;

    @Before
    public void setUp() {
        notifySetting = new NotifySetting(new LongIdKey(NOTIFY_SETTING_ID), "label", "remark", true);
        topic = new Topic(new StringIdKey(TOPIC_ID), "label", "remark", true, 12450);
        user = new User(new StringIdKey(USER_ID), "remark");
        preference = new Preference(
                new PreferenceKey(NOTIFY_SETTING_ID, TOPIC_ID, USER_ID, PREFERENCE_ID), "value", "remark"
        );
    }

    @After
    public void tearDown() {
        notifySetting = null;
        topic = null;
        user = null;
        preference = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            notifySettingMaintainService.insertOrUpdate(notifySetting);
            topicMaintainService.insertOrUpdate(topic);
            userMaintainService.insertOrUpdate(user);

            preferenceMaintainService.insertOrUpdate(preference);
            preferenceMaintainService.update(preference);
            Preference testPreference = preferenceMaintainService.get(preference.getKey());
            assertEquals(BeanUtils.describe(preference), BeanUtils.describe(testPreference));
        } finally {
            preferenceMaintainService.deleteIfExists(preference.getKey());
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
            preferenceMaintainService.insertOrUpdate(preference);

            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
            assertFalse(preferenceMaintainService.exists(preference.getKey()));
        } finally {
            preferenceMaintainService.deleteIfExists(preference.getKey());
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
            preferenceMaintainService.insertOrUpdate(preference);

            topicMaintainService.deleteIfExists(topic.getKey());
            assertFalse(preferenceMaintainService.exists(preference.getKey()));
        } finally {
            preferenceMaintainService.deleteIfExists(preference.getKey());
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
            preferenceMaintainService.insertOrUpdate(preference);

            userMaintainService.deleteIfExists(user.getKey());
            assertFalse(preferenceMaintainService.exists(preference.getKey()));
        } finally {
            preferenceMaintainService.deleteIfExists(preference.getKey());
            userMaintainService.deleteIfExists(user.getKey());
            topicMaintainService.deleteIfExists(topic.getKey());
            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
        }
    }
}

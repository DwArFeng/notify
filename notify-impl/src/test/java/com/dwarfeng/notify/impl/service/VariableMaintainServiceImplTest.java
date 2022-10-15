package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.NotifySetting;
import com.dwarfeng.notify.stack.bean.entity.Topic;
import com.dwarfeng.notify.stack.bean.entity.User;
import com.dwarfeng.notify.stack.bean.entity.Variable;
import com.dwarfeng.notify.stack.bean.entity.key.VariableKey;
import com.dwarfeng.notify.stack.service.NotifySettingMaintainService;
import com.dwarfeng.notify.stack.service.TopicMaintainService;
import com.dwarfeng.notify.stack.service.UserMaintainService;
import com.dwarfeng.notify.stack.service.VariableMaintainService;
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
public class VariableMaintainServiceImplTest {

    private static final long NOTIFY_SETTING_ID = 12450L;
    private static final String TOPIC_ID = "test.topic";
    private static final String USER_ID = "test.user";
    private static final String VARIABLE_ID = "test.variable";

    @Autowired
    private NotifySettingMaintainService notifySettingMaintainService;
    @Autowired
    private TopicMaintainService topicMaintainService;
    @Autowired
    private UserMaintainService userMaintainService;
    @Autowired
    private VariableMaintainService variableMaintainService;

    private NotifySetting notifySetting;
    private Topic topic;
    private User user;
    private Variable variable;

    @Before
    public void setUp() {
        notifySetting = new NotifySetting(new LongIdKey(NOTIFY_SETTING_ID), "label", "remark", true);
        topic = new Topic(new StringIdKey(TOPIC_ID), "label", "remark", true, 12450);
        user = new User(new StringIdKey(USER_ID), "remark", true);
        variable = new Variable(
                new VariableKey(NOTIFY_SETTING_ID, TOPIC_ID, USER_ID, VARIABLE_ID), "value", "remark"
        );
    }

    @After
    public void tearDown() {
        notifySetting = null;
        topic = null;
        user = null;
        variable = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            notifySettingMaintainService.insertOrUpdate(notifySetting);
            topicMaintainService.insertOrUpdate(topic);
            userMaintainService.insertOrUpdate(user);

            variableMaintainService.insertOrUpdate(variable);
            variableMaintainService.update(variable);
            Variable testVariable = variableMaintainService.get(variable.getKey());
            assertEquals(BeanUtils.describe(variable), BeanUtils.describe(testVariable));
        } finally {
            variableMaintainService.deleteIfExists(variable.getKey());
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
            variableMaintainService.insertOrUpdate(variable);

            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
            assertFalse(variableMaintainService.exists(variable.getKey()));
        } finally {
            variableMaintainService.deleteIfExists(variable.getKey());
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
            variableMaintainService.insertOrUpdate(variable);

            topicMaintainService.deleteIfExists(topic.getKey());
            assertFalse(variableMaintainService.exists(variable.getKey()));
        } finally {
            variableMaintainService.deleteIfExists(variable.getKey());
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
            variableMaintainService.insertOrUpdate(variable);

            userMaintainService.deleteIfExists(user.getKey());
            assertFalse(variableMaintainService.exists(variable.getKey()));
        } finally {
            variableMaintainService.deleteIfExists(variable.getKey());
            userMaintainService.deleteIfExists(user.getKey());
            topicMaintainService.deleteIfExists(topic.getKey());
            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
        }
    }
}

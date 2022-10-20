package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.PreferenceIndicator;
import com.dwarfeng.notify.stack.bean.entity.Topic;
import com.dwarfeng.notify.stack.bean.entity.key.PreferenceIndicatorKey;
import com.dwarfeng.notify.stack.service.PreferenceIndicatorMaintainService;
import com.dwarfeng.notify.stack.service.TopicMaintainService;
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
public class PreferenceIndicatorMaintainServiceImplTest {

    private static final String TOPIC_ID = "test.topic";
    private static final String PREFERENCE_ID = "test.preference";

    @Autowired
    private TopicMaintainService topicMaintainService;
    @Autowired
    private PreferenceIndicatorMaintainService preferenceIndicatorMaintainService;

    private Topic topic;
    private PreferenceIndicator preferenceIndicator;

    @Before
    public void setUp() {
        topic = new Topic(new StringIdKey(TOPIC_ID), "label", "remark", true, 12450);
        preferenceIndicator = new PreferenceIndicator(
                new PreferenceIndicatorKey(TOPIC_ID, PREFERENCE_ID), "value", "remark", "defaultValue"
        );
    }

    @After
    public void tearDown() {
        topic = null;
        preferenceIndicator = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            topicMaintainService.insertOrUpdate(topic);

            preferenceIndicatorMaintainService.insertOrUpdate(preferenceIndicator);
            preferenceIndicatorMaintainService.update(preferenceIndicator);
            PreferenceIndicator testPreferenceIndicator = preferenceIndicatorMaintainService.get(
                    preferenceIndicator.getKey()
            );
            assertEquals(BeanUtils.describe(preferenceIndicator), BeanUtils.describe(testPreferenceIndicator));
        } finally {
            preferenceIndicatorMaintainService.deleteIfExists(preferenceIndicator.getKey());
            topicMaintainService.deleteIfExists(topic.getKey());
        }
    }

    @Test
    public void testForTopicCascade() throws Exception {
        try {
            topicMaintainService.insertOrUpdate(topic);
            preferenceIndicatorMaintainService.insertOrUpdate(preferenceIndicator);

            topicMaintainService.deleteIfExists(topic.getKey());
            assertFalse(preferenceIndicatorMaintainService.exists(preferenceIndicator.getKey()));
        } finally {
            preferenceIndicatorMaintainService.deleteIfExists(preferenceIndicator.getKey());
            topicMaintainService.deleteIfExists(topic.getKey());
        }
    }
}

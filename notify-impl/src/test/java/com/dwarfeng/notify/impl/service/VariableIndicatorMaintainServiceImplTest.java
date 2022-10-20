package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.Topic;
import com.dwarfeng.notify.stack.bean.entity.VariableIndicator;
import com.dwarfeng.notify.stack.bean.entity.key.VariableIndicatorKey;
import com.dwarfeng.notify.stack.service.TopicMaintainService;
import com.dwarfeng.notify.stack.service.VariableIndicatorMaintainService;
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
public class VariableIndicatorMaintainServiceImplTest {

    private static final String TOPIC_ID = "test.topic";
    private static final String VARIABLE_ID = "test.variable";

    @Autowired
    private TopicMaintainService topicMaintainService;
    @Autowired
    private VariableIndicatorMaintainService variableIndicatorMaintainService;

    private Topic topic;
    private VariableIndicator variableIndicator;

    @Before
    public void setUp() {
        topic = new Topic(new StringIdKey(TOPIC_ID), "label", "remark", true, 12450);
        variableIndicator = new VariableIndicator(
                new VariableIndicatorKey(TOPIC_ID, VARIABLE_ID), "value", "remark", "defaultValue"
        );
    }

    @After
    public void tearDown() {
        topic = null;
        variableIndicator = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            topicMaintainService.insertOrUpdate(topic);

            variableIndicatorMaintainService.insertOrUpdate(variableIndicator);
            variableIndicatorMaintainService.update(variableIndicator);
            VariableIndicator testVariableIndicator = variableIndicatorMaintainService.get(
                    variableIndicator.getKey()
            );
            assertEquals(BeanUtils.describe(variableIndicator), BeanUtils.describe(testVariableIndicator));
        } finally {
            variableIndicatorMaintainService.deleteIfExists(variableIndicator.getKey());
            topicMaintainService.deleteIfExists(topic.getKey());
        }
    }

    @Test
    public void testForTopicCascade() throws Exception {
        try {
            topicMaintainService.insertOrUpdate(topic);
            variableIndicatorMaintainService.insertOrUpdate(variableIndicator);

            topicMaintainService.deleteIfExists(topic.getKey());
            assertFalse(variableIndicatorMaintainService.exists(variableIndicator.getKey()));
        } finally {
            variableIndicatorMaintainService.deleteIfExists(variableIndicator.getKey());
            topicMaintainService.deleteIfExists(topic.getKey());
        }
    }
}

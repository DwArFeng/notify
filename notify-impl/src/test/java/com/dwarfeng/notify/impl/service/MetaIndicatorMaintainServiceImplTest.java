package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.MetaIndicator;
import com.dwarfeng.notify.stack.bean.entity.Topic;
import com.dwarfeng.notify.stack.bean.key.MetaIndicatorKey;
import com.dwarfeng.notify.stack.service.MetaIndicatorMaintainService;
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

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class MetaIndicatorMaintainServiceImplTest {

    private static final String TOPIC_ID = "test.topic";
    private static final String META_ID = "test.meta";

    @Autowired
    private TopicMaintainService topicMaintainService;
    @Autowired
    private MetaIndicatorMaintainService metaIndicatorMaintainService;

    private Topic topic;
    private MetaIndicator metaIndicator;

    @Before
    public void setUp() {
        topic = new Topic(new StringIdKey(TOPIC_ID), "label", "remark", true, 12450);
        metaIndicator = new MetaIndicator(
                new MetaIndicatorKey(TOPIC_ID, META_ID), "value", "remark", "defaultValue"
        );
    }

    @After
    public void tearDown() {
        topic = null;
        metaIndicator = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            topicMaintainService.insertOrUpdate(topic);

            metaIndicatorMaintainService.insertOrUpdate(metaIndicator);
            metaIndicatorMaintainService.update(metaIndicator);
            MetaIndicator testMetaIndicator = metaIndicatorMaintainService.get(
                    metaIndicator.getKey()
            );
            assertEquals(BeanUtils.describe(metaIndicator), BeanUtils.describe(testMetaIndicator));
        } finally {
            if (Objects.nonNull(metaIndicator.getKey())) {
                metaIndicatorMaintainService.deleteIfExists(metaIndicator.getKey());
            }
            if (Objects.nonNull(topic.getKey())) {
                topicMaintainService.deleteIfExists(topic.getKey());
            }
        }
    }

    @Test
    public void testForTopicCascade() throws Exception {
        try {
            topicMaintainService.insertOrUpdate(topic);
            metaIndicatorMaintainService.insertOrUpdate(metaIndicator);

            topicMaintainService.deleteIfExists(topic.getKey());
            assertFalse(metaIndicatorMaintainService.exists(metaIndicator.getKey()));
        } finally {
            if (Objects.nonNull(metaIndicator.getKey())) {
                metaIndicatorMaintainService.deleteIfExists(metaIndicator.getKey());
            }
            if (Objects.nonNull(topic.getKey())) {
                topicMaintainService.deleteIfExists(topic.getKey());
            }
        }
    }
}

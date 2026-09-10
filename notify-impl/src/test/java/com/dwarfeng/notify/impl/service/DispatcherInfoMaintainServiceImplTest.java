package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.DispatcherInfo;
import com.dwarfeng.notify.stack.bean.entity.Topic;
import com.dwarfeng.notify.stack.service.DispatcherInfoMaintainService;
import com.dwarfeng.notify.stack.service.TopicMaintainService;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:com/dwarfeng/notify/impl/spring/application-context*.xml")
public class DispatcherInfoMaintainServiceImplTest {

    @Autowired
    private TopicMaintainService topicMaintainService;
    @Autowired
    private DispatcherInfoMaintainService dispatcherInfoMaintainService;

    private Topic topic;
    private DispatcherInfo dispatcherInfo;

    @BeforeEach
    public void setUp() {
        topic = new Topic(new StringIdKey("test.topic"), "label", "remark", true, 12450);
        dispatcherInfo = new DispatcherInfo(null, "label", "type", "param", "remark");
    }

    @AfterEach
    public void tearDown() {
        topic = null;
        dispatcherInfo = null;
    }

    @Test
    public void testForCurd() throws Exception {
        try {
            topic.setKey(topicMaintainService.insert(topic));
            dispatcherInfo.setKey(topic.getKey());
            dispatcherInfoMaintainService.insertOrUpdate(dispatcherInfo);
            dispatcherInfoMaintainService.update(dispatcherInfo);
            DispatcherInfo testDispatcherInfo = dispatcherInfoMaintainService.get(dispatcherInfo.getKey());
            assertEquals(BeanUtils.describe(dispatcherInfo), BeanUtils.describe(testDispatcherInfo));
        } finally {
            if (Objects.nonNull(dispatcherInfo.getKey())) {
                dispatcherInfoMaintainService.deleteIfExists(dispatcherInfo.getKey());
            }
            if (Objects.nonNull(topic.getKey())) {
                topicMaintainService.deleteIfExists(topic.getKey());
            }
        }
    }

    @Test
    public void testForTopicCascade() throws Exception {
        try {
            topic.setKey(topicMaintainService.insert(topic));
            dispatcherInfo.setKey(topic.getKey());
            dispatcherInfoMaintainService.insertOrUpdate(dispatcherInfo);

            assertTrue(dispatcherInfoMaintainService.exists(dispatcherInfo.getKey()));

            topicMaintainService.deleteIfExists(topic.getKey());

            assertFalse(dispatcherInfoMaintainService.exists(dispatcherInfo.getKey()));
        } finally {
            if (Objects.nonNull(dispatcherInfo.getKey())) {
                dispatcherInfoMaintainService.deleteIfExists(dispatcherInfo.getKey());
            }
            if (Objects.nonNull(topic.getKey())) {
                topicMaintainService.deleteIfExists(topic.getKey());
            }
        }
    }
}

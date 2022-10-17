package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.Topic;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class TopicMaintainServiceImplTest {

    @Autowired
    private TopicMaintainService service;

    private final List<Topic> topics = new ArrayList<>();

    @Before
    public void setUp() {
        for (int i = 0; i < 5; i++) {
            Topic topic = new Topic(new StringIdKey("test.topic-" + (i + 1)), "label", "remark", true, 12450);
            topics.add(topic);
        }
    }

    @After
    public void tearDown() {
        topics.clear();
    }

    @Test
    public void test() throws Exception {
        try {
            for (Topic topic : topics) {
                topic.setKey(service.insert(topic));
                service.update(topic);
                Topic testTopic = service.get(topic.getKey());
                assertEquals(BeanUtils.describe(topic), BeanUtils.describe(testTopic));
            }
        } finally {
            for (Topic topic : topics) {
                service.delete(topic.getKey());
            }
        }
    }
}

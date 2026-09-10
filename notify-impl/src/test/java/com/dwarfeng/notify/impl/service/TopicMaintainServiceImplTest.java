package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.Topic;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:com/dwarfeng/notify/impl/spring/application-context*.xml")
public class TopicMaintainServiceImplTest {

    @Autowired
    private TopicMaintainService service;

    private final List<Topic> topics = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        for (int i = 0; i < 5; i++) {
            Topic topic = new Topic(new StringIdKey("test.topic-" + (i + 1)), "label", "remark", true, 12450);
            topics.add(topic);
        }
    }

    @AfterEach
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
                if (Objects.isNull(topic.getKey())) {
                    continue;
                }
                service.delete(topic.getKey());
            }
        }
    }
}

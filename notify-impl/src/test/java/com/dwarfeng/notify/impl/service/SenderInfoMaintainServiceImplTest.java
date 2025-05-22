package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.NotifySetting;
import com.dwarfeng.notify.stack.bean.entity.SenderInfo;
import com.dwarfeng.notify.stack.bean.entity.Topic;
import com.dwarfeng.notify.stack.bean.key.SenderInfoKey;
import com.dwarfeng.notify.stack.service.NotifySettingMaintainService;
import com.dwarfeng.notify.stack.service.SenderInfoMaintainService;
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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class SenderInfoMaintainServiceImplTest {

    @Autowired
    private NotifySettingMaintainService notifySettingMaintainService;
    @Autowired
    private TopicMaintainService topicMaintainService;
    @Autowired
    private SenderInfoMaintainService senderInfoMaintainService;

    private NotifySetting notifySetting;
    private Topic topic;
    private SenderInfo senderInfo;

    @Before
    public void setUp() {
        notifySetting = new NotifySetting(null, "label", "remark", true);
        topic = new Topic(new StringIdKey("test.topic"), "label", "remark", true, 12450);
        senderInfo = new SenderInfo(null, "label", "type", "param", "remark");
    }

    @After
    public void tearDown() {
        notifySetting = null;
        topic = null;
        senderInfo = null;
    }

    @Test
    public void testForCurd() throws Exception {
        try {
            notifySetting.setKey(notifySettingMaintainService.insert(notifySetting));
            topic.setKey(topicMaintainService.insert(topic));
            senderInfo.setKey(new SenderInfoKey(notifySetting.getKey().getLongId(), topic.getKey().getStringId()));
            senderInfoMaintainService.insertOrUpdate(senderInfo);
            senderInfoMaintainService.update(senderInfo);
            SenderInfo testSenderInfo = senderInfoMaintainService.get(senderInfo.getKey());
            assertEquals(BeanUtils.describe(senderInfo), BeanUtils.describe(testSenderInfo));
        } finally {
            if (Objects.nonNull(senderInfo.getKey())) {
                senderInfoMaintainService.deleteIfExists(senderInfo.getKey());
            }
            if (Objects.nonNull(notifySetting.getKey())) {
                notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
            }
            if (Objects.nonNull(topic.getKey())) {
                topicMaintainService.deleteIfExists(topic.getKey());
            }
        }
    }

    @Test
    public void testForNotifySettingCascade() throws Exception {
        try {
            notifySetting.setKey(notifySettingMaintainService.insert(notifySetting));
            topic.setKey(topicMaintainService.insert(topic));
            senderInfo.setKey(new SenderInfoKey(notifySetting.getKey().getLongId(), topic.getKey().getStringId()));
            senderInfoMaintainService.insertOrUpdate(senderInfo);

            assertTrue(senderInfoMaintainService.exists(senderInfo.getKey()));

            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());

            assertFalse(senderInfoMaintainService.exists(senderInfo.getKey()));
        } finally {
            if (Objects.nonNull(senderInfo.getKey())) {
                senderInfoMaintainService.deleteIfExists(senderInfo.getKey());
            }
            if (Objects.nonNull(notifySetting.getKey())) {
                notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
            }
            if (Objects.nonNull(topic.getKey())) {
                topicMaintainService.deleteIfExists(topic.getKey());
            }
        }
    }

    @Test
    public void testForTopicCascade() throws Exception {
        try {
            notifySetting.setKey(notifySettingMaintainService.insert(notifySetting));
            topic.setKey(topicMaintainService.insert(topic));
            senderInfo.setKey(new SenderInfoKey(notifySetting.getKey().getLongId(), topic.getKey().getStringId()));
            senderInfoMaintainService.insertOrUpdate(senderInfo);

            assertTrue(senderInfoMaintainService.exists(senderInfo.getKey()));

            topicMaintainService.deleteIfExists(topic.getKey());

            assertFalse(senderInfoMaintainService.exists(senderInfo.getKey()));
        } finally {
            if (Objects.nonNull(senderInfo.getKey())) {
                senderInfoMaintainService.deleteIfExists(senderInfo.getKey());
            }
            if (Objects.nonNull(notifySetting.getKey())) {
                notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
            }
            if (Objects.nonNull(topic.getKey())) {
                topicMaintainService.deleteIfExists(topic.getKey());
            }
        }
    }
}

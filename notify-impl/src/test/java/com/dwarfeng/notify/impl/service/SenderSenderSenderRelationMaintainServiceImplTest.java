package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.NotifySetting;
import com.dwarfeng.notify.stack.bean.entity.SenderInfo;
import com.dwarfeng.notify.stack.bean.entity.SenderRelation;
import com.dwarfeng.notify.stack.bean.entity.Topic;
import com.dwarfeng.notify.stack.bean.entity.key.SenderRelationKey;
import com.dwarfeng.notify.stack.service.NotifySettingMaintainService;
import com.dwarfeng.notify.stack.service.SenderInfoMaintainService;
import com.dwarfeng.notify.stack.service.SenderRelationMaintainService;
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
public class SenderSenderSenderRelationMaintainServiceImplTest {

    @Autowired
    private NotifySettingMaintainService notifySettingMaintainService;
    @Autowired
    private TopicMaintainService topicMaintainService;
    @Autowired
    private SenderInfoMaintainService senderInfoMaintainService;
    @Autowired
    private SenderRelationMaintainService senderRelationMaintainService;

    private NotifySetting notifySetting;
    private Topic topic;
    private SenderInfo senderInfo;
    private SenderRelation senderRelation;

    @Before
    public void setUp() {
        notifySetting = new NotifySetting(null, "label", "remark", true);
        topic = new Topic(new StringIdKey("test.topic"), "label", "remark", true, 12450);
        senderInfo = new SenderInfo(null, "label", "type", "param", "remark");
        senderRelation = new SenderRelation(null, null, "remark");
    }

    @After
    public void tearDown() {
        notifySetting = null;
        topic = null;
        senderInfo = null;
        senderRelation = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            notifySetting.setKey(notifySettingMaintainService.insert(notifySetting));
            topicMaintainService.insertOrUpdate(topic);
            senderInfo.setKey(senderInfoMaintainService.insert(senderInfo));
            senderRelation.setKey(new SenderRelationKey(notifySetting.getKey().getLongId(), topic.getKey().getStringId()));
            senderRelation.setSenderInfoKey(senderInfo.getKey());

            senderRelationMaintainService.insertOrUpdate(senderRelation);
            senderRelationMaintainService.update(senderRelation);
            SenderRelation testSenderRelation = senderRelationMaintainService.get(senderRelation.getKey());
            assertEquals(BeanUtils.describe(senderRelation), BeanUtils.describe(testSenderRelation));
        } finally {
            senderRelationMaintainService.deleteIfExists(senderRelation.getKey());
            senderInfoMaintainService.deleteIfExists(senderInfo.getKey());
            topicMaintainService.deleteIfExists(topic.getKey());
            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
        }
    }

    @Test
    public void testForNotifySettingCascade() throws Exception {
        try {
            notifySetting.setKey(notifySettingMaintainService.insert(notifySetting));
            topicMaintainService.insertOrUpdate(topic);
            senderInfo.setKey(senderInfoMaintainService.insert(senderInfo));
            senderRelation.setKey(new SenderRelationKey(notifySetting.getKey().getLongId(), topic.getKey().getStringId()));
            senderRelation.setSenderInfoKey(senderInfo.getKey());
            senderRelationMaintainService.insertOrUpdate(senderRelation);

            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
            assertFalse(senderRelationMaintainService.exists(senderRelation.getKey()));
        } finally {
            senderRelationMaintainService.deleteIfExists(senderRelation.getKey());
            senderInfoMaintainService.deleteIfExists(senderInfo.getKey());
            topicMaintainService.deleteIfExists(topic.getKey());
            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
        }
    }

    @Test
    public void testForTopicCascade() throws Exception {
        try {
            notifySetting.setKey(notifySettingMaintainService.insert(notifySetting));
            topicMaintainService.insertOrUpdate(topic);
            senderInfo.setKey(senderInfoMaintainService.insert(senderInfo));
            senderRelation.setKey(new SenderRelationKey(notifySetting.getKey().getLongId(), topic.getKey().getStringId()));
            senderRelation.setSenderInfoKey(senderInfo.getKey());
            senderRelationMaintainService.insertOrUpdate(senderRelation);

            topicMaintainService.deleteIfExists(topic.getKey());
            assertFalse(senderRelationMaintainService.exists(senderRelation.getKey()));
        } finally {
            senderRelationMaintainService.deleteIfExists(senderRelation.getKey());
            senderInfoMaintainService.deleteIfExists(senderInfo.getKey());
            topicMaintainService.deleteIfExists(topic.getKey());
            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
        }
    }

    @Test
    public void testForSenderInfoCascade() throws Exception {
        try {
            notifySetting.setKey(notifySettingMaintainService.insert(notifySetting));
            topicMaintainService.insertOrUpdate(topic);
            senderInfo.setKey(senderInfoMaintainService.insert(senderInfo));
            senderRelation.setKey(new SenderRelationKey(notifySetting.getKey().getLongId(), topic.getKey().getStringId()));
            senderRelation.setSenderInfoKey(senderInfo.getKey());
            senderRelationMaintainService.insertOrUpdate(senderRelation);

            senderInfoMaintainService.deleteIfExists(senderInfo.getKey());
            assertFalse(senderRelationMaintainService.exists(senderRelation.getKey()));
        } finally {
            senderRelationMaintainService.deleteIfExists(senderRelation.getKey());
            senderInfoMaintainService.deleteIfExists(senderInfo.getKey());
            topicMaintainService.deleteIfExists(topic.getKey());
            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
        }
    }
}

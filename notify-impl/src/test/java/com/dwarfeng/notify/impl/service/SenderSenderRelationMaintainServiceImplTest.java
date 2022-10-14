package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.NotifySetting;
import com.dwarfeng.notify.stack.bean.entity.Relation;
import com.dwarfeng.notify.stack.bean.entity.SenderInfo;
import com.dwarfeng.notify.stack.bean.entity.Topic;
import com.dwarfeng.notify.stack.bean.entity.key.RelationKey;
import com.dwarfeng.notify.stack.service.NotifySettingMaintainService;
import com.dwarfeng.notify.stack.service.RelationMaintainService;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class SenderSenderRelationMaintainServiceImplTest {

    @Autowired
    private NotifySettingMaintainService notifySettingMaintainService;
    @Autowired
    private TopicMaintainService topicMaintainService;
    @Autowired
    private SenderInfoMaintainService senderInfoMaintainService;
    @Autowired
    private RelationMaintainService relationMaintainService;

    private NotifySetting notifySetting;
    private Topic topic;
    private SenderInfo senderInfo;
    private Relation relation;

    @Before
    public void setUp() {
        notifySetting = new NotifySetting(null, "label", "remark", true);
        topic = new Topic(new StringIdKey("test.topic"), "label", "remark", true, 12450);
        senderInfo = new SenderInfo(null, "label", "type", "param", "remark");
        relation = new Relation(null, null, "remark");
    }

    @After
    public void tearDown() {
        notifySetting = null;
        topic = null;
        senderInfo = null;
        relation = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            notifySetting.setKey(notifySettingMaintainService.insert(notifySetting));
            topicMaintainService.insertOrUpdate(topic);
            senderInfo.setKey(senderInfoMaintainService.insert(senderInfo));
            relation.setKey(new RelationKey(notifySetting.getKey().getLongId(), topic.getKey().getStringId()));
            relation.setSenderInfoKey(senderInfo.getKey());

            relationMaintainService.insertOrUpdate(relation);
            relationMaintainService.update(relation);
            Relation testRelation = relationMaintainService.get(relation.getKey());
            assertEquals(BeanUtils.describe(relation), BeanUtils.describe(testRelation));
        } finally {
            relationMaintainService.deleteIfExists(relation.getKey());
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
            relation.setKey(new RelationKey(notifySetting.getKey().getLongId(), topic.getKey().getStringId()));
            relation.setSenderInfoKey(senderInfo.getKey());
            relationMaintainService.insertOrUpdate(relation);

            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
            assertFalse(relationMaintainService.exists(relation.getKey()));
        } finally {
            relationMaintainService.deleteIfExists(relation.getKey());
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
            relation.setKey(new RelationKey(notifySetting.getKey().getLongId(), topic.getKey().getStringId()));
            relation.setSenderInfoKey(senderInfo.getKey());
            relationMaintainService.insertOrUpdate(relation);

            topicMaintainService.deleteIfExists(topic.getKey());
            assertFalse(relationMaintainService.exists(relation.getKey()));
        } finally {
            relationMaintainService.deleteIfExists(relation.getKey());
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
            relation.setKey(new RelationKey(notifySetting.getKey().getLongId(), topic.getKey().getStringId()));
            relation.setSenderInfoKey(senderInfo.getKey());
            relationMaintainService.insertOrUpdate(relation);

            senderInfoMaintainService.deleteIfExists(senderInfo.getKey());
            assertFalse(relationMaintainService.exists(relation.getKey()));
        } finally {
            relationMaintainService.deleteIfExists(relation.getKey());
            senderInfoMaintainService.deleteIfExists(senderInfo.getKey());
            topicMaintainService.deleteIfExists(topic.getKey());
            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
        }
    }
}

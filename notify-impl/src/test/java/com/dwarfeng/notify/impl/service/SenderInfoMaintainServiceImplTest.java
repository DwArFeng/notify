package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.NotifySetting;
import com.dwarfeng.notify.stack.bean.entity.SenderInfo;
import com.dwarfeng.notify.stack.service.SenderInfoMaintainService;
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
public class SenderInfoMaintainServiceImplTest {

    @Autowired
    private SenderInfoMaintainService senderInfoMaintainService;

    private NotifySetting notifySetting;
    private List<SenderInfo> senderInfos;

    @Before
    public void setUp() {
        notifySetting = new NotifySetting(null, "label", "remark");
        senderInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SenderInfo senderInfo = new SenderInfo(null, "type", "param", "remark");
            senderInfos.add(senderInfo);
        }
    }

    @After
    public void tearDown() {
        notifySetting = null;
        senderInfos.clear();
    }

    @Test
    public void testForCurd() throws Exception {
        try {
            for (SenderInfo senderInfo : senderInfos) {
                senderInfo.setKey(senderInfoMaintainService.insertOrUpdate(senderInfo));
                senderInfoMaintainService.update(senderInfo);
                SenderInfo testSenderInfo = senderInfoMaintainService.get(senderInfo.getKey());
                assertEquals(BeanUtils.describe(senderInfo), BeanUtils.describe(testSenderInfo));
            }
        } finally {
            for (SenderInfo senderInfo : senderInfos) {
                senderInfoMaintainService.deleteIfExists(senderInfo.getKey());
            }
        }
    }
}

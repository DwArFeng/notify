package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.NotifySetting;
import com.dwarfeng.notify.stack.service.NotifySettingMaintainService;
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
import java.util.Objects;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class NotifySettingMaintainServiceImplTest {

    @Autowired
    private NotifySettingMaintainService notifySettingMaintainService;

    private List<NotifySetting> notifySettings;

    @Before
    public void setUp() {
        notifySettings = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            NotifySetting notifySetting = new NotifySetting(null, "label", "remark", true);
            notifySettings.add(notifySetting);
        }
    }

    @After
    public void tearDown() {
        notifySettings.clear();
    }

    @Test
    public void test() throws Exception {
        try {
            for (NotifySetting notifySetting : notifySettings) {
                notifySetting.setKey(notifySettingMaintainService.insertOrUpdate(notifySetting));
                notifySettingMaintainService.update(notifySetting);
                NotifySetting testNotifySetting = notifySettingMaintainService.get(notifySetting.getKey());
                assertEquals(BeanUtils.describe(notifySetting), BeanUtils.describe(testNotifySetting));
            }
        } finally {
            for (NotifySetting notifySetting : notifySettings) {
                if (Objects.isNull(notifySetting.getKey())) {
                    continue;
                }
                notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
            }
        }
    }
}

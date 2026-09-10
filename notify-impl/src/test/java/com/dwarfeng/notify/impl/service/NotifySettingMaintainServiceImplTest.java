package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.NotifySetting;
import com.dwarfeng.notify.stack.service.NotifySettingMaintainService;
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
public class NotifySettingMaintainServiceImplTest {

    @Autowired
    private NotifySettingMaintainService notifySettingMaintainService;

    private List<NotifySetting> notifySettings;

    @BeforeEach
    public void setUp() {
        notifySettings = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            NotifySetting notifySetting = new NotifySetting(null, "label", "remark", true);
            notifySettings.add(notifySetting);
        }
    }

    @AfterEach
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

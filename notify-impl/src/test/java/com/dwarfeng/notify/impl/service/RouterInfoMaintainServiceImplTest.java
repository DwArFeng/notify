package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.NotifySetting;
import com.dwarfeng.notify.stack.bean.entity.RouterInfo;
import com.dwarfeng.notify.stack.service.NotifySettingMaintainService;
import com.dwarfeng.notify.stack.service.RouterInfoMaintainService;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class RouterInfoMaintainServiceImplTest {

    @Autowired
    private NotifySettingMaintainService notifySettingMaintainService;
    @Autowired
    private RouterInfoMaintainService routerInfoMaintainService;

    private NotifySetting notifySetting;
    private RouterInfo routerInfo;

    @Before
    public void setUp() {
        notifySetting = new NotifySetting(null, "label", "remark", true);
        routerInfo = new RouterInfo(null, "label", "type", "param", "remark");
    }

    @After
    public void tearDown() {
        notifySetting = null;
        routerInfo = null;
    }

    @Test
    public void testForCurd() throws Exception {
        try {
            notifySetting.setKey(notifySettingMaintainService.insert(notifySetting));
            routerInfo.setKey(notifySetting.getKey());
            routerInfoMaintainService.insertOrUpdate(routerInfo);
            routerInfoMaintainService.update(routerInfo);
            RouterInfo testRouterInfo = routerInfoMaintainService.get(routerInfo.getKey());
            assertEquals(BeanUtils.describe(routerInfo), BeanUtils.describe(testRouterInfo));
        } finally {
            routerInfoMaintainService.deleteIfExists(routerInfo.getKey());
            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
        }
    }

    @Test
    public void testForNotifySettingCascade() throws Exception {
        try {
            notifySetting.setKey(notifySettingMaintainService.insert(notifySetting));
            routerInfo.setKey(notifySetting.getKey());
            routerInfoMaintainService.insertOrUpdate(routerInfo);

            assertTrue(routerInfoMaintainService.exists(routerInfo.getKey()));

            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());

            assertFalse(routerInfoMaintainService.exists(routerInfo.getKey()));
        } finally {
            routerInfoMaintainService.deleteIfExists(routerInfo.getKey());
            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
        }
    }
}

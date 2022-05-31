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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class RouterInfoMaintainServiceImplTest {

    @Autowired
    private NotifySettingMaintainService notifySettingMaintainService;
    @Autowired
    private RouterInfoMaintainService routerInfoMaintainService;

    private NotifySetting notifySetting;
    private List<RouterInfo> routerInfos;

    @Before
    public void setUp() {
        notifySetting = new NotifySetting(null, "label", "remark");
        routerInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            RouterInfo routerInfo = new RouterInfo(null, null, "label", "type", "param", "remark");
            routerInfos.add(routerInfo);
        }
    }

    @After
    public void tearDown() {
        notifySetting = null;
        routerInfos.clear();
    }

    @Test
    public void testForCurd() throws Exception {
        try {
            notifySetting.setKey(notifySettingMaintainService.insert(notifySetting));
            for (RouterInfo routerInfo : routerInfos) {
                routerInfo.setNotifySettingKey(notifySetting.getKey());
                routerInfo.setKey(routerInfoMaintainService.insertOrUpdate(routerInfo));
                routerInfoMaintainService.update(routerInfo);
                RouterInfo testRouterInfo = routerInfoMaintainService.get(routerInfo.getKey());
                assertEquals(BeanUtils.describe(routerInfo), BeanUtils.describe(testRouterInfo));
            }
        } finally {
            for (RouterInfo routerInfo : routerInfos) {
                routerInfoMaintainService.deleteIfExists(routerInfo.getKey());
            }
            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
        }
    }

    @Test
    public void testForNotifySettingCascade() throws Exception {
        try {
            notifySetting.setKey(notifySettingMaintainService.insert(notifySetting));
            for (RouterInfo routerInfo : routerInfos) {
                routerInfo.setNotifySettingKey(notifySetting.getKey());
                routerInfo.setKey(routerInfoMaintainService.insertOrUpdate(routerInfo));
            }

            assertEquals(routerInfos.size(), routerInfoMaintainService.lookup(
                    RouterInfoMaintainService.CHILD_FOR_NOTIFY_SETTING,
                    new Object[]{notifySetting.getKey()}).getCount()
            );

            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());

            assertEquals(0, routerInfoMaintainService.lookup(
                    RouterInfoMaintainService.CHILD_FOR_NOTIFY_SETTING,
                    new Object[]{notifySetting.getKey()}).getCount()
            );
            assertTrue(routerInfoMaintainService.nonExists(
                    routerInfos.stream().map(RouterInfo::getKey).collect(Collectors.toList())
            ));
        } finally {
            for (RouterInfo routerInfo : routerInfos) {
                routerInfoMaintainService.deleteIfExists(routerInfo.getKey());
            }
            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
        }
    }
}

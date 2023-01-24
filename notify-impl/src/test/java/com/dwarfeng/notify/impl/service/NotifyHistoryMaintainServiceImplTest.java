package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.NotifyHistory;
import com.dwarfeng.notify.stack.bean.entity.NotifySetting;
import com.dwarfeng.notify.stack.service.NotifyHistoryMaintainService;
import com.dwarfeng.notify.stack.service.NotifySettingMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class NotifyHistoryMaintainServiceImplTest {

    private static final long NOTIFY_SETTING_ID = 12450L;

    @Autowired
    private NotifySettingMaintainService notifySettingMaintainService;
    @Autowired
    private NotifyHistoryMaintainService notifyHistoryMaintainService;

    private NotifySetting notifySetting;
    private final List<NotifyHistory> notifyHistories = new ArrayList<>();

    @Before
    public void setUp() {
        notifySetting = new NotifySetting(new LongIdKey(NOTIFY_SETTING_ID), "label", "remark", true);
        for (int i = 0; i < 5; i++) {
            NotifyHistory notifyHistory = new NotifyHistory(
                    null, new LongIdKey(NOTIFY_SETTING_ID), new Date(), "remark"
            );
            notifyHistories.add(notifyHistory);
        }
    }

    @After
    public void tearDown() {
        notifySetting = null;
        notifyHistories.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            notifySettingMaintainService.insertOrUpdate(notifySetting);

            for (NotifyHistory notifyHistory : notifyHistories) {
                notifyHistory.setKey(notifyHistoryMaintainService.insertOrUpdate(notifyHistory));
                notifyHistoryMaintainService.update(notifyHistory);
                NotifyHistory testNotifyHistory = notifyHistoryMaintainService.get(notifyHistory.getKey());
                assertEquals(BeanUtils.describe(notifyHistory), BeanUtils.describe(testNotifyHistory));
            }
        } finally {
            for (NotifyHistory notifyHistory : notifyHistories) {
                notifyHistoryMaintainService.deleteIfExists(notifyHistory.getKey());
            }
            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
        }
    }

    @Test
    public void testForNotifySettingCascade() throws Exception {
        try {
            notifySettingMaintainService.insertOrUpdate(notifySetting);
            for (NotifyHistory notifyHistory : notifyHistories) {
                notifyHistory.setKey(notifyHistoryMaintainService.insertOrUpdate(notifyHistory));
            }

            assertEquals(notifyHistories.size(), notifyHistoryMaintainService.lookup(
                    NotifyHistoryMaintainService.CHILD_FOR_NOTIFY_SETTING,
                    new Object[]{notifySetting.getKey()}).getCount()
            );

            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());

            assertEquals(0, notifyHistoryMaintainService.lookup(
                    NotifyHistoryMaintainService.CHILD_FOR_NOTIFY_SETTING,
                    new Object[]{notifySetting.getKey()}).getCount()
            );
            assertTrue(notifyHistoryMaintainService.allExists(
                    notifyHistories.stream().map(NotifyHistory::getKey).collect(Collectors.toList())
            ));
            for (NotifyHistory notifyHistory : notifyHistories) {
                NotifyHistory testNotifyHistory = notifyHistoryMaintainService.get(notifyHistory.getKey());
                assertNull(testNotifyHistory.getNotifySettingKey());
            }
        } finally {
            for (NotifyHistory notifyHistory : notifyHistories) {
                notifyHistoryMaintainService.deleteIfExists(notifyHistory.getKey());
            }
            notifySettingMaintainService.deleteIfExists(notifySetting.getKey());
        }
    }
}

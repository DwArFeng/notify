package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.NotifyHistory;
import com.dwarfeng.notify.stack.bean.entity.NotifyInfoRecord;
import com.dwarfeng.notify.stack.bean.key.NotifyInfoRecordKey;
import com.dwarfeng.notify.stack.service.NotifyHistoryMaintainService;
import com.dwarfeng.notify.stack.service.NotifyInfoRecordMaintainService;
import com.dwarfeng.subgrade.basic.stack.bean.key.LongIdKey;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:com/dwarfeng/notify/impl/spring/application-context*.xml")
public class NotifyInfoRecordMaintainServiceImplTest {

    private static final long NOTIFY_HISTORY_ID = 12450L;
    private static final String RECORD_ID_PREFIX = "test.record_id.";

    @Autowired
    private NotifyHistoryMaintainService notifyHistoryMaintainService;
    @Autowired
    private NotifyInfoRecordMaintainService notifyInfoRecordMaintainService;

    private NotifyHistory notifyHistory;
    private final List<NotifyInfoRecord> notifyInfoRecords = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        notifyHistory = new NotifyHistory(new LongIdKey(NOTIFY_HISTORY_ID), null, new Date(), "remark");
        for (int i = 0; i < 5; i++) {
            NotifyInfoRecord notifyInfoRecord = new NotifyInfoRecord(
                    new NotifyInfoRecordKey(NOTIFY_HISTORY_ID, 12450, RECORD_ID_PREFIX + i), "value"
            );
            notifyInfoRecords.add(notifyInfoRecord);
        }
    }

    @AfterEach
    public void tearDown() {
        notifyHistory = null;
        notifyInfoRecords.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            notifyHistoryMaintainService.insertOrUpdate(notifyHistory);

            for (NotifyInfoRecord notifyInfoRecord : notifyInfoRecords) {
                notifyInfoRecordMaintainService.insertOrUpdate(notifyInfoRecord);
                notifyInfoRecordMaintainService.update(notifyInfoRecord);
                NotifyInfoRecord testNotifyInfoRecord = notifyInfoRecordMaintainService.get(notifyInfoRecord.getKey());
                assertEquals(BeanUtils.describe(notifyInfoRecord), BeanUtils.describe(testNotifyInfoRecord));
            }
        } finally {
            for (NotifyInfoRecord notifyInfoRecord : notifyInfoRecords) {
                if (Objects.isNull(notifyInfoRecord.getKey())) {
                    continue;
                }
                notifyInfoRecordMaintainService.deleteIfExists(notifyInfoRecord.getKey());
            }
            if (Objects.nonNull(notifyHistory.getKey())) {
                notifyHistoryMaintainService.deleteIfExists(notifyHistory.getKey());
            }
        }
    }

    @Test
    public void testForNotifyHistoryCascade() throws Exception {
        try {
            notifyHistoryMaintainService.insertOrUpdate(notifyHistory);
            for (NotifyInfoRecord notifyInfoRecord : notifyInfoRecords) {
                notifyInfoRecordMaintainService.insertOrUpdate(notifyInfoRecord);
            }

            assertEquals(notifyInfoRecords.size(), notifyInfoRecordMaintainService.lookup(
                    NotifyInfoRecordMaintainService.CHILD_FOR_NOTIFY_HISTORY,
                    new Object[]{notifyHistory.getKey()}).getCount()
            );

            notifyHistoryMaintainService.deleteIfExists(notifyHistory.getKey());

            assertEquals(0, notifyInfoRecordMaintainService.lookup(
                    NotifyInfoRecordMaintainService.CHILD_FOR_NOTIFY_HISTORY,
                    new Object[]{notifyHistory.getKey()}).getCount()
            );
            assertTrue(notifyInfoRecordMaintainService.nonExists(
                    notifyInfoRecords.stream().map(NotifyInfoRecord::getKey).collect(Collectors.toList())
            ));
        } finally {
            for (NotifyInfoRecord notifyInfoRecord : notifyInfoRecords) {
                if (Objects.isNull(notifyInfoRecord.getKey())) {
                    continue;
                }
                notifyInfoRecordMaintainService.deleteIfExists(notifyInfoRecord.getKey());
            }
            if (Objects.nonNull(notifyHistory.getKey())) {
                notifyHistoryMaintainService.deleteIfExists(notifyHistory.getKey());
            }
        }
    }
}

package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.DispatcherInfo;
import com.dwarfeng.notify.stack.service.DispatcherInfoMaintainService;
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
public class DispatcherInfoMaintainServiceImplTest {

    @Autowired
    private DispatcherInfoMaintainService dispatcherInfoMaintainService;

    private List<DispatcherInfo> dispatcherInfos;

    @Before
    public void setUp() {
        dispatcherInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            DispatcherInfo dispatcherInfo = new DispatcherInfo(null, "label", "type", "param", "remark");
            dispatcherInfos.add(dispatcherInfo);
        }
    }

    @After
    public void tearDown() {
        dispatcherInfos.clear();
    }

    @Test
    public void testForCurd() throws Exception {
        try {
            for (DispatcherInfo dispatcherInfo : dispatcherInfos) {
                dispatcherInfo.setKey(dispatcherInfoMaintainService.insertOrUpdate(dispatcherInfo));
                dispatcherInfoMaintainService.update(dispatcherInfo);
                DispatcherInfo testDispatcherInfo = dispatcherInfoMaintainService.get(dispatcherInfo.getKey());
                assertEquals(BeanUtils.describe(dispatcherInfo), BeanUtils.describe(testDispatcherInfo));
            }
        } finally {
            for (DispatcherInfo dispatcherInfo : dispatcherInfos) {
                dispatcherInfoMaintainService.deleteIfExists(dispatcherInfo.getKey());
            }
        }
    }
}

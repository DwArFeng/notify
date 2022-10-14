package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.DispatcherSupport;
import com.dwarfeng.notify.stack.service.DispatcherSupportMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
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
public class DispatcherSupportMaintainServiceImplTest {

    @Autowired
    private DispatcherSupportMaintainService service;

    private final List<DispatcherSupport> dispatcherSupports = new ArrayList<>();

    @Before
    public void setUp() {
        for (int i = 0; i < 5; i++) {
            DispatcherSupport dispatcherSupport = new DispatcherSupport(
                    new StringIdKey("dispatcher-support-" + (i + 1)), "label", "description", "exampleParam"
            );
            dispatcherSupports.add(dispatcherSupport);
        }
    }

    @After
    public void tearDown() {
        dispatcherSupports.clear();
    }

    @Test
    public void test() throws Exception {
        try {
            for (DispatcherSupport dispatcherSupport : dispatcherSupports) {
                dispatcherSupport.setKey(service.insert(dispatcherSupport));
                service.update(dispatcherSupport);
                DispatcherSupport testDispatcherSupport = service.get(dispatcherSupport.getKey());
                assertEquals(BeanUtils.describe(dispatcherSupport), BeanUtils.describe(testDispatcherSupport));
            }
        } finally {
            for (DispatcherSupport dispatcherSupport : dispatcherSupports) {
                service.delete(dispatcherSupport.getKey());
            }
        }
    }
}

package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.RouterSupport;
import com.dwarfeng.notify.stack.service.RouterSupportMaintainService;
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
public class RouterSupportMaintainServiceImplTest {

    @Autowired
    private RouterSupportMaintainService service;

    private final List<RouterSupport> routerSupports = new ArrayList<>();

    @Before
    public void setUp() {
        for (int i = 0; i < 5; i++) {
            RouterSupport routerSupport = new RouterSupport(
                    new StringIdKey("router-support-" + (i + 1)), "label", "description", "exampleParam"
            );
            routerSupports.add(routerSupport);
        }
    }

    @After
    public void tearDown() {
        routerSupports.clear();
    }

    @Test
    public void test() throws Exception {
        try {
            for (RouterSupport routerSupport : routerSupports) {
                routerSupport.setKey(service.insert(routerSupport));
                service.update(routerSupport);
                RouterSupport testRouterSupport = service.get(routerSupport.getKey());
                assertEquals(BeanUtils.describe(routerSupport), BeanUtils.describe(testRouterSupport));
            }
        } finally {
            for (RouterSupport routerSupport : routerSupports) {
                service.delete(routerSupport.getKey());
            }
        }
    }
}

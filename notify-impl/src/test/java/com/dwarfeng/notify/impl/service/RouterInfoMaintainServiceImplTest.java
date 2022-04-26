package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.RouterInfo;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class RouterInfoMaintainServiceImplTest {

    @Autowired
    private RouterInfoMaintainService routerInfoMaintainService;

    private List<RouterInfo> routerInfos;

    @Before
    public void setUp() {
        routerInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            RouterInfo routerInfo = new RouterInfo(null, true, "type", "param", "remark");
            routerInfos.add(routerInfo);
        }
    }

    @After
    public void tearDown() {
        routerInfos.clear();
    }

    @Test
    public void test() throws Exception {
        try {
            for (RouterInfo routerInfo : routerInfos) {
                routerInfo.setKey(routerInfoMaintainService.insertOrUpdate(routerInfo));
                routerInfoMaintainService.update(routerInfo);
                RouterInfo testRouterInfo = routerInfoMaintainService.get(routerInfo.getKey());
                assertEquals(BeanUtils.describe(routerInfo), BeanUtils.describe(testRouterInfo));
            }
        } finally {
            for (RouterInfo routerInfo : routerInfos) {
                routerInfoMaintainService.deleteIfExists(routerInfo.getKey());
            }
        }
    }
}

package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.RouterSupport;
import com.dwarfeng.notify.stack.service.RouterSupportMaintainService;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
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
public class RouterSupportMaintainServiceImplTest {

    @Autowired
    private RouterSupportMaintainService service;

    private final List<RouterSupport> routerSupports = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        for (int i = 0; i < 5; i++) {
            RouterSupport routerSupport = new RouterSupport(
                    new StringIdKey("router-support-" + (i + 1)), "label", "description", "exampleParam"
            );
            routerSupports.add(routerSupport);
        }
    }

    @AfterEach
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
                if (Objects.isNull(routerSupport.getKey())) {
                    continue;
                }
                service.delete(routerSupport.getKey());
            }
        }
    }
}

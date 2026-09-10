package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.DispatcherSupport;
import com.dwarfeng.notify.stack.service.DispatcherSupportMaintainService;
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
public class DispatcherSupportMaintainServiceImplTest {

    @Autowired
    private DispatcherSupportMaintainService service;

    private final List<DispatcherSupport> dispatcherSupports = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        for (int i = 0; i < 5; i++) {
            DispatcherSupport dispatcherSupport = new DispatcherSupport(
                    new StringIdKey("dispatcher-support-" + (i + 1)), "label", "description", "exampleParam"
            );
            dispatcherSupports.add(dispatcherSupport);
        }
    }

    @AfterEach
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
                if (Objects.isNull(dispatcherSupport.getKey())) {
                    continue;
                }
                service.delete(dispatcherSupport.getKey());
            }
        }
    }
}

package com.dwarfeng.notify.impl.service;

import com.dwarfeng.notify.stack.bean.entity.SenderSupport;
import com.dwarfeng.notify.stack.service.SenderSupportMaintainService;
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
import java.util.Objects;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class SenderSupportMaintainServiceImplTest {

    @Autowired
    private SenderSupportMaintainService service;

    private final List<SenderSupport> senderSupports = new ArrayList<>();

    @Before
    public void setUp() {
        for (int i = 0; i < 5; i++) {
            SenderSupport senderSupport = new SenderSupport(
                    new StringIdKey("sender-support-" + (i + 1)), "label", "description", "exampleParam"
            );
            senderSupports.add(senderSupport);
        }
    }

    @After
    public void tearDown() {
        senderSupports.clear();
    }

    @Test
    public void test() throws Exception {
        try {
            for (SenderSupport senderSupport : senderSupports) {
                senderSupport.setKey(service.insert(senderSupport));
                service.update(senderSupport);
                SenderSupport testSenderSupport = service.get(senderSupport.getKey());
                assertEquals(BeanUtils.describe(senderSupport), BeanUtils.describe(testSenderSupport));
            }
        } finally {
            for (SenderSupport senderSupport : senderSupports) {
                if (Objects.isNull(senderSupport.getKey())) {
                    continue;
                }
                service.delete(senderSupport.getKey());
            }
        }
    }
}

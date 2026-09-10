package com.dwarfeng.notify.sdk.exception;

import com.dwarfeng.notify.base.sdk.i18n.MessageContext;
import com.dwarfeng.subgrade.basic.stack.exception.ServiceException;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

/**
 * 服务异常代码供应器测试。
 *
 * @author DwArFeng
 * @since 4.0.0
 */
public class ServiceExceptionCodeSuppliersTest {

    @Test
    public void shouldResolveLocaleAndCreateIndependentCodeSnapshots() {
        int originalOffset = ServiceExceptionCodeSuppliers.getExceptionCodeOffset();
        try {
            ServiceExceptionCodeSuppliers.setExceptionCodeOffset(5500);

            ServiceException.Code englishCode = MessageContext.call(
                    Locale.ENGLISH, ServiceExceptionCodeSuppliers.ROUTER_FAILED::get
            );
            ServiceException.Code chineseCode = MessageContext.call(
                    Locale.SIMPLIFIED_CHINESE, ServiceExceptionCodeSuppliers.ROUTER_FAILED::get
            );

            assertEquals(5500, englishCode.getCode());
            assertEquals("router failed", englishCode.getTip());
            assertEquals(5500, chineseCode.getCode());
            assertEquals("路由器失败", chineseCode.getTip());
            assertNotSame(englishCode, chineseCode);

            ServiceExceptionCodeSuppliers.setExceptionCodeOffset(6500);
            ServiceException.Code updatedCode = ServiceExceptionCodeSuppliers.ROUTER_FAILED.get();

            assertEquals(5500, englishCode.getCode());
            assertEquals(6500, updatedCode.getCode());
            assertNotSame(englishCode, updatedCode);
        } finally {
            ServiceExceptionCodeSuppliers.setExceptionCodeOffset(originalOffset);
        }
    }
}

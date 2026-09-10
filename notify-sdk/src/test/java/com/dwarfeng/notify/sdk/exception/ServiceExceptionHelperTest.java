package com.dwarfeng.notify.sdk.exception;

import com.dwarfeng.notify.stack.exception.RouterException;
import com.dwarfeng.subgrade.basic.impl.exception.MapServiceExceptionMapper;
import com.dwarfeng.subgrade.basic.stack.exception.ServiceException;
import org.junit.jupiter.api.Test;

import java.io.Serial;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 服务异常帮助类测试。
 *
 * @author DwArFeng
 * @since 4.0.0
 */
public class ServiceExceptionHelperTest {

    @Test
    public void shouldProvideSuppliersForDirectParentAndDefaultRoutes() {
        int originalOffset = ServiceExceptionCodeSuppliers.getExceptionCodeOffset();
        try {
            ServiceExceptionCodeSuppliers.setExceptionCodeOffset(5500);
            Map<Class<? extends Exception>, Supplier<ServiceException.Code>> destination =
                    ServiceExceptionHelper.putDefaultDestination(null);
            AtomicInteger defaultSequence = new AtomicInteger();
            MapServiceExceptionMapper mapper = new MapServiceExceptionMapper(
                    destination,
                    () -> new ServiceException.Code(9000 + defaultSequence.incrementAndGet(), "undefined")
            );

            assertEquals(5500, mapper.map(new RouterException()).getCode().getCode());
            ServiceExceptionCodeSuppliers.setExceptionCodeOffset(6500);
            assertEquals(6500, mapper.map(new RouterException()).getCode().getCode());

            ServiceExceptionCodeSuppliers.setExceptionCodeOffset(7000);
            assertEquals(7000, mapper.map(new ExtendedRouterException()).getCode().getCode());
            ServiceExceptionCodeSuppliers.setExceptionCodeOffset(8000);
            assertEquals(8000, mapper.map(new ExtendedRouterException()).getCode().getCode());

            assertEquals(9001, mapper.map(new IllegalStateException()).getCode().getCode());
            assertEquals(9002, mapper.map(new IllegalStateException()).getCode().getCode());
        } finally {
            ServiceExceptionCodeSuppliers.setExceptionCodeOffset(originalOffset);
        }
    }

    private static class ExtendedRouterException extends RouterException {

        @Serial
        private static final long serialVersionUID = -4014980740436337145L;
    }
}

package com.dwarfeng.notify.sdk.exception;

import com.dwarfeng.subgrade.base.sdk.i18n.MessageContext;
import com.dwarfeng.subgrade.basic.stack.exception.ServiceException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 服务异常序列化测试。
 *
 * @author DwArFeng
 * @since 4.0.0
 */
public class ServiceExceptionSerializationTest {

    @Test
    public void shouldPreserveCodeTipAndMessageAfterSerialization() throws Exception {
        ServiceException source = new ServiceException(new ServiceException.Code(5500, "serialization tip"));

        byte[] serialized;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(source);
            serialized = byteArrayOutputStream.toByteArray();
        }

        ServiceException restored;
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(serialized);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            restored = (ServiceException) objectInputStream.readObject();
        }

        assertEquals(5500, restored.getCode().getCode());
        assertEquals("serialization tip", restored.getCode().getTip());
        assertEquals(
                "Exception code=5500 - serialization tip",
                MessageContext.call(Locale.ENGLISH, restored::getMessage)
        );
    }
}

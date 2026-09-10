package com.dwarfeng.notify.sdk.internal.i18n;

import com.dwarfeng.notify.base.sdk.i18n.Messages;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SDK 模块消息测试。
 *
 * @author DwArFeng
 * @since 4.0.0
 */
public class SdkMessagesTest {

    @Test
    public void shouldResolveEnglishAndSimplifiedChineseMessages() {
        assertEquals(
                "router failed",
                SdkMessages.message(Locale.ENGLISH, SdkMessageKey.SERVICE_EXCEPTION_ROUTER_FAILED)
        );
        assertEquals(
                "路由器失败",
                SdkMessages.message(Locale.SIMPLIFIED_CHINESE, SdkMessageKey.SERVICE_EXCEPTION_ROUTER_FAILED)
        );
    }

    @Test
    public void shouldFallbackToMissingKeyMarker() {
        assertEquals(
                "!missing.key!",
                Messages.resolve(SdkMessages.Catalog.SDK.messageCatalog(), "missing.key", Locale.ENGLISH)
        );
    }

    @Test
    public void shouldKeepKeysAlignedWithResources() throws IOException {
        Map<SdkMessages.Catalog, Set<String>> keys = Arrays.stream(SdkMessageKey.values()).collect(
                Collectors.groupingBy(
                        SdkMessageKey::catalog, Collectors.mapping(SdkMessageKey::key, Collectors.toSet())
                )
        );

        for (SdkMessages.Catalog catalog : SdkMessages.Catalog.values()) {
            Properties rootProperties = loadProperties(catalog, Locale.ROOT);
            Properties chineseProperties = loadProperties(catalog, Locale.SIMPLIFIED_CHINESE);
            assertEquals(keys.get(catalog), rootProperties.stringPropertyNames());
            assertEquals(rootProperties.stringPropertyNames(), chineseProperties.stringPropertyNames());
        }
    }

    @Test
    public void shouldCacheOneMessageCatalogPerCatalog() {
        Object first = SdkMessages.Catalog.SDK.messageCatalog();
        Object second = SdkMessages.Catalog.SDK.messageCatalog();
        assertSame(first, second);
    }

    private Properties loadProperties(SdkMessages.Catalog catalog, Locale locale) throws IOException {
        String resourceName = catalog.messageCatalog().baseName().replace('.', '/');
        if (!Locale.ROOT.equals(locale)) {
            resourceName += "_" + locale;
        }
        resourceName += ".properties";

        try (InputStream inputStream = catalog.messageCatalog().module().getResourceAsStream(resourceName)) {
            assertNotNull(inputStream, resourceName);
            Properties properties = new Properties();
            properties.load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            return properties;
        }
    }
}

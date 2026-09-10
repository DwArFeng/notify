package com.dwarfeng.notify.stack.internal.i18n;

import com.dwarfeng.notify.base.sdk.i18n.Messages;
import com.dwarfeng.notify.base.stack.i18n.MessageCatalog;

import java.util.Locale;

/**
 * Stack 模块私有消息入口。
 *
 * <p>
 * 该工具隐藏资源路径和项目内部国际化协议，不属于公共 API。
 *
 * @author DwArFeng
 * @since 4.0.0
 */
public final class StackMessages {

    public static String message(StackMessageKey key, Object... args) {
        return Messages.resolve(key.catalog().messageCatalog(), key.key(), args);
    }

    public static String message(Locale locale, StackMessageKey key, Object... args) {
        return Messages.resolve(key.catalog().messageCatalog(), key.key(), locale, args);
    }

    private StackMessages() {
        throw new AssertionError("No instances");
    }

    enum Catalog {

        STACK("com.dwarfeng.notify.stack.i18n.messages");

        private final MessageCatalog messageCatalog;

        Catalog(String baseName) {
            this.messageCatalog = MessageCatalog.of(StackMessages.class, baseName);
        }

        MessageCatalog messageCatalog() {
            return messageCatalog;
        }
    }
}

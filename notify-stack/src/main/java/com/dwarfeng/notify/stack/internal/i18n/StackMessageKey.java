package com.dwarfeng.notify.stack.internal.i18n;

import static com.dwarfeng.notify.stack.internal.i18n.StackMessages.Catalog.STACK;

/**
 * Stack 模块消息键。
 *
 * @author DwArFeng
 * @since 4.0.0
 */
public enum StackMessageKey {

    DISPATCHER_INFO_NOT_EXISTS(STACK, "exception.dispatcher_info_not_exists"),
    DISPATCHER_MAKE_EXCEPTION(STACK, "exception.dispatcher_make_exception"),
    NOTIFY_SETTING_DISABLED(STACK, "exception.notify_setting_disabled"),
    NOTIFY_SETTING_NOT_EXISTS(STACK, "exception.notify_setting_not_exists"),
    ROUTER_INFO_NOT_EXISTS(STACK, "exception.router_info_not_exists"),
    ROUTER_MAKE_EXCEPTION(STACK, "exception.router_make_exception"),
    SENDER_INFO_NOT_EXISTS(STACK, "exception.sender_info_not_exists"),
    SENDER_MAKE_EXCEPTION(STACK, "exception.sender_make_exception"),
    TOPIC_NOT_EXISTS(STACK, "exception.topic_not_exists"),
    UNSUPPORTED_DISPATCHER_TYPE(STACK, "exception.unsupported_dispatcher_type"),
    UNSUPPORTED_ROUTER_TYPE(STACK, "exception.unsupported_router_type"),
    UNSUPPORTED_SENDER_TYPE(STACK, "exception.unsupported_sender_type"),
    USER_NOT_EXISTS(STACK, "exception.user_not_exists");

    private final StackMessages.Catalog catalog;
    private final String key;

    StackMessageKey(StackMessages.Catalog catalog, String key) {
        this.catalog = catalog;
        this.key = key;
    }

    StackMessages.Catalog catalog() {
        return catalog;
    }

    public String key() {
        return key;
    }
}

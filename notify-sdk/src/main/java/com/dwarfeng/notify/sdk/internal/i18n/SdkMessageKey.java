package com.dwarfeng.notify.sdk.internal.i18n;

import static com.dwarfeng.notify.sdk.internal.i18n.SdkMessages.Catalog.SDK;

/**
 * SDK 模块消息键。
 *
 * @author DwArFeng
 * @since 4.0.0
 */
public enum SdkMessageKey {

    SERVICE_EXCEPTION_ROUTER_FAILED(SDK, "service_exception.router_failed"),
    SERVICE_EXCEPTION_ROUTER_EXECUTION_FAILED(SDK, "service_exception.router_execution_failed"),
    SERVICE_EXCEPTION_ROUTER_MAKE_FAILED(SDK, "service_exception.router_make_failed"),
    SERVICE_EXCEPTION_UNSUPPORTED_ROUTER_TYPE(SDK, "service_exception.unsupported_router_type"),
    SERVICE_EXCEPTION_SENDER_FAILED(SDK, "service_exception.sender_failed"),
    SERVICE_EXCEPTION_SENDER_EXECUTION_FAILED(SDK, "service_exception.sender_execution_failed"),
    SERVICE_EXCEPTION_SENDER_MAKE_FAILED(SDK, "service_exception.sender_make_failed"),
    SERVICE_EXCEPTION_UNSUPPORTED_SENDER_TYPE(SDK, "service_exception.unsupported_sender_type"),
    SERVICE_EXCEPTION_NOTIFY_SETTING_NOT_EXISTED(SDK, "service_exception.notify_setting_not_existed"),
    SERVICE_EXCEPTION_TOPIC_NOT_EXISTED(SDK, "service_exception.topic_not_existed"),
    SERVICE_EXCEPTION_USER_NOT_EXISTED(SDK, "service_exception.user_not_existed"),
    SERVICE_EXCEPTION_NOTIFY_SETTING_DISABLED(SDK, "service_exception.notify_setting_disabled"),
    SERVICE_EXCEPTION_DISPATCHER_FAILED(SDK, "service_exception.dispatcher_failed"),
    SERVICE_EXCEPTION_DISPATCHER_EXECUTION_FAILED(SDK, "service_exception.dispatcher_execution_failed"),
    SERVICE_EXCEPTION_DISPATCHER_MAKE_FAILED(SDK, "service_exception.dispatcher_make_failed"),
    SERVICE_EXCEPTION_UNSUPPORTED_DISPATCHER_TYPE(SDK, "service_exception.unsupported_dispatcher_type"),
    SERVICE_EXCEPTION_ROUTER_INFO_NOT_EXISTED(SDK, "service_exception.router_info_not_existed"),
    SERVICE_EXCEPTION_DISPATCHER_INFO_NOT_EXISTED(SDK, "service_exception.dispatcher_info_not_existed"),
    SERVICE_EXCEPTION_SENDER_INFO_NOT_EXISTED(SDK, "service_exception.sender_info_not_existed");

    private final SdkMessages.Catalog catalog;
    private final String key;

    SdkMessageKey(SdkMessages.Catalog catalog, String key) {
        this.catalog = catalog;
        this.key = key;
    }

    SdkMessages.Catalog catalog() {
        return catalog;
    }

    /**
     * 获取消息键。
     *
     * @return 消息键。
     */
    public String key() {
        return key;
    }
}

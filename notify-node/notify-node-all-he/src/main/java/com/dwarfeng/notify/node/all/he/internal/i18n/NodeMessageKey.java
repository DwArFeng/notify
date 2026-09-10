package com.dwarfeng.notify.node.all.he.internal.i18n;

import static com.dwarfeng.notify.node.all.he.internal.i18n.NodeMessages.Catalog.NODE;

/**
 * Node All HE 模块消息键。
 *
 * @author DwArFeng
 * @since 4.0.0
 */
public enum NodeMessageKey {

    LAUNCHER_RESETTING_ROUTER_SUPPORT(NODE, "launcher.resetting_router_support"),
    LAUNCHER_RESET_ROUTER_SUPPORT_FAILED(NODE, "launcher.reset_router_support_failed"),
    LAUNCHER_RESETTING_SENDER_SUPPORT(NODE, "launcher.resetting_sender_support"),
    LAUNCHER_RESET_SENDER_SUPPORT_FAILED(NODE, "launcher.reset_sender_support_failed"),
    LAUNCHER_RESETTING_DISPATCHER_SUPPORT(NODE, "launcher.resetting_dispatcher_support"),
    LAUNCHER_RESET_DISPATCHER_SUPPORT_FAILED(NODE, "launcher.reset_dispatcher_support_failed"),
    LAUNCHER_STARTING_RESET_IMMEDIATELY(NODE, "launcher.starting_reset_immediately"),
    LAUNCHER_START_RESET_FAILED(NODE, "launcher.start_reset_failed"),
    LAUNCHER_STARTING_RESET_DELAYED(NODE, "launcher.starting_reset_delayed"),
    LAUNCHER_STARTING_RESET(NODE, "launcher.starting_reset"),
    LAUNCHER_ONLINE_PURGE_IMMEDIATELY(NODE, "launcher.online_purge_immediately"),
    LAUNCHER_ONLINE_PURGE_FAILED(NODE, "launcher.online_purge_failed"),
    LAUNCHER_ONLINE_PURGE_DELAYED(NODE, "launcher.online_purge_delayed"),
    LAUNCHER_ONLINE_PURGE(NODE, "launcher.online_purge"),
    LAUNCHER_STARTING_PURGE_IMMEDIATELY(NODE, "launcher.starting_purge_immediately"),
    LAUNCHER_START_PURGE_FAILED(NODE, "launcher.start_purge_failed"),
    LAUNCHER_STARTING_PURGE_DELAYED(NODE, "launcher.starting_purge_delayed"),
    LAUNCHER_STARTING_PURGE(NODE, "launcher.starting_purge");

    private final NodeMessages.Catalog catalog;
    private final String key;

    NodeMessageKey(NodeMessages.Catalog catalog, String key) {
        this.catalog = catalog;
        this.key = key;
    }

    NodeMessages.Catalog catalog() {
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

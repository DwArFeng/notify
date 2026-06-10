package com.dwarfeng.notify.node.handler;

import com.dwarfeng.subgrade.stack.handler.Handler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LauncherSettingHandler implements Handler {

    @Value("${com.dwarfeng.notify.launcher.reset_router_support}")
    private boolean resetRouterSupport;

    @Value("${com.dwarfeng.notify.launcher.reset_sender_support}")
    private boolean resetSenderSupport;

    @Value("${com.dwarfeng.notify.launcher.reset_dispatcher_support}")
    private boolean resetDispatcherSupport;

    @Value("${com.dwarfeng.notify.launcher.start_reset_delay}")
    private long startResetDelay;

    @Value("${com.dwarfeng.notify.launcher.online_purge_delay}")
    private long onlinePurgeDelay;
    @Value("${com.dwarfeng.notify.launcher.enable_purge_delay}")
    private long enablePurgeDelay;

    public boolean isResetRouterSupport() {
        return resetRouterSupport;
    }

    public boolean isResetSenderSupport() {
        return resetSenderSupport;
    }

    public boolean isResetDispatcherSupport() {
        return resetDispatcherSupport;
    }

    public long getStartResetDelay() {
        return startResetDelay;
    }

    public long getOnlinePurgeDelay() {
        return onlinePurgeDelay;
    }

    public long getEnablePurgeDelay() {
        return enablePurgeDelay;
    }
}

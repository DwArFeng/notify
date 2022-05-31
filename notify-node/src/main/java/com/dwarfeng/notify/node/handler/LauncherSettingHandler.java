package com.dwarfeng.notify.node.handler;

import com.dwarfeng.subgrade.stack.handler.Handler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LauncherSettingHandler implements Handler {

    @Value("${launcher.reset_router_support}")
    private boolean resetRouterSupport;

    @Value("${launcher.reset_sender_support}")
    private boolean resetSenderSupport;

    public boolean isResetRouterSupport() {
        return resetRouterSupport;
    }

    public boolean isResetSenderSupport() {
        return resetSenderSupport;
    }
}

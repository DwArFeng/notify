package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.stack.handler.DispatchLocalCacheHandler;
import com.dwarfeng.notify.stack.handler.RouteLocalCacheHandler;
import com.dwarfeng.notify.stack.handler.SendLocalCacheHandler;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

/**
 * 重置处理器。
 *
 * @author DwArFeng
 * @since 1.4.2
 */
@Component
class ResetProcessor {

    private final RouteLocalCacheHandler routeLocalCacheHandler;
    private final DispatchLocalCacheHandler dispatchLocalCacheHandler;
    private final SendLocalCacheHandler sendLocalCacheHandler;

    ResetProcessor(
            RouteLocalCacheHandler routeLocalCacheHandler,
            DispatchLocalCacheHandler dispatchLocalCacheHandler,
            SendLocalCacheHandler sendLocalCacheHandler
    ) {
        this.routeLocalCacheHandler = routeLocalCacheHandler;
        this.dispatchLocalCacheHandler = dispatchLocalCacheHandler;
        this.sendLocalCacheHandler = sendLocalCacheHandler;
    }

    public void resetRoute() throws HandlerException {
        routeLocalCacheHandler.clear();
    }

    public void resetDispatch() throws HandlerException {
        dispatchLocalCacheHandler.clear();
    }

    public void resetSend() throws HandlerException {
        sendLocalCacheHandler.clear();
    }
}

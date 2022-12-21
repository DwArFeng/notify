package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.stack.handler.DispatchLocalCacheHandler;
import com.dwarfeng.notify.stack.handler.PushHandler;
import com.dwarfeng.notify.stack.handler.RouteLocalCacheHandler;
import com.dwarfeng.notify.stack.handler.SendLocalCacheHandler;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 重置处理器。
 *
 * @author DwArFeng
 * @since 1.4.2
 */
@Component
class ResetProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResetProcessor.class);

    private final RouteLocalCacheHandler routeLocalCacheHandler;
    private final DispatchLocalCacheHandler dispatchLocalCacheHandler;
    private final SendLocalCacheHandler sendLocalCacheHandler;

    private final PushHandler pushHandler;

    ResetProcessor(
            RouteLocalCacheHandler routeLocalCacheHandler,
            DispatchLocalCacheHandler dispatchLocalCacheHandler,
            SendLocalCacheHandler sendLocalCacheHandler,
            PushHandler pushHandler
    ) {
        this.routeLocalCacheHandler = routeLocalCacheHandler;
        this.dispatchLocalCacheHandler = dispatchLocalCacheHandler;
        this.sendLocalCacheHandler = sendLocalCacheHandler;
        this.pushHandler = pushHandler;
    }

    public void resetRoute() throws HandlerException {
        routeLocalCacheHandler.clear();

        try {
            pushHandler.routeReset();
        } catch (Exception e) {
            LOGGER.warn("推送路由被重置消息时发生异常, 本次消息将不会被推送, 异常信息如下: ", e);
        }
    }

    public void resetDispatch() throws HandlerException {
        dispatchLocalCacheHandler.clear();

        try {
            pushHandler.dispatchReset();
        } catch (Exception e) {
            LOGGER.warn("推送调度被重置消息时发生异常, 本次消息将不会被推送, 异常信息如下: ", e);
        }
    }

    public void resetSend() throws HandlerException {
        sendLocalCacheHandler.clear();

        try {
            pushHandler.sendReset();
        } catch (Exception e) {
            LOGGER.warn("推送推送被重置消息时发生异常, 本次消息将不会被推送, 异常信息如下: ", e);
        }
    }
}

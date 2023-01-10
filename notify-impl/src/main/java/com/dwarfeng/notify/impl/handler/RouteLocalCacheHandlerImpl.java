package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.stack.bean.entity.RouterInfo;
import com.dwarfeng.notify.stack.handler.RouteLocalCacheHandler;
import com.dwarfeng.notify.stack.handler.Router;
import com.dwarfeng.notify.stack.handler.RouterHandler;
import com.dwarfeng.notify.stack.service.RouterInfoMaintainService;
import com.dwarfeng.subgrade.impl.handler.Fetcher;
import com.dwarfeng.subgrade.impl.handler.GeneralLocalCacheHandler;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RouteLocalCacheHandlerImpl implements RouteLocalCacheHandler {

    private final GeneralLocalCacheHandler<LongIdKey, Router> handler;

    public RouteLocalCacheHandlerImpl(RouterFetcher routerFetcher) {
        this.handler = new GeneralLocalCacheHandler<>(routerFetcher);
    }

    @BehaviorAnalyse
    @Override
    public boolean exists(LongIdKey key) throws HandlerException {
        return handler.exists(key);
    }

    @BehaviorAnalyse
    @Override
    public Router get(LongIdKey key) throws HandlerException {
        return handler.get(key);
    }

    @BehaviorAnalyse
    @Override
    public boolean remove(LongIdKey key) {
        return handler.remove(key);
    }

    @BehaviorAnalyse
    @Override
    public void clear() throws HandlerException {
        handler.clear();
    }

    @Component
    public static class RouterFetcher implements Fetcher<LongIdKey, Router> {

        private final RouterInfoMaintainService routerInfoMaintainService;

        private final RouterHandler routerHandler;

        public RouterFetcher(RouterInfoMaintainService routerInfoMaintainService, RouterHandler routerHandler) {
            this.routerInfoMaintainService = routerInfoMaintainService;
            this.routerHandler = routerHandler;
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public boolean exists(LongIdKey key) throws Exception {
            return routerInfoMaintainService.exists(key);
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public Router fetch(LongIdKey key) throws Exception {
            RouterInfo routerInfo = routerInfoMaintainService.get(key);
            String type = routerInfo.getType();
            return routerHandler.make(type, routerInfo.getParam());
        }
    }
}

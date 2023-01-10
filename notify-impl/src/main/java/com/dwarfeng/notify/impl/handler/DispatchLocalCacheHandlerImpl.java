package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.stack.bean.entity.DispatcherInfo;
import com.dwarfeng.notify.stack.handler.DispatchLocalCacheHandler;
import com.dwarfeng.notify.stack.handler.Dispatcher;
import com.dwarfeng.notify.stack.handler.DispatcherHandler;
import com.dwarfeng.notify.stack.service.DispatcherInfoMaintainService;
import com.dwarfeng.subgrade.impl.handler.Fetcher;
import com.dwarfeng.subgrade.impl.handler.GeneralLocalCacheHandler;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DispatchLocalCacheHandlerImpl implements DispatchLocalCacheHandler {

    private final GeneralLocalCacheHandler<StringIdKey, Dispatcher> handler;

    public DispatchLocalCacheHandlerImpl(DispatchLocalCacheHandlerImpl.DispatcherFetcher dispatcherFetcher) {
        this.handler = new GeneralLocalCacheHandler<>(dispatcherFetcher);
    }

    @BehaviorAnalyse
    @Override
    public boolean exists(StringIdKey key) throws HandlerException {
        return handler.exists(key);
    }

    @BehaviorAnalyse
    @Override
    public Dispatcher get(StringIdKey key) throws HandlerException {
        return handler.get(key);
    }

    @BehaviorAnalyse
    @Override
    public boolean remove(StringIdKey key) {
        return handler.remove(key);
    }

    @BehaviorAnalyse
    @Override
    public void clear() throws HandlerException {
        handler.clear();
    }

    @Component
    public static class DispatcherFetcher implements Fetcher<StringIdKey, Dispatcher> {

        private final DispatcherInfoMaintainService dispatcherInfoMaintainService;

        private final DispatcherHandler dispatcherHandler;

        public DispatcherFetcher(DispatcherInfoMaintainService dispatcherInfoMaintainService, DispatcherHandler dispatcherHandler) {
            this.dispatcherInfoMaintainService = dispatcherInfoMaintainService;
            this.dispatcherHandler = dispatcherHandler;
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public boolean exists(StringIdKey key) throws Exception {
            return dispatcherInfoMaintainService.exists(key);
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public Dispatcher fetch(StringIdKey key) throws Exception {
            DispatcherInfo dispatcherInfo = dispatcherInfoMaintainService.get(key);
            String type = dispatcherInfo.getType();
            return dispatcherHandler.make(type, dispatcherInfo.getParam());
        }
    }
}

package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.stack.bean.dto.RouteContext;
import com.dwarfeng.notify.stack.bean.entity.RouterInfo;
import com.dwarfeng.notify.stack.handler.RouteLocalCacheHandler;
import com.dwarfeng.notify.stack.handler.Router;
import com.dwarfeng.notify.stack.handler.RouterHandler;
import com.dwarfeng.notify.stack.service.RouterInfoMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class RouteLocalCacheHandlerImpl implements RouteLocalCacheHandler {

    private final RouterFetcher routerFetcher;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Map<LongIdKey, RouteContext> routeContextMap = new HashMap<>();
    private final Set<LongIdKey> notExistSettings = new HashSet<>();

    public RouteLocalCacheHandlerImpl(RouterFetcher routerFetcher) {
        this.routerFetcher = routerFetcher;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public RouteContext getContext(LongIdKey routeInfoKey) throws HandlerException {
        try {
            lock.readLock().lock();
            try {
                if (routeContextMap.containsKey(routeInfoKey)) {
                    return routeContextMap.get(routeInfoKey);
                }
                if (notExistSettings.contains(routeInfoKey)) {
                    return null;
                }
            } finally {
                lock.readLock().unlock();
            }
            lock.writeLock().lock();
            try {
                if (routeContextMap.containsKey(routeInfoKey)) {
                    return routeContextMap.get(routeInfoKey);
                }
                if (notExistSettings.contains(routeInfoKey)) {
                    return null;
                }
                RouteContext routeContext = routerFetcher.fetchRouter(routeInfoKey);
                if (Objects.nonNull(routeContext)) {
                    routeContextMap.put(routeInfoKey, routeContext);
                    return routeContext;
                }
                notExistSettings.add(routeInfoKey);
                return null;
            } finally {
                lock.writeLock().unlock();
            }
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void clear() throws HandlerException {
        lock.writeLock().lock();
        try {
            routeContextMap.clear();
            notExistSettings.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Component
    public static class RouterFetcher {

        private final RouterInfoMaintainService routerInfoMaintainService;

        private final RouterHandler routerHandler;

        public RouterFetcher(RouterInfoMaintainService routerInfoMaintainService, RouterHandler routerHandler) {
            this.routerInfoMaintainService = routerInfoMaintainService;
            this.routerHandler = routerHandler;
        }

        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public RouteContext fetchRouter(LongIdKey commandSettingKey) throws Exception {
            if (!routerInfoMaintainService.exists(commandSettingKey)) {
                return null;
            }
            RouterInfo routerInfo = routerInfoMaintainService.get(commandSettingKey);
            Router router = routerHandler.make(routerInfo.getType(), routerInfo.getParam());
            return new RouteContext(routerInfo, router);
        }
    }
}

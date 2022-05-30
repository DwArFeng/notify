package com.dwarfeng.notify.impl.handler;

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
    private final Map<LongIdKey, Router> routerMap = new HashMap<>();
    private final Set<LongIdKey> notExistSettings = new HashSet<>();

    public RouteLocalCacheHandlerImpl(RouterFetcher routerFetcher) {
        this.routerFetcher = routerFetcher;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public Router getRouter(LongIdKey routerInfoKey) throws HandlerException {
        try {
            lock.readLock().lock();
            try {
                if (routerMap.containsKey(routerInfoKey)) {
                    return routerMap.get(routerInfoKey);
                }
                if (notExistSettings.contains(routerInfoKey)) {
                    return null;
                }
            } finally {
                lock.readLock().unlock();
            }
            lock.writeLock().lock();
            try {
                if (routerMap.containsKey(routerInfoKey)) {
                    return routerMap.get(routerInfoKey);
                }
                if (notExistSettings.contains(routerInfoKey)) {
                    return null;
                }
                Router router = routerFetcher.fetchRouter(routerInfoKey);
                if (Objects.nonNull(router)) {
                    routerMap.put(routerInfoKey, router);
                    return router;
                }
                notExistSettings.add(routerInfoKey);
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
            routerMap.clear();
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

        @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
        public Router fetchRouter(LongIdKey commandSettingKey) throws Exception {
            if (!routerInfoMaintainService.exists(commandSettingKey)) {
                return null;
            }
            RouterInfo routerInfo = routerInfoMaintainService.get(commandSettingKey);
            return routerHandler.make(routerInfo.getType(), routerInfo.getParam());
        }
    }
}

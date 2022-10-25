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
    private final Map<LongIdKey, FetchedItem> itemMap = new HashMap<>();
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
                if (itemMap.containsKey(routerInfoKey)) {
                    return itemMap.get(routerInfoKey).getRouter();
                }
                if (notExistSettings.contains(routerInfoKey)) {
                    return null;
                }
            } finally {
                lock.readLock().unlock();
            }
            lock.writeLock().lock();
            try {
                if (itemMap.containsKey(routerInfoKey)) {
                    return itemMap.get(routerInfoKey).getRouter();
                }
                if (notExistSettings.contains(routerInfoKey)) {
                    return null;
                }
                FetchedItem fetchedItem = routerFetcher.fetchRouter(routerInfoKey);
                if (Objects.nonNull(fetchedItem)) {
                    itemMap.put(routerInfoKey, fetchedItem);
                    return fetchedItem.getRouter();
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

    @SuppressWarnings("DuplicatedCode")
    @Override
    public String getType(LongIdKey routerInfoKey) throws HandlerException {
        try {
            lock.readLock().lock();
            try {
                if (itemMap.containsKey(routerInfoKey)) {
                    return itemMap.get(routerInfoKey).getType();
                }
                if (notExistSettings.contains(routerInfoKey)) {
                    return null;
                }
            } finally {
                lock.readLock().unlock();
            }
            lock.writeLock().lock();
            try {
                if (itemMap.containsKey(routerInfoKey)) {
                    return itemMap.get(routerInfoKey).getType();
                }
                if (notExistSettings.contains(routerInfoKey)) {
                    return null;
                }
                FetchedItem fetchedItem = routerFetcher.fetchRouter(routerInfoKey);
                if (Objects.nonNull(fetchedItem)) {
                    itemMap.put(routerInfoKey, fetchedItem);
                    return fetchedItem.getType();
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
            itemMap.clear();
            notExistSettings.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    private static class FetchedItem {

        private final String type;
        private final Router router;

        public FetchedItem(String type, Router router) {
            this.type = type;
            this.router = router;
        }

        public String getType() {
            return type;
        }

        public Router getRouter() {
            return router;
        }

        @Override
        public String toString() {
            return "FetchedItem{" +
                    "type='" + type + '\'' +
                    ", router=" + router +
                    '}';
        }
    }

    @Component
    public static class RouterFetcher {

        private final RouterInfoMaintainService routerInfoMaintainService;

        private final RouterHandler routerHandler;

        public RouterFetcher(
                RouterInfoMaintainService routerInfoMaintainService,
                RouterHandler routerHandler
        ) {
            this.routerInfoMaintainService = routerInfoMaintainService;
            this.routerHandler = routerHandler;
        }

        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public FetchedItem fetchRouter(LongIdKey routerInfoKey) throws Exception {
            if (!routerInfoMaintainService.exists(routerInfoKey)) {
                return null;
            }
            RouterInfo routerInfo = routerInfoMaintainService.get(routerInfoKey);
            Router router = routerHandler.make(routerInfo.getType(), routerInfo.getParam());
            return new FetchedItem(routerInfo.getType(), router);
        }
    }
}

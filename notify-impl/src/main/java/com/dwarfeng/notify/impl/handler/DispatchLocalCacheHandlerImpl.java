package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.stack.bean.entity.DispatcherInfo;
import com.dwarfeng.notify.stack.handler.DispatchLocalCacheHandler;
import com.dwarfeng.notify.stack.handler.Dispatcher;
import com.dwarfeng.notify.stack.handler.DispatcherHandler;
import com.dwarfeng.notify.stack.service.DispatcherInfoMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class DispatchLocalCacheHandlerImpl implements DispatchLocalCacheHandler {

    private final DispatcherFetcher dispatcherFetcher;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Map<StringIdKey, FetchedItem> itemMap = new HashMap<>();
    private final Set<StringIdKey> notExistSettings = new HashSet<>();

    public DispatchLocalCacheHandlerImpl(DispatcherFetcher dispatcherFetcher) {
        this.dispatcherFetcher = dispatcherFetcher;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public Dispatcher getDispatcher(StringIdKey dispatcherInfoKey) throws HandlerException {
        try {
            lock.readLock().lock();
            try {
                if (itemMap.containsKey(dispatcherInfoKey)) {
                    return itemMap.get(dispatcherInfoKey).getDispatcher();
                }
                if (notExistSettings.contains(dispatcherInfoKey)) {
                    return null;
                }
            } finally {
                lock.readLock().unlock();
            }
            lock.writeLock().lock();
            try {
                if (itemMap.containsKey(dispatcherInfoKey)) {
                    return itemMap.get(dispatcherInfoKey).getDispatcher();
                }
                if (notExistSettings.contains(dispatcherInfoKey)) {
                    return null;
                }
                FetchedItem fetchedItem = dispatcherFetcher.fetchDispatcher(dispatcherInfoKey);
                if (Objects.nonNull(fetchedItem)) {
                    itemMap.put(dispatcherInfoKey, fetchedItem);
                    return fetchedItem.getDispatcher();
                }
                notExistSettings.add(dispatcherInfoKey);
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
    public String getType(StringIdKey dispatcherInfoKey) throws HandlerException {
        try {
            lock.readLock().lock();
            try {
                if (itemMap.containsKey(dispatcherInfoKey)) {
                    return itemMap.get(dispatcherInfoKey).getType();
                }
                if (notExistSettings.contains(dispatcherInfoKey)) {
                    return null;
                }
            } finally {
                lock.readLock().unlock();
            }
            lock.writeLock().lock();
            try {
                if (itemMap.containsKey(dispatcherInfoKey)) {
                    return itemMap.get(dispatcherInfoKey).getType();
                }
                if (notExistSettings.contains(dispatcherInfoKey)) {
                    return null;
                }
                FetchedItem fetchedItem = dispatcherFetcher.fetchDispatcher(dispatcherInfoKey);
                if (Objects.nonNull(fetchedItem)) {
                    itemMap.put(dispatcherInfoKey, fetchedItem);
                    return fetchedItem.getType();
                }
                notExistSettings.add(dispatcherInfoKey);
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
        private final Dispatcher dispatcher;

        public FetchedItem(String type, Dispatcher dispatcher) {
            this.type = type;
            this.dispatcher = dispatcher;
        }

        public String getType() {
            return type;
        }

        public Dispatcher getDispatcher() {
            return dispatcher;
        }

        @Override
        public String toString() {
            return "FetchedItem{" +
                    "type='" + type + '\'' +
                    ", dispatcher=" + dispatcher +
                    '}';
        }
    }

    @Component
    public static class DispatcherFetcher {

        private final DispatcherInfoMaintainService dispatcherInfoMaintainService;

        private final DispatcherHandler dispatcherHandler;

        public DispatcherFetcher(
                DispatcherInfoMaintainService dispatcherInfoMaintainService,
                DispatcherHandler dispatcherHandler
        ) {
            this.dispatcherInfoMaintainService = dispatcherInfoMaintainService;
            this.dispatcherHandler = dispatcherHandler;
        }

        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public FetchedItem fetchDispatcher(StringIdKey dispatcherInfoKey) throws Exception {
            if (!dispatcherInfoMaintainService.exists(dispatcherInfoKey)) {
                return null;
            }
            DispatcherInfo dispatcherInfo = dispatcherInfoMaintainService.get(dispatcherInfoKey);
            Dispatcher dispatcher = dispatcherHandler.make(dispatcherInfo.getType(), dispatcherInfo.getParam());
            return new FetchedItem(dispatcherInfo.getType(), dispatcher);
        }
    }
}

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
    private final Map<StringIdKey, Dispatcher> dispatcherMap = new HashMap<>();
    private final Set<StringIdKey> notExistSettings = new HashSet<>();

    public DispatchLocalCacheHandlerImpl(DispatcherFetcher dispatcherFetcher) {
        this.dispatcherFetcher = dispatcherFetcher;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public Dispatcher getDispatcher(StringIdKey dispatchInfoKey) throws HandlerException {
        try {
            lock.readLock().lock();
            try {
                if (dispatcherMap.containsKey(dispatchInfoKey)) {
                    return dispatcherMap.get(dispatchInfoKey);
                }
                if (notExistSettings.contains(dispatchInfoKey)) {
                    return null;
                }
            } finally {
                lock.readLock().unlock();
            }
            lock.writeLock().lock();
            try {
                if (dispatcherMap.containsKey(dispatchInfoKey)) {
                    return dispatcherMap.get(dispatchInfoKey);
                }
                if (notExistSettings.contains(dispatchInfoKey)) {
                    return null;
                }
                Dispatcher dispatcher = dispatcherFetcher.fetchDispatcher(dispatchInfoKey);
                if (Objects.nonNull(dispatcher)) {
                    dispatcherMap.put(dispatchInfoKey, dispatcher);
                    return dispatcher;
                }
                notExistSettings.add(dispatchInfoKey);
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
            dispatcherMap.clear();
            notExistSettings.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Component
    public static class DispatcherFetcher {

        private final DispatcherInfoMaintainService dispatcherInfoMaintainService;

        private final DispatcherHandler dispatcherHandler;

        public DispatcherFetcher(DispatcherInfoMaintainService dispatcherInfoMaintainService, DispatcherHandler dispatcherHandler) {
            this.dispatcherInfoMaintainService = dispatcherInfoMaintainService;
            this.dispatcherHandler = dispatcherHandler;
        }

        @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
        public Dispatcher fetchDispatcher(StringIdKey dispatchInfoKey) throws Exception {
            if (!dispatcherInfoMaintainService.exists(dispatchInfoKey)) {
                return null;
            }
            DispatcherInfo dispatcherInfo = dispatcherInfoMaintainService.get(dispatchInfoKey);
            return dispatcherHandler.make(dispatcherInfo.getType(), dispatcherInfo.getParam());
        }
    }
}

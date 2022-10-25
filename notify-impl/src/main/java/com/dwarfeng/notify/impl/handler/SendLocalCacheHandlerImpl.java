package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.stack.bean.entity.SenderInfo;
import com.dwarfeng.notify.stack.bean.entity.key.SenderInfoKey;
import com.dwarfeng.notify.stack.handler.SendLocalCacheHandler;
import com.dwarfeng.notify.stack.handler.Sender;
import com.dwarfeng.notify.stack.handler.SenderHandler;
import com.dwarfeng.notify.stack.service.SenderInfoMaintainService;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class SendLocalCacheHandlerImpl implements SendLocalCacheHandler {

    private final SenderFetcher senderFetcher;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Map<SenderInfoKey, FetchedItem> itemMap = new HashMap<>();
    private final Set<SenderInfoKey> notExistSettings = new HashSet<>();

    public SendLocalCacheHandlerImpl(SenderFetcher senderFetcher) {
        this.senderFetcher = senderFetcher;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public Sender getSender(SenderInfoKey senderInfoKey) throws HandlerException {
        try {
            lock.readLock().lock();
            try {
                if (itemMap.containsKey(senderInfoKey)) {
                    return itemMap.get(senderInfoKey).getSender();
                }
                if (notExistSettings.contains(senderInfoKey)) {
                    return null;
                }
            } finally {
                lock.readLock().unlock();
            }
            lock.writeLock().lock();
            try {
                if (itemMap.containsKey(senderInfoKey)) {
                    return itemMap.get(senderInfoKey).getSender();
                }
                if (notExistSettings.contains(senderInfoKey)) {
                    return null;
                }
                FetchedItem fetchedItem = senderFetcher.fetchSender(senderInfoKey);
                if (Objects.nonNull(fetchedItem)) {
                    itemMap.put(senderInfoKey, fetchedItem);
                    return fetchedItem.getSender();
                }
                notExistSettings.add(senderInfoKey);
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
    public String getType(SenderInfoKey senderInfoKey) throws HandlerException {
        try {
            lock.readLock().lock();
            try {
                if (itemMap.containsKey(senderInfoKey)) {
                    return itemMap.get(senderInfoKey).getType();
                }
                if (notExistSettings.contains(senderInfoKey)) {
                    return null;
                }
            } finally {
                lock.readLock().unlock();
            }
            lock.writeLock().lock();
            try {
                if (itemMap.containsKey(senderInfoKey)) {
                    return itemMap.get(senderInfoKey).getType();
                }
                if (notExistSettings.contains(senderInfoKey)) {
                    return null;
                }
                FetchedItem fetchedItem = senderFetcher.fetchSender(senderInfoKey);
                if (Objects.nonNull(fetchedItem)) {
                    itemMap.put(senderInfoKey, fetchedItem);
                    return fetchedItem.getType();
                }
                notExistSettings.add(senderInfoKey);
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
        private final Sender sender;

        public FetchedItem(String type, Sender sender) {
            this.type = type;
            this.sender = sender;
        }

        public String getType() {
            return type;
        }

        public Sender getSender() {
            return sender;
        }

        @Override
        public String toString() {
            return "FetchedItem{" +
                    "type='" + type + '\'' +
                    ", sender=" + sender +
                    '}';
        }
    }

    @Component
    public static class SenderFetcher {

        private final SenderInfoMaintainService senderInfoMaintainService;

        private final SenderHandler senderHandler;

        public SenderFetcher(
                SenderInfoMaintainService senderInfoMaintainService,
                SenderHandler senderHandler
        ) {
            this.senderInfoMaintainService = senderInfoMaintainService;
            this.senderHandler = senderHandler;
        }

        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public FetchedItem fetchSender(SenderInfoKey senderInfoKey) throws Exception {
            if (!senderInfoMaintainService.exists(senderInfoKey)) {
                return null;
            }
            SenderInfo senderInfo = senderInfoMaintainService.get(senderInfoKey);
            Sender sender = senderHandler.make(senderInfo.getType(), senderInfo.getParam());
            return new FetchedItem(senderInfo.getType(), sender);
        }
    }
}

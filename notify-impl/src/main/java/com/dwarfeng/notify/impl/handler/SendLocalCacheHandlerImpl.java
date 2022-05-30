package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.stack.bean.entity.SenderInfo;
import com.dwarfeng.notify.stack.handler.SendLocalCacheHandler;
import com.dwarfeng.notify.stack.handler.Sender;
import com.dwarfeng.notify.stack.handler.SenderHandler;
import com.dwarfeng.notify.stack.service.SenderInfoMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
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
    private final Map<LongIdKey, Sender> senderMap = new HashMap<>();
    private final Set<LongIdKey> notExistSettings = new HashSet<>();

    public SendLocalCacheHandlerImpl(SenderFetcher senderFetcher) {
        this.senderFetcher = senderFetcher;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public Sender getSender(LongIdKey sendInfoKey) throws HandlerException {
        try {
            lock.readLock().lock();
            try {
                if (senderMap.containsKey(sendInfoKey)) {
                    return senderMap.get(sendInfoKey);
                }
                if (notExistSettings.contains(sendInfoKey)) {
                    return null;
                }
            } finally {
                lock.readLock().unlock();
            }
            lock.writeLock().lock();
            try {
                if (senderMap.containsKey(sendInfoKey)) {
                    return senderMap.get(sendInfoKey);
                }
                if (notExistSettings.contains(sendInfoKey)) {
                    return null;
                }
                Sender sender = senderFetcher.fetchSender(sendInfoKey);
                if (Objects.nonNull(sender)) {
                    senderMap.put(sendInfoKey, sender);
                    return sender;
                }
                notExistSettings.add(sendInfoKey);
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
            senderMap.clear();
            notExistSettings.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Component
    public static class SenderFetcher {

        private final SenderInfoMaintainService senderInfoMaintainService;

        private final SenderHandler senderHandler;

        public SenderFetcher(SenderInfoMaintainService senderInfoMaintainService, SenderHandler senderHandler) {
            this.senderInfoMaintainService = senderInfoMaintainService;
            this.senderHandler = senderHandler;
        }

        @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
        public Sender fetchSender(LongIdKey commandSettingKey) throws Exception {
            if (!senderInfoMaintainService.exists(commandSettingKey)) {
                return null;
            }
            SenderInfo senderInfo = senderInfoMaintainService.get(commandSettingKey);
            return senderHandler.make(senderInfo.getType(), senderInfo.getParam());
        }
    }
}

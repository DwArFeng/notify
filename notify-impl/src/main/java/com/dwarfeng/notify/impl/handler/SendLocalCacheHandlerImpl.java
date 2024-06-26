package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.stack.bean.entity.SenderInfo;
import com.dwarfeng.notify.stack.bean.key.SenderInfoKey;
import com.dwarfeng.notify.stack.handler.SendLocalCacheHandler;
import com.dwarfeng.notify.stack.handler.Sender;
import com.dwarfeng.notify.stack.handler.SenderHandler;
import com.dwarfeng.notify.stack.service.SenderInfoMaintainService;
import com.dwarfeng.subgrade.impl.handler.Fetcher;
import com.dwarfeng.subgrade.impl.handler.GeneralLocalCacheHandler;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SendLocalCacheHandlerImpl implements SendLocalCacheHandler {

    private final GeneralLocalCacheHandler<SenderInfoKey, Sender> handler;

    public SendLocalCacheHandlerImpl(SendLocalCacheHandlerImpl.SenderFetcher senderFetcher) {
        this.handler = new GeneralLocalCacheHandler<>(senderFetcher);
    }

    @BehaviorAnalyse
    @Override
    public boolean exists(SenderInfoKey key) throws HandlerException {
        return handler.exists(key);
    }

    @BehaviorAnalyse
    @Override
    public Sender get(SenderInfoKey key) throws HandlerException {
        return handler.get(key);
    }

    @BehaviorAnalyse
    @Override
    public boolean remove(SenderInfoKey key) {
        return handler.remove(key);
    }

    @BehaviorAnalyse
    @Override
    public void clear() {
        handler.clear();
    }

    @Component
    public static class SenderFetcher implements Fetcher<SenderInfoKey, Sender> {

        private final SenderInfoMaintainService senderInfoMaintainService;

        private final SenderHandler senderHandler;

        public SenderFetcher(SenderInfoMaintainService senderInfoMaintainService, SenderHandler senderHandler) {
            this.senderInfoMaintainService = senderInfoMaintainService;
            this.senderHandler = senderHandler;
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public boolean exists(SenderInfoKey key) throws Exception {
            return senderInfoMaintainService.exists(key);
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public Sender fetch(SenderInfoKey key) throws Exception {
            SenderInfo senderInfo = senderInfoMaintainService.get(key);
            String type = senderInfo.getType();
            return senderHandler.make(type, senderInfo.getParam());
        }
    }
}

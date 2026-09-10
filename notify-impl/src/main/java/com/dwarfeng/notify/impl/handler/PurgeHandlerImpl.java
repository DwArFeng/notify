package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.notify.impl.internal.i18n.ImplMessages;

import com.dwarfeng.notify.stack.handler.PurgeHandler;
import com.dwarfeng.subgrade.aop.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.basic.stack.exception.HandlerException;
import com.dwarfeng.subgrade.lifecycle.stack.handler.Worker;
import com.dwarfeng.subgrade.lock.impl.handler.curator.CuratorDistributedLockHandler;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PurgeHandlerImpl implements PurgeHandler {

    private final CuratorDistributedLockHandler handler;

    public PurgeHandlerImpl(
            CuratorFramework curatorFramework,
            @Value("${com.dwarfeng.notify.curator.latch_path.purge.leader_latch}") String leaserLatchPath,
            PurgeWorker purgeWorker
    ) {
        handler = new CuratorDistributedLockHandler(curatorFramework, leaserLatchPath, purgeWorker);
    }

    @BehaviorAnalyse
    @Override
    public boolean isOnline() {
        return handler.isOnline();
    }

    @BehaviorAnalyse
    @Override
    public void online() throws HandlerException {
        handler.online();
    }

    @BehaviorAnalyse
    @Override
    public void offline() throws HandlerException {
        handler.offline();
    }

    @BehaviorAnalyse
    @Override
    public boolean isStarted() {
        return handler.isStarted();
    }

    @BehaviorAnalyse
    @Override
    public void start() throws HandlerException {
        handler.start();
    }

    @BehaviorAnalyse
    @Override
    public void stop() throws HandlerException {
        handler.stop();
    }

    @BehaviorAnalyse
    @Override
    public boolean isLockHolding() {
        return handler.isLockHolding();
    }

    @BehaviorAnalyse
    @Override
    public boolean isWorking() {
        return handler.isWorking();
    }

    @Component
    public static class PurgeWorker implements Worker {

        private static final Logger LOGGER = LoggerFactory.getLogger(PurgeWorker.class);

        private final PurgeProcessor purgeProcessor;

        public PurgeWorker(PurgeProcessor purgeProcessor) {
            this.purgeProcessor = purgeProcessor;
        }

        @Override
        public void work() {
            LOGGER.info(ImplMessages.message(ImplMessageKey.LOG_PURGE_HANDLER_WORKING_STARTED));
            purgeProcessor.work();
        }

        @Override
        public void rest() {
            LOGGER.info(ImplMessages.message(ImplMessageKey.LOG_PURGE_HANDLER_WORKING_STOPPED));
            purgeProcessor.rest();
        }
    }
}

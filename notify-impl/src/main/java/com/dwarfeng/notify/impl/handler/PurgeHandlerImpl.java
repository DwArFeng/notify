package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.stack.handler.PurgeHandler;
import com.dwarfeng.subgrade.impl.handler.CuratorDistributedLockHandler;
import com.dwarfeng.subgrade.impl.handler.Worker;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
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
            @Value("${curator.latch_path.purge.leader_latch}") String leaserLatchPath,
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
            LOGGER.info("清除处理器开始工作...");
            purgeProcessor.work();
        }

        @Override
        public void rest() {
            LOGGER.info("清除处理器停止工作...");
            purgeProcessor.rest();
        }
    }
}

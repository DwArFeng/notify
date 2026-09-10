package com.dwarfeng.notify.impl.handler.resetter;

import com.dwarfeng.notify.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.notify.impl.internal.i18n.ImplMessages;

import com.dwarfeng.notify.sdk.handler.resetter.AbstractResetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledFuture;

/**
 * 使用 CRON 表达式定时重置的重置器。
 *
 * @author DwArFeng
 * @since 1.2.2
 */
@Component
public class CronResetter extends AbstractResetter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CronResetter.class);

    private final ThreadPoolTaskScheduler scheduler;

    @Value("${com.dwarfeng.notify.resetter.cron.cron}")
    private String cron;

    private final ResetTask resetTask = new ResetTask();

    private ScheduledFuture<?> resetTaskFuture;

    public CronResetter(ThreadPoolTaskScheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    protected void doStart() {
        resetTaskFuture = scheduler.schedule(resetTask, new CronTrigger(cron));
    }

    @Override
    protected void doStop() {
        resetTaskFuture.cancel(true);
    }

    @Override
    public String toString() {
        return "CronResetter{" +
                "cron='" + cron + '\'' +
                '}';
    }

    private class ResetTask implements Runnable {

        @Override
        public void run() {
            try {
                LOGGER.info(ImplMessages.message(ImplMessageKey.LOG_RESET_SCHEDULE_TRIGGERED));
                context.resetRoute();
                context.resetDispatch();
                context.resetSend();
            } catch (Exception e) {
                String message = ImplMessages.message(ImplMessageKey.TELQOS_RESET_RESETTER, CronResetter.this) +
                        ImplMessages.message(ImplMessageKey.LOG_RESET_ALL_FAILED);
                LOGGER.warn(message, e);
            }
        }
    }
}

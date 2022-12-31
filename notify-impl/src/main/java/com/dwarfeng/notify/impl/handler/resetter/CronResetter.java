package com.dwarfeng.notify.impl.handler.resetter;

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

    @Value("${resetter.cron.cron}")
    private String cron;

    private final ResetTask resetTask = new ResetTask();

    private ScheduledFuture<?> resetTaskFuture;

    public CronResetter(ThreadPoolTaskScheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void start() {
        resetTaskFuture = scheduler.schedule(resetTask, new CronTrigger(cron));
    }

    @Override
    public void stop() {
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
                LOGGER.info("计划时间已到, 重置路由, 调度, 发送...");
                context.resetRoute();
                context.resetDispatch();
                context.resetSend();
            } catch (Exception e) {
                String message = "重置器 " + CronResetter.this +
                        " 执行重置调度时发生异常, 路由, 调度, 发送将不会重置, 异常信息如下: ";
                LOGGER.warn(message, e);
            }
        }
    }
}

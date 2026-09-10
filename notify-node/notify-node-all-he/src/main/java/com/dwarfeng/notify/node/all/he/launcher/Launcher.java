package com.dwarfeng.notify.node.all.he.launcher;

import com.dwarfeng.notify.node.all.he.handler.LauncherSettingHandler;
import com.dwarfeng.notify.node.all.he.internal.i18n.NodeMessageKey;
import com.dwarfeng.notify.node.all.he.internal.i18n.NodeMessages;
import com.dwarfeng.notify.stack.service.PurgeQosService;
import com.dwarfeng.notify.stack.service.ResetQosService;
import com.dwarfeng.notify.stack.service.SupportQosService;
import com.dwarfeng.springterminator.sdk.util.ApplicationUtil;
import com.dwarfeng.subgrade.basic.stack.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.time.Instant;

/**
 * 程序启动器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class Launcher {

    private final static Logger LOGGER = LoggerFactory.getLogger(Launcher.class);

    static void main() {
        ApplicationUtil.launch(new String[]{
                "classpath:com/dwarfeng/notify/node/all/he/spring/application-context*.xml",
                "file:opt/opt*.xml",
                "file:optext/opt*.xml"
        }, ctx -> {
            // 根据启动器设置处理器的设置，选择性重置路由器。
            mayResetRouter(ctx);

            // 根据启动器设置处理器的设置，选择性重置发送器。
            mayResetSender(ctx);

            // 根据启动器设置处理器的设置，选择性重置调度器。
            mayResetDispatcher(ctx);

            // 根据启动器设置处理器的设置，选择性启动重置服务。
            mayStartReset(ctx);

            // 根据启动器设置处理器的设置，选择性上线清除服务。
            mayOnlinePurge(ctx);
            // 根据启动器设置处理器的设置，选择性启动清除服务。
            mayEnablePurge(ctx);
        });
    }

    private static void mayResetRouter(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置选择性执行功能。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 如果不重置路由器，则返回。
        if (!launcherSettingHandler.isResetRouterSupport()) {
            return;
        }

        // 重置路由器支持。
        LOGGER.info(NodeMessages.message(NodeMessageKey.LAUNCHER_RESETTING_ROUTER_SUPPORT));
        SupportQosService supportQosService = ctx.getBean(SupportQosService.class);
        try {
            supportQosService.resetRouter();
        } catch (ServiceException e) {
            LOGGER.warn(NodeMessages.message(NodeMessageKey.LAUNCHER_RESET_ROUTER_SUPPORT_FAILED), e);
        }
    }

    private static void mayResetSender(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置选择性执行功能。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 如果不重置发送器，则返回。
        if (!launcherSettingHandler.isResetSenderSupport()) {
            return;
        }

        // 重置发送器支持。
        LOGGER.info(NodeMessages.message(NodeMessageKey.LAUNCHER_RESETTING_SENDER_SUPPORT));
        SupportQosService supportQosService = ctx.getBean(SupportQosService.class);
        try {
            supportQosService.resetSender();
        } catch (ServiceException e) {
            LOGGER.warn(NodeMessages.message(NodeMessageKey.LAUNCHER_RESET_SENDER_SUPPORT_FAILED), e);
        }
    }

    private static void mayResetDispatcher(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置选择性执行功能。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 如果不重置调度器，则返回。
        if (!launcherSettingHandler.isResetDispatcherSupport()) {
            return;
        }

        // 重置调度器支持。
        LOGGER.info(NodeMessages.message(NodeMessageKey.LAUNCHER_RESETTING_DISPATCHER_SUPPORT));
        SupportQosService supportQosService = ctx.getBean(SupportQosService.class);
        try {
            supportQosService.resetDispatcher();
        } catch (ServiceException e) {
            LOGGER.warn(NodeMessages.message(NodeMessageKey.LAUNCHER_RESET_DISPATCHER_SUPPORT_FAILED), e);
        }
    }

    private static void mayStartReset(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置选择性执行功能。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 获取程序中的 ThreadPoolTaskScheduler，用于处理计划任务。
        ThreadPoolTaskScheduler scheduler = ctx.getBean(ThreadPoolTaskScheduler.class);

        // 获取重置 QOS 服务。
        ResetQosService resetQosService = ctx.getBean(ResetQosService.class);

        // 判断重置处理器是否启动重置服务，并按条件执行不同的操作。
        long startResetDelay = launcherSettingHandler.getStartResetDelay();
        if (startResetDelay == 0) {
            LOGGER.info(NodeMessages.message(NodeMessageKey.LAUNCHER_STARTING_RESET_IMMEDIATELY));
            try {
                resetQosService.start();
            } catch (ServiceException e) {
                LOGGER.error(NodeMessages.message(NodeMessageKey.LAUNCHER_START_RESET_FAILED), e);
            }
        } else if (startResetDelay > 0) {
            LOGGER.info(NodeMessages.message(NodeMessageKey.LAUNCHER_STARTING_RESET_DELAYED, startResetDelay));
            scheduler.schedule(
                    () -> {
                        LOGGER.info(NodeMessages.message(NodeMessageKey.LAUNCHER_STARTING_RESET));
                        try {
                            resetQosService.start();
                        } catch (ServiceException e) {
                            LOGGER.error(NodeMessages.message(NodeMessageKey.LAUNCHER_START_RESET_FAILED), e);
                        }
                    },
                    Instant.ofEpochMilli(System.currentTimeMillis() + startResetDelay)
            );
        }
    }

    private static void mayOnlinePurge(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置选择性执行功能。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 获取程序中的 ThreadPoolTaskScheduler，用于处理计划任务。
        ThreadPoolTaskScheduler scheduler = ctx.getBean(ThreadPoolTaskScheduler.class);

        // 处理清除处理器的启动选项。
        PurgeQosService purgeQosService = ctx.getBean(PurgeQosService.class);

        // 清除处理器是否上线清除服务。
        long onlinePurgeDelay = launcherSettingHandler.getOnlinePurgeDelay();
        if (onlinePurgeDelay == 0) {
            LOGGER.info(NodeMessages.message(NodeMessageKey.LAUNCHER_ONLINE_PURGE_IMMEDIATELY));
            try {
                purgeQosService.online();
            } catch (ServiceException e) {
                LOGGER.error(NodeMessages.message(NodeMessageKey.LAUNCHER_ONLINE_PURGE_FAILED), e);
            }
        } else if (onlinePurgeDelay > 0) {
            LOGGER.info(NodeMessages.message(NodeMessageKey.LAUNCHER_ONLINE_PURGE_DELAYED, onlinePurgeDelay));
            scheduler.schedule(
                    () -> {
                        LOGGER.info(NodeMessages.message(NodeMessageKey.LAUNCHER_ONLINE_PURGE));
                        try {
                            purgeQosService.online();
                        } catch (ServiceException e) {
                            LOGGER.error(NodeMessages.message(NodeMessageKey.LAUNCHER_ONLINE_PURGE_FAILED), e);
                        }
                    },
                    Instant.ofEpochMilli(System.currentTimeMillis() + onlinePurgeDelay)
            );
        }
    }

    private static void mayEnablePurge(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置选择性执行功能。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 获取程序中的 ThreadPoolTaskScheduler，用于处理计划任务。
        ThreadPoolTaskScheduler scheduler = ctx.getBean(ThreadPoolTaskScheduler.class);

        // 处理清除处理器的启动选项。
        PurgeQosService purgeQosService = ctx.getBean(PurgeQosService.class);

        // 清除处理器是否启动清除服务。
        long enablePurgeDelay = launcherSettingHandler.getEnablePurgeDelay();
        if (enablePurgeDelay == 0) {
            LOGGER.info(NodeMessages.message(NodeMessageKey.LAUNCHER_STARTING_PURGE_IMMEDIATELY));
            try {
                purgeQosService.start();
            } catch (ServiceException e) {
                LOGGER.error(NodeMessages.message(NodeMessageKey.LAUNCHER_START_PURGE_FAILED), e);
            }
        } else if (enablePurgeDelay > 0) {
            LOGGER.info(NodeMessages.message(NodeMessageKey.LAUNCHER_STARTING_PURGE_DELAYED, enablePurgeDelay));
            scheduler.schedule(
                    () -> {
                        LOGGER.info(NodeMessages.message(NodeMessageKey.LAUNCHER_STARTING_PURGE));
                        try {
                            purgeQosService.start();
                        } catch (ServiceException e) {
                            LOGGER.error(NodeMessages.message(NodeMessageKey.LAUNCHER_START_PURGE_FAILED), e);
                        }
                    },
                    Instant.ofEpochMilli(System.currentTimeMillis() + enablePurgeDelay)
            );
        }
    }
}

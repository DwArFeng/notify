package com.dwarfeng.notify.node.launcher;

import com.dwarfeng.notify.node.handler.LauncherSettingHandler;
import com.dwarfeng.notify.stack.service.DispatcherSupportMaintainService;
import com.dwarfeng.notify.stack.service.ResetQosService;
import com.dwarfeng.notify.stack.service.RouterSupportMaintainService;
import com.dwarfeng.notify.stack.service.SenderSupportMaintainService;
import com.dwarfeng.springterminator.sdk.util.ApplicationUtil;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Date;

/**
 * 程序启动器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class Launcher {

    private final static Logger LOGGER = LoggerFactory.getLogger(Launcher.class);

    public static void main(String[] args) {
        ApplicationUtil.launch(new String[]{
                "classpath:spring/application-context*.xml",
                "file:opt/opt*.xml",
                "file:optext/opt*.xml"
        }, ctx -> {
            LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);
            // 判断是否重置路由器支持。
            if (launcherSettingHandler.isResetRouterSupport()) {
                LOGGER.info("重置路由器支持...");
                RouterSupportMaintainService maintainService = ctx.getBean(RouterSupportMaintainService.class);
                try {
                    maintainService.reset();
                } catch (ServiceException e) {
                    LOGGER.warn("路由器支持重置失败，异常信息如下", e);
                }
            }
            // 判断是否重置发送器支持。
            if (launcherSettingHandler.isResetSenderSupport()) {
                LOGGER.info("重置发送器支持...");
                SenderSupportMaintainService maintainService = ctx.getBean(SenderSupportMaintainService.class);
                try {
                    maintainService.reset();
                } catch (ServiceException e) {
                    LOGGER.warn("发送器支持重置失败，异常信息如下", e);
                }
            }
            // 判断是否重置调度器支持。
            if (launcherSettingHandler.isResetDispatcherSupport()) {
                LOGGER.info("重置发送器支持...");
                DispatcherSupportMaintainService maintainService = ctx.getBean(DispatcherSupportMaintainService.class);
                try {
                    maintainService.reset();
                } catch (ServiceException e) {
                    LOGGER.warn("发送器支持重置失败，异常信息如下", e);
                }
            }

            // 拿出程序中的 ThreadPoolTaskScheduler，用于处理计划任务。
            ThreadPoolTaskScheduler scheduler = ctx.getBean(ThreadPoolTaskScheduler.class);

            // 处理重置处理器的启动选项。
            ResetQosService resetQosService = ctx.getBean(ResetQosService.class);
            // 重置处理器是否启动重置服务。
            long startResetDelay = launcherSettingHandler.getStartResetDelay();
            if (startResetDelay == 0) {
                LOGGER.info("立即启动重置服务...");
                try {
                    resetQosService.start();
                } catch (ServiceException e) {
                    LOGGER.error("无法启动重置服务，异常原因如下", e);
                }
            } else if (startResetDelay > 0) {
                LOGGER.info(startResetDelay + " 毫秒后启动重置服务...");
                scheduler.schedule(
                        () -> {
                            LOGGER.info("启动重置服务...");
                            try {
                                resetQosService.start();
                            } catch (ServiceException e) {
                                LOGGER.error("无法启动重置服务，异常原因如下", e);
                            }
                        },
                        new Date(System.currentTimeMillis() + startResetDelay)
                );
            }
        });
    }
}

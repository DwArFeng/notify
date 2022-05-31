package com.dwarfeng.notify.node.launcher;

import com.dwarfeng.notify.node.handler.LauncherSettingHandler;
import com.dwarfeng.notify.stack.service.RouterSupportMaintainService;
import com.dwarfeng.notify.stack.service.SenderSupportMaintainService;
import com.dwarfeng.springterminator.sdk.util.ApplicationUtil;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        });
    }
}

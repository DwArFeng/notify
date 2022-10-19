package com.dwarfeng.notify.impl.handler.pusher;

import com.dwarfeng.notify.stack.bean.entity.SendHistory;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * 将信息输出至日志的推送器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
@Component
public class LogPusher extends AbstractPusher {

    public static final String PUSHER_TYPE = "log";

    private static final Logger LOGGER = LoggerFactory.getLogger(LogPusher.class);

    private static final String LEVEL_TRACE = "TRACE";
    private static final String LEVEL_DEBUG = "DEBUG";
    private static final String LEVEL_INFO = "INFO";
    private static final String LEVEL_WARN = "WARN";
    private static final String LEVEL_ERROR = "ERROR";

    @Value("${pusher.log.log_level}")
    private String logLevel;

    public LogPusher() {
        super(PUSHER_TYPE);
    }

    @Override
    public void notifySent(SendHistory sendHistory) throws HandlerException {
        String title = "通知被发送事件:";
        String message = Objects.toString(sendHistory);
        logData(title, message);
    }

    @Override
    public void notifySent(List<SendHistory> sendHistories) throws HandlerException {
        for (SendHistory sendHistory : sendHistories) {
            notifySent(sendHistory);
        }
    }

    private void logData(String title, String message) throws HandlerException {
        String logLevel = this.logLevel.toUpperCase();
        switch (logLevel) {
            case LEVEL_TRACE:
                LOGGER.trace(title);
                LOGGER.trace(message);
                return;
            case LEVEL_DEBUG:
                LOGGER.debug(title);
                LOGGER.debug(message);
                return;
            case LEVEL_INFO:
                LOGGER.info(title);
                LOGGER.info(message);
                return;
            case LEVEL_WARN:
                LOGGER.warn(title);
                LOGGER.warn(message);
                return;
            case LEVEL_ERROR:
                LOGGER.error(title);
                LOGGER.error(message);
                return;
            default:
                throw new HandlerException("未知的日志等级: " + logLevel);
        }
    }

    @Override
    public String toString() {
        return "LogPusher{" +
                "pusherType='" + pusherType + '\'' +
                ", logLevel='" + logLevel + '\'' +
                '}';
    }
}

package com.dwarfeng.notify.impl.handler.pusher;

import com.dwarfeng.notify.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.notify.impl.internal.i18n.ImplMessages;

import com.dwarfeng.notify.sdk.handler.pusher.AbstractPusher;
import com.dwarfeng.notify.stack.bean.dto.NotifyHistoryRecordInfo;
import com.dwarfeng.notify.stack.bean.dto.PurgeFinishedResult;
import com.dwarfeng.subgrade.basic.stack.exception.HandlerException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

    @Value("${com.dwarfeng.notify.pusher.log.log_level}")
    private String logLevel;

    public LogPusher() {
        super(PUSHER_TYPE);
    }

    @Override
    public void notifyHistoryRecorded(NotifyHistoryRecordInfo info) throws HandlerException {
        String title = ImplMessages.message(ImplMessageKey.NOTIFY_HISTORY_RECORDED_EVENT);
        String message = Objects.toString(info);
        logData(title, message);
    }

    @Override
    public void routeReset() throws HandlerException {
        String title = ImplMessages.message(ImplMessageKey.RESET_ROUTE_EVENT);
        String message = StringUtils.EMPTY;
        logData(title, message);
    }

    @Override
    public void dispatchReset() throws HandlerException {
        String title = ImplMessages.message(ImplMessageKey.RESET_DISPATCH_EVENT);
        String message = StringUtils.EMPTY;
        logData(title, message);
    }

    @Override
    public void sendReset() throws HandlerException {
        String title = ImplMessages.message(ImplMessageKey.RESET_SEND_EVENT);
        String message = StringUtils.EMPTY;
        logData(title, message);
    }

    @Override
    public void purgeFinished(PurgeFinishedResult result) throws HandlerException {
        String title = ImplMessages.message(ImplMessageKey.PURGE_COMPLETED_EVENT);
        String message = Objects.toString(result);
        logData(title, message);
    }

    @Override
    public void purgeFailed() throws HandlerException {
        String title = ImplMessages.message(ImplMessageKey.PURGE_FAILED_EVENT);
        String message = StringUtils.EMPTY;
        logData(title, message);
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
                throw new HandlerException(ImplMessages.message(ImplMessageKey.ERROR_UNKNOWN_LOG_LEVEL, logLevel));
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

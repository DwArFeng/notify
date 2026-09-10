package com.dwarfeng.notify.impl.handler.sender;

import com.dwarfeng.notify.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.notify.impl.internal.i18n.ImplMessages;

import com.dwarfeng.notify.sdk.handler.sender.AbstractSender;
import com.dwarfeng.notify.sdk.handler.sender.AbstractSenderRegistry;
import com.dwarfeng.notify.stack.exception.SenderException;
import com.dwarfeng.notify.stack.exception.SenderMakeException;
import com.dwarfeng.notify.stack.handler.Sender;
import com.dwarfeng.subgrade.basic.sdk.log.SingleLevelLoggerFactory;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.basic.stack.log.LogLevel;
import com.dwarfeng.subgrade.basic.stack.log.SingleLevelLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 日志发送器注册。
 *
 * @author DwArFeng
 * @since 1.0.1
 */
@Component
public class LogSenderRegistry extends AbstractSenderRegistry {

    public static final String SENDER_TYPE = "log_sender";
    public static final String LOG_LEVEL_DEBUG = "debug";
    public static final String LOG_LEVEL_INFO = "info";
    public static final String LOG_LEVEL_WARN = "warn";
    public static final String LOG_LEVEL_ERROR = "error";

    // 映射定义。
    private static final Map<String, LogLevel> LOG_LEVEL_MAP;

    // 初始化 LOG_LEVEL_MAP。
    static {
        Map<String, LogLevel> LOG_LEVEL_MAP_DEJA_VU = new LinkedHashMap<>();
        LOG_LEVEL_MAP_DEJA_VU.put(LOG_LEVEL_DEBUG, LogLevel.DEBUG);
        LOG_LEVEL_MAP_DEJA_VU.put(LOG_LEVEL_INFO, LogLevel.INFO);
        LOG_LEVEL_MAP_DEJA_VU.put(LOG_LEVEL_WARN, LogLevel.WARN);
        LOG_LEVEL_MAP_DEJA_VU.put(LOG_LEVEL_ERROR, LogLevel.ERROR);
        LOG_LEVEL_MAP = Collections.unmodifiableMap(LOG_LEVEL_MAP_DEJA_VU);
    }

    private final ApplicationContext ctx;

    public LogSenderRegistry(ApplicationContext ctx) {
        super(SENDER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "日志发送器";
    }

    @Override
    public String provideDescription() {
        return "将发送的内容打印在日志上";
    }

    @Override
    public String provideExampleParam() {
        StringJoiner stringJoiner = new StringJoiner(
                ", ", "", ImplMessages.message(ImplMessageKey.SENDER_LOG_OPTION_ANY)
        );
        LOG_LEVEL_MAP.keySet().forEach(stringJoiner::add);
        return stringJoiner.toString();
    }

    @Override
    public Sender makeSender(String type, String param) throws SenderException {
        try {
            if (Objects.isNull(param)) {
                throw new NullPointerException(ImplMessages.message(ImplMessageKey.ERROR_PARAM_NULL));
            }
            LogLevel logLevel = LOG_LEVEL_MAP.getOrDefault(param.toLowerCase(), null);
            if (Objects.isNull(logLevel)) {
                throw new IllegalArgumentException(ImplMessages.message(ImplMessageKey.ERROR_ILLEGAL_PARAM, param));
            }
            return ctx.getBean(LogSender.class, logLevel);
        } catch (Exception e) {
            throw new SenderMakeException(e, type, param);
        }
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class LogSender extends AbstractSender {

        private static final Logger LOGGER = LoggerFactory.getLogger(LogSender.class);

        private final SingleLevelLogger logger;

        public LogSender(LogLevel logLevel) {
            this.logger = SingleLevelLoggerFactory.newInstance(LOGGER, logLevel);
        }

        @Override
        public List<Response> send(
                ContextInfo contextInfo, Map<String, String> sendInfoMap, List<StringIdKey> userKeys
        ) {
            List<Response> responses = new ArrayList<>();
            for (StringIdKey userKey : userKeys) {
                logger.log(ImplMessages.message(ImplMessageKey.LOG_LOG_SENDER_SENDING, userKey, sendInfoMap));
                responses.add(new Response(userKey, true, ImplMessages.message(ImplMessageKey.SENDER_SEND_SUCCEEDED)));
            }
            return responses;
        }

        @Override
        public String toString() {
            return "LogSender{" +
                    "logger=" + logger +
                    '}';
        }
    }
}

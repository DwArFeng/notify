package com.dwarfeng.notify.impl.handler.sender;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.dutil.basic.io.StringOutputStream;
import com.dwarfeng.notify.sdk.handler.sender.AbstractSender;
import com.dwarfeng.notify.sdk.handler.sender.AbstractSenderRegistry;
import com.dwarfeng.notify.stack.exception.SenderException;
import com.dwarfeng.notify.stack.exception.SenderMakeException;
import com.dwarfeng.notify.stack.handler.Sender;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import groovy.lang.GroovyClassLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * Groovy 发送器注册。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
@Component
public class GroovySenderRegistry extends AbstractSenderRegistry {

    public static final String SENDER_TYPE = "groovy_sender";

    private static final Logger LOGGER = LoggerFactory.getLogger(GroovySenderRegistry.class);

    private final ApplicationContext ctx;

    public GroovySenderRegistry(ApplicationContext ctx) {
        super(SENDER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "Groovy 发送器";
    }

    @Override
    public String provideDescription() {
        return "通过自定义的 Groovy 脚本发送信息。";
    }

    @Override
    public String provideExampleParam() {
        try {
            Resource resource = ctx.getResource("classpath:groovy/ExampleSenderProcessor.groovy");
            String example;
            try (InputStream sin = resource.getInputStream();
                 StringOutputStream sout = new StringOutputStream(StandardCharsets.UTF_8, true)) {
                IOUtil.trans(sin, sout, 4096);
                sout.flush();
                example = sout.toString();
            }
            return example;
        } catch (Exception e) {
            LOGGER.warn("读取文件 classpath:groovy/ExampleSenderProcessor.groovy 时出现异常", e);
            return "";
        }
    }

    @Override
    public Sender makeSender(String type, String param) throws SenderException {
        try (GroovyClassLoader classLoader = new GroovyClassLoader()) {
            // 通过 Groovy 脚本生成处理器。
            Class<?> aClass = classLoader.parseClass(param);
            Processor processor = (Processor) aClass.newInstance();
            ctx.getAutowireCapableBeanFactory().autowireBean(processor);
            // 构建过滤器对象并返回。
            return ctx.getBean(GroovySender.class, processor);
        } catch (Exception e) {
            throw new SenderMakeException(e, type, param);
        }
    }

    @Override
    public String toString() {
        return "GroovySenderRegistry{" +
                "ctx=" + ctx +
                ", senderType='" + senderType + '\'' +
                '}';
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class GroovySender extends AbstractSender {

        private final Processor processor;

        public GroovySender(Processor processor) {
            this.processor = processor;
        }

        @Override
        public List<Response> send(
                ContextInfo contextInfo, Map<String, String> sendInfoMap, List<StringIdKey> userKeys)
                throws SenderException {
            return processor.send(contextInfo, sendInfoMap, userKeys);
        }

        @Override
        public String toString() {
            return "GroovySender{" +
                    "processor=" + processor +
                    '}';
        }
    }

    /**
     * Groovy 处理器。
     *
     * @author DwArFeng
     * @since 1.0.0
     */
    public interface Processor {

        /**
         * 发送操作。
         *
         * @param contextInfo 上下文信息。
         * @param sendInfo    发送信息。
         * @param userKeys    用户列表。
         * @return 发送响应组成的列表。
         * @throws SenderException 发送器异常。
         * @see #send(Sender.ContextInfo, Map, List)
         */
        List<Sender.Response> send(
                Sender.ContextInfo contextInfo, Map<String, String> sendInfo, List<StringIdKey> userKeys
        ) throws SenderException;
    }
}

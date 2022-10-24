package com.dwarfeng.notify.impl.handler.dispatcher;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.dutil.basic.io.StringOutputStream;
import com.dwarfeng.notify.stack.exception.DispatcherException;
import com.dwarfeng.notify.stack.exception.DispatcherMakeException;
import com.dwarfeng.notify.stack.handler.Dispatcher;
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

/**
 * Groovy调度器注册。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
@Component
public class GroovyDispatcherRegistry extends AbstractDispatcherRegistry {

    public static final String DISPATCHER_TYPE = "groovy_dispatcher";

    private static final Logger LOGGER = LoggerFactory.getLogger(GroovyDispatcherRegistry.class);

    private final ApplicationContext ctx;

    public GroovyDispatcherRegistry(ApplicationContext ctx) {
        super(DISPATCHER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "Groovy调度器";
    }

    @Override
    public String provideDescription() {
        return "通过自定义的 Groovy 脚本调度信息。";
    }

    @Override
    public String provideExampleParam() {
        try {
            Resource resource = ctx.getResource("classpath:groovy/ExampleDispatcherProcessor.groovy");
            String example;
            try (InputStream sin = resource.getInputStream();
                 StringOutputStream sout = new StringOutputStream(StandardCharsets.UTF_8, true)) {
                IOUtil.trans(sin, sout, 4096);
                sout.flush();
                example = sout.toString();
            }
            return example;
        } catch (Exception e) {
            LOGGER.warn("读取文件 classpath:groovy/ExampleDispatcherProcessor.groovy 时出现异常", e);
            return "";
        }
    }

    @Override
    public Dispatcher makeDispatcher(String type, String param) throws DispatcherException {
        try (GroovyClassLoader classLoader = new GroovyClassLoader()) {
            // 通过Groovy脚本生成处理器。
            Class<?> aClass = classLoader.parseClass(param);
            Processor processor = (Processor) aClass.newInstance();
            ctx.getAutowireCapableBeanFactory().autowireBean(processor);
            // 构建过滤器对象并返回。
            return ctx.getBean(GroovyDispatcher.class, processor);
        } catch (Exception e) {
            throw new DispatcherMakeException(e, type, param);
        }
    }

    @Override
    public String toString() {
        return "GroovyDispatcherRegistry{" +
                "ctx=" + ctx +
                ", dispatcherType='" + dispatcherType + '\'' +
                '}';
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class GroovyDispatcher implements Dispatcher {

        private final Processor processor;

        public GroovyDispatcher(Processor processor) {
            this.processor = processor;
        }

        @Override
        public List<StringIdKey> dispatchUser(String dispatchInfo, List<StringIdKey> userKeys, UserContext context)
                throws DispatcherException {
            return processor.dispatchUser(dispatchInfo, userKeys, context);
        }

        @Override
        public void dispatchResponse(String dispatchInfo, List<Sender.Response> responses, ResponseContext context)
                throws DispatcherException {
            processor.dispatchResponse(dispatchInfo, responses, context);
        }

        @Override
        public String toString() {
            return "GroovyDispatcher{" +
                    "processor=" + processor +
                    '}';
        }
    }

    /**
     * Groovy处理器。
     *
     * @author DwArFeng
     * @since 1.1.0
     */
    public interface Processor {

        /**
         * 用户调度操作。
         *
         * @param dispatchInfo 调度信息。
         * @param userKeys     用户空间。
         * @param context      上下文。
         * @return 调度返回的用户主键组成的列表。
         * @throws DispatcherException 调度器异常。
         * @see Dispatcher#dispatchUser(String, List, Dispatcher.UserContext)
         */
        List<StringIdKey> dispatchUser(String dispatchInfo, List<StringIdKey> userKeys, Dispatcher.UserContext context)
                throws DispatcherException;

        /**
         * 响应调度操作。
         *
         * @param dispatchInfo 调度信息。
         * @param responses    发送器响应结构体。
         * @param context      上下文。
         * @throws DispatcherException 调度器异常。
         * @see Dispatcher#dispatchResponse(String, List, Dispatcher.ResponseContext)
         */
        void dispatchResponse(String dispatchInfo, List<Sender.Response> responses, Dispatcher.ResponseContext context)
                throws DispatcherException;
    }
}

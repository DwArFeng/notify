package com.dwarfeng.notify.impl.handler.router;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.dutil.basic.io.StringOutputStream;
import com.dwarfeng.notify.sdk.handler.router.AbstractRouter;
import com.dwarfeng.notify.sdk.handler.router.AbstractRouterRegistry;
import com.dwarfeng.notify.stack.exception.RouterException;
import com.dwarfeng.notify.stack.exception.RouterMakeException;
import com.dwarfeng.notify.stack.handler.Router;
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
 * Groovy 路由器注册。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
@Component
public class GroovyRouterRegistry extends AbstractRouterRegistry {

    public static final String ROUTER_TYPE = "groovy_router";

    private static final Logger LOGGER = LoggerFactory.getLogger(GroovyRouterRegistry.class);

    private final ApplicationContext ctx;

    public GroovyRouterRegistry(ApplicationContext ctx) {
        super(ROUTER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "Groovy 路由器";
    }

    @Override
    public String provideDescription() {
        return "通过自定义的 Groovy 脚本解析路径。";
    }

    @Override
    public String provideExampleParam() {
        try {
            Resource resource = ctx.getResource("classpath:groovy/ExampleRouterProcessor.groovy");
            String example;
            try (InputStream sin = resource.getInputStream();
                 StringOutputStream sout = new StringOutputStream(StandardCharsets.UTF_8, true)) {
                IOUtil.trans(sin, sout, 4096);
                sout.flush();
                example = sout.toString();
            }
            return example;
        } catch (Exception e) {
            LOGGER.warn("读取文件 classpath:groovy/ExampleRouterProcessor.groovy 时出现异常", e);
            return "";
        }
    }

    @Override
    public Router makeRouter(String type, String param) throws RouterException {
        try (GroovyClassLoader classLoader = new GroovyClassLoader()) {
            // 通过 Groovy 脚本生成处理器。
            Class<?> aClass = classLoader.parseClass(param);
            Processor processor = (Processor) aClass.newInstance();
            ctx.getAutowireCapableBeanFactory().autowireBean(processor);
            // 构建过滤器对象并返回。
            return ctx.getBean(GroovyRouter.class, processor);
        } catch (Exception e) {
            throw new RouterMakeException(e, type, param);
        }
    }

    @Override
    public String toString() {
        return "GroovyRouterRegistry{" +
                "ctx=" + ctx +
                ", routerType='" + routerType + '\'' +
                '}';
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class GroovyRouter extends AbstractRouter {

        private final Processor processor;

        public GroovyRouter(Processor processor) {
            this.processor = processor;
        }

        @Override
        public List<StringIdKey> route(ContextInfo contextInfo, Map<String, String> routeInfoMap) throws RouterException {
            return processor.route(contextInfo, routeInfoMap);
        }

        @Override
        public String toString() {
            return "GroovyRouter{" +
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
         * 路由操作。
         *
         * @param contextInfo 上下文信息。
         * @param routeInfo   路由信息。
         * @return 路由返回的用户主键组成的列表。
         * @throws RouterException 路由器异常。
         * @see #route(Router.ContextInfo, Map)
         */
        List<StringIdKey> route(Router.ContextInfo contextInfo, Map<String, String> routeInfo) throws RouterException;
    }
}

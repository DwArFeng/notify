package com.dwarfeng.notify.impl.handler.router;

import com.dwarfeng.notify.stack.exception.RouterException;
import com.dwarfeng.notify.stack.exception.RouterMakeException;
import com.dwarfeng.notify.stack.handler.Router;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 空路由注册。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
@Component
public class EmptyRouterRegistry extends AbstractRouterRegistry {

    public static final String ROUTER_TYPE = "empty_router";

    private final ApplicationContext ctx;

    public EmptyRouterRegistry(ApplicationContext ctx) {
        super(ROUTER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "空路由器";
    }

    @Override
    public String provideDescription() {
        return "不返回任何用户的路由器";
    }

    @Override
    public String provideExampleParam() {
        return "";
    }

    @Override
    public Router makeRouter(String type, String param) throws RouterException {
        try {
            return ctx.getBean(EmptyRouter.class);
        } catch (Exception e) {
            throw new RouterMakeException(e, type, param);
        }
    }

    @Override
    public String toString() {
        return "EmptyRouterRegistry{" +
                "routerType='" + routerType + '\'' +
                '}';
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class EmptyRouter implements Router {

        @Override
        public List<StringIdKey> route(Map<String, String> routeInfoMap, Context context) {
            return Collections.emptyList();
        }

        @Override
        public String toString() {
            return "EmptyRouter{}";
        }
    }
}

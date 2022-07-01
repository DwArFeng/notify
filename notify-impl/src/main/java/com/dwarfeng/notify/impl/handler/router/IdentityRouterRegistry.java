package com.dwarfeng.notify.impl.handler.router;

import com.dwarfeng.notify.stack.bean.dto.Routing;
import com.dwarfeng.notify.stack.exception.RouterException;
import com.dwarfeng.notify.stack.exception.RouterExecutionException;
import com.dwarfeng.notify.stack.exception.RouterMakeException;
import com.dwarfeng.notify.stack.handler.Router;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 特定路由器注册。
 *
 * @author DwArFeng
 * @since 1.0.1
 */
@Component
public class IdentityRouterRegistry extends AbstractRouterRegistry {

    public static final String ROUTER_TYPE = "identity_router";

    private final ApplicationContext ctx;

    public IdentityRouterRegistry(ApplicationContext ctx) {
        super(ROUTER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "特定主题与用户路由器";
    }

    @Override
    public String provideDescription() {
        return "将 List<Routing> 作为 routerContext 传入路由器，路由器直接返回此结果。";
    }

    @Override
    public String provideExampleParam() {
        return "";
    }

    @Override
    public Router makeRouter(String type, String param) throws RouterException {
        try {
            return ctx.getBean(IdentityRouter.class);
        } catch (Exception e) {
            throw new RouterMakeException(e, type, param);
        }
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class IdentityRouter implements Router {

        @Override
        public List<Routing> parseRouting(Object context) throws RouterException {
            try {
                @SuppressWarnings("unchecked") List<Routing> result = (List<Routing>) context;
                return result;
            } catch (Exception e) {
                throw new RouterExecutionException(e);
            }
        }
    }
}


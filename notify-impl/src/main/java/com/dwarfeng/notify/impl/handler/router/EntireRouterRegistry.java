package com.dwarfeng.notify.impl.handler.router;

import com.dwarfeng.notify.stack.bean.entity.User;
import com.dwarfeng.notify.stack.exception.RouterException;
import com.dwarfeng.notify.stack.exception.RouterExecutionException;
import com.dwarfeng.notify.stack.exception.RouterMakeException;
import com.dwarfeng.notify.stack.handler.Router;
import com.dwarfeng.notify.stack.service.UserMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 全体路由注册。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class EntireRouterRegistry extends AbstractRouterRegistry {

    public static final String ROUTER_TYPE = "entire_router";

    private final ApplicationContext ctx;

    private final UserMaintainService userMaintainService;

    public EntireRouterRegistry(ApplicationContext ctx, UserMaintainService userMaintainService) {
        super(ROUTER_TYPE);
        this.ctx = ctx;
        this.userMaintainService = userMaintainService;
    }

    @Override
    public String provideLabel() {
        return "全体路由器";
    }

    @Override
    public String provideDescription() {
        return "将服务中的所有有效的人员作为路由结果（用户量大时慎用）";
    }

    @Override
    public String provideExampleParam() {
        return "";
    }

    @Override
    public Router makeRouter(String type, String param) throws RouterException {
        try {
            return ctx.getBean(EntireRouter.class, userMaintainService);
        } catch (Exception e) {
            throw new RouterMakeException(e, type, param);
        }
    }

    @Override
    public String toString() {
        return "EntireRouterRegistry{" +
                "routerType='" + routerType + '\'' +
                '}';
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class EntireRouter implements Router {

        private final UserMaintainService userMaintainService;

        public EntireRouter(UserMaintainService userMaintainService) {
            this.userMaintainService = userMaintainService;
        }

        @Override
        public List<StringIdKey> route(String routeInfo, Context context) throws RouterException {
            try {
                return userMaintainService.lookupAsList(UserMaintainService.ENABLED, new Object[0])
                        .stream().map(User::getKey).collect(Collectors.toList());
            } catch (ServiceException e) {
                throw new RouterExecutionException(e);
            }
        }

        @Override
        public String toString() {
            return "EntireRouter{" +
                    "userMaintainService=" + userMaintainService +
                    '}';
        }
    }
}

package com.dwarfeng.notify.impl.handler.router;

import com.dwarfeng.notify.stack.exception.RouterException;
import com.dwarfeng.notify.stack.exception.RouterMakeException;
import com.dwarfeng.notify.stack.handler.Router;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 本体路由器注册。
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
        return "本体路由器";
    }

    @Override
    public String provideDescription() {
        return "以 param 参数中的内容为分隔符（支持正则表达式），拆分 routeInfo，拆分的结果作为用户的 ID。";
    }

    @Override
    public String provideExampleParam() {
        return ",";
    }

    @Override
    public Router makeRouter(String type, String param) throws RouterException {
        try {
            return ctx.getBean(IdentityRouter.class, param);
        } catch (Exception e) {
            throw new RouterMakeException(e, type, param);
        }
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class IdentityRouter implements Router {

        private final String delimiter;

        public IdentityRouter(String delimiter) {
            this.delimiter = delimiter;
        }

        @Override
        public List<StringIdKey> route(String routeInfo, Context context) {
            if (Objects.isNull(routeInfo)) {
                return Collections.emptyList();
            }
            return Arrays.stream(routeInfo.split(delimiter)).map(StringIdKey::new).collect(Collectors.toList());
        }
    }
}


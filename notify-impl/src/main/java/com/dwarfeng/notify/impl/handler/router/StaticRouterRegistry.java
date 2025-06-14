package com.dwarfeng.notify.impl.handler.router;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.notify.sdk.handler.router.AbstractRouter;
import com.dwarfeng.notify.sdk.handler.router.AbstractRouterRegistry;
import com.dwarfeng.notify.stack.exception.RouterException;
import com.dwarfeng.notify.stack.exception.RouterExecutionException;
import com.dwarfeng.notify.stack.exception.RouterMakeException;
import com.dwarfeng.notify.stack.handler.Router;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 静态路由器注册。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
@Component
public class StaticRouterRegistry extends AbstractRouterRegistry {

    public static final String ROUTER_TYPE = "static_router";

    /**
     * 将指定的路由器参数转换为字符串。
     *
     * @param userKeys 指定的路由器参数。
     * @return 指定的参数转换成的字符串。
     */
    public static String stringifyParam(List<StringIdKey> userKeys) {
        List<FastJsonStringIdKey> fastJsonUserKeys = userKeys.stream().map(FastJsonStringIdKey::of)
                .collect(Collectors.toList());
        return JSON.toJSONString(fastJsonUserKeys, false);
    }

    /**
     * 从指定的字符串中解析路由器参数。
     *
     * @param string 指定的字符串。
     * @return 解析指定的字符串获取到的路由器参数。
     */
    public static List<StringIdKey> parseParam(String string) {
        List<FastJsonStringIdKey> fastJsonUserKeys = JSON.parseArray(string, FastJsonStringIdKey.class);
        return fastJsonUserKeys.stream().map(FastJsonStringIdKey::toStackBean).collect(Collectors.toList());
    }

    private final ApplicationContext ctx;

    public StaticRouterRegistry(ApplicationContext ctx) {
        super(ROUTER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "静态路由器";
    }

    @Override
    public String provideDescription() {
        return "将 param 解析为用户列表，并作为路由结果返回";
    }

    @Override
    public String provideExampleParam() {
        List<StringIdKey> userKeys = new ArrayList<>();
        userKeys.add(new StringIdKey("foobar.1"));
        userKeys.add(new StringIdKey("foobar.2"));
        userKeys.add(new StringIdKey("foobar.3"));
        return stringifyParam(userKeys);
    }

    @Override
    public Router makeRouter(String type, String param) throws RouterException {
        try {
            List<StringIdKey> userKeys = parseParam(param);
            return ctx.getBean(StaticRouter.class, userKeys);
        } catch (Exception e) {
            throw new RouterMakeException(e, type, param);
        }
    }

    @Override
    public String toString() {
        return "StaticRouterRegistry{" +
                "routerType='" + routerType + '\'' +
                '}';
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class StaticRouter extends AbstractRouter {

        private final List<StringIdKey> userKeys;

        public StaticRouter(List<StringIdKey> userKeys) {
            this.userKeys = userKeys;
        }

        @Override
        public List<StringIdKey> route(ContextInfo contextInfo, Map<String, String> routeInfoMap)
                throws RouterException {
            try {
                return context.filterUser(userKeys);
            } catch (RouterException e) {
                throw e;
            } catch (Exception e) {
                throw new RouterExecutionException(e);
            }
        }

        @Override
        public String toString() {
            return "StaticRouter{" +
                    "userKeys=" + userKeys +
                    '}';
        }
    }
}

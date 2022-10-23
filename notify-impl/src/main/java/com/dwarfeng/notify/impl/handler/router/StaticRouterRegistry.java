package com.dwarfeng.notify.impl.handler.router;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.notify.stack.exception.RouterException;
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
import java.util.stream.Collectors;

/**
 * 静态路由器注册。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class StaticRouterRegistry extends AbstractRouterRegistry {

    public static final String ROUTER_TYPE = "static_router";

    /**
     * 将指定的路由参数转换为参数。
     *
     * @param userKeys 指定的路由参数。
     * @return 指定的参数转换成的参数。
     */
    public static String toParam(List<StringIdKey> userKeys) {
        List<FastJsonStringIdKey> fastJsonUserKeys = userKeys.stream().map(FastJsonStringIdKey::of)
                .collect(Collectors.toList());
        return JSON.toJSONString(fastJsonUserKeys, false);
    }

    /**
     * 解析参数并获取路由参数。
     *
     * @param param 指定的参数。
     * @return 解析参数获取到的路由参数。
     */
    public static List<StringIdKey> parseParam(String param) {
        List<FastJsonStringIdKey> fastJsonUserKeys = JSON.parseArray(param, FastJsonStringIdKey.class);
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
        return toParam(userKeys);
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
    public static class StaticRouter implements Router {

        private final List<StringIdKey> userKeys;

        public StaticRouter(List<StringIdKey> userKeys) {
            this.userKeys = userKeys;
        }

        @Override
        public List<StringIdKey> route(String routeInfo, Context context) throws RouterException {
            return context.filterUser(userKeys);
        }

        @Override
        public String toString() {
            return "StaticRouter{" +
                    "userKeys=" + userKeys +
                    '}';
        }
    }
}

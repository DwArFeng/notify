package com.dwarfeng.notify.impl.handler.router;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.notify.stack.exception.RouterException;
import com.dwarfeng.notify.stack.exception.RouterMakeException;
import com.dwarfeng.notify.stack.handler.Router;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 本体路由器注册。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
@Component
public class IdentityRouterRegistry extends AbstractRouterRegistry {

    public static final String ROUTER_TYPE = "identity_router";

    /**
     * 将指定的路由参数转换为路由信息文本。
     *
     * @param userKeys 指定的路由参数。
     * @return 指定的参数转换成的路由信息文本。
     */
    public static String toRouteInfo(List<StringIdKey> userKeys) {
        List<FastJsonStringIdKey> fastJsonUserKeys = userKeys.stream().map(FastJsonStringIdKey::of)
                .collect(Collectors.toList());
        return JSON.toJSONString(fastJsonUserKeys, false);
    }

    /**
     * 解析路由信息文本并获取路由参数。
     *
     * @param routeInfo 指定的路由信息文本。
     * @return 解析路由信息文本获取到的路由参数。
     */
    public static List<StringIdKey> parseRouteInfo(String routeInfo) {
        List<FastJsonStringIdKey> fastJsonUserKeys = JSON.parseArray(routeInfo, FastJsonStringIdKey.class);
        return fastJsonUserKeys.stream().map(FastJsonStringIdKey::toStackBean).collect(Collectors.toList());
    }

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
        return "将 routeInfo 解析为用户列表，并作为路由结果返回。";
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

    @Override
    public String toString() {
        return "IdentityRouterRegistry{" +
                "routerType='" + routerType + '\'' +
                '}';
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class IdentityRouter implements Router {

        @Override
        public List<StringIdKey> route(String routeInfo, Context context) throws RouterException {
            if (StringUtils.isEmpty(routeInfo)) {
                return Collections.emptyList();
            }
            List<StringIdKey> userKeys = parseRouteInfo(routeInfo);
            return context.filterUser(userKeys);
        }

        @Override
        public String toString() {
            return "IdentityRouter{}";
        }
    }
}


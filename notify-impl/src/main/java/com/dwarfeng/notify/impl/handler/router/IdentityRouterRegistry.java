package com.dwarfeng.notify.impl.handler.router;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.sdk.handler.router.AbstractRouter;
import com.dwarfeng.notify.sdk.handler.router.AbstractRouterRegistry;
import com.dwarfeng.notify.stack.exception.RouterException;
import com.dwarfeng.notify.stack.exception.RouterExecutionException;
import com.dwarfeng.notify.stack.exception.RouterMakeException;
import com.dwarfeng.notify.stack.handler.Router;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
     * 将指定的路由器参数转换为字符串。
     *
     * @param config 指定的路由器参数。
     * @return 指定的参数转换成的字符串。
     */
    public static String stringifyParam(Config config) {
        return JSON.toJSONString(config, false);
    }

    /**
     * 从指定的字符串中解析路由器参数。
     *
     * @param string 指定的字符串。
     * @return 解析指定的字符串获取到的路由器参数。
     */
    public static Config parseParam(String string) {
        return JSON.parseObject(string, Config.class);
    }

    /**
     * 将指定的本体用户列表转换为字符串。
     *
     * @param identityUserList 指定的本体用户列表。
     * @return 指定的本体用户列表转换成的字符串。
     */
    @SuppressWarnings("JavaExistingMethodCanBeUsed")
    public static String stringifyIdentityUserList(List<StringIdKey> identityUserList) {
        List<FastJsonStringIdKey> fastJsonUserKeys = identityUserList.stream().map(FastJsonStringIdKey::of)
                .collect(Collectors.toList());
        return JSON.toJSONString(fastJsonUserKeys, false);
    }

    /**
     * 从指定的字符串中解析本体用户列表。
     *
     * @param string 指定的字符串。
     * @return 解析指定的字符串获取到的本体用户列表。
     */
    @SuppressWarnings("JavaExistingMethodCanBeUsed")
    public static List<StringIdKey> parseIdentityUserList(String string) {
        List<FastJsonStringIdKey> fastJsonUserKeys = JSON.parseArray(string, FastJsonStringIdKey.class);
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
        return "将 routeInfoMap 解析为用户列表，并作为路由结果返回。";
    }

    @Override
    public String provideExampleParam() {
        Config config = new Config("your-identity-user-list-key-here");
        return JSON.toJSONString(config, false);
    }

    @Override
    public Router makeRouter(String type, String param) throws RouterException {
        try {
            // 通过 param 生成路由器的参数。
            Config config = parseParam(param);

            // 通过 ctx 生成路由器。
            return ctx.getBean(IdentityRouter.class, config);
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
    public static class IdentityRouter extends AbstractRouter {

        private final Config config;

        public IdentityRouter(Config config) {
            this.config = config;
        }

        @Override
        public List<StringIdKey> route(ContextInfo contextInfo, Map<String, String> routeInfoMap)
                throws RouterException {
            try {
                // 获取本体用户列表的字符串形式。
                String identityUserListString = Optional.ofNullable(routeInfoMap)
                        .map(map -> map.get(config.getIdentityUserListKey())).orElse(StringUtils.EMPTY);

                // 解析本体用户列表字符串并返回结果。
                if (StringUtils.isEmpty(identityUserListString)) {
                    return Collections.emptyList();
                }
                List<StringIdKey> userKeys = parseIdentityUserList(identityUserListString);
                return context.filterUser(userKeys);
            } catch (RouterException e) {
                throw e;
            } catch (Exception e) {
                throw new RouterExecutionException(e);
            }
        }

        @Override
        public String toString() {
            return "IdentityRouter{}";
        }
    }

    public static class Config implements Bean {

        private static final long serialVersionUID = -409012282137963853L;

        @JSONField(name = "identity_user_list_key", ordinal = 1)
        private String identityUserListKey;

        public Config() {
        }

        public Config(String identityUserListKey) {
            this.identityUserListKey = identityUserListKey;
        }

        public String getIdentityUserListKey() {
            return identityUserListKey;
        }

        public void setIdentityUserListKey(String identityUserListKey) {
            this.identityUserListKey = identityUserListKey;
        }

        @Override
        public String toString() {
            return "Config{" +
                    "identityUserListKey='" + identityUserListKey + '\'' +
                    '}';
        }
    }
}


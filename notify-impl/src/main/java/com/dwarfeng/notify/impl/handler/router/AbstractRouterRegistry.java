package com.dwarfeng.notify.impl.handler.router;

/**
 * 抽象路由器注册。
 *
 * @author DwArFeng
 * @see com.dwarfeng.notify.sdk.handler.router.AbstractRouterRegistry
 * @since 1.0.0
 * @deprecated 该对象已经被废弃，请使用 sdk 模块下的对应对象代替。
 */
@Deprecated
public abstract class AbstractRouterRegistry extends com.dwarfeng.notify.sdk.handler.router.AbstractRouterRegistry {

    public AbstractRouterRegistry() {
    }

    public AbstractRouterRegistry(String routerType) {
        super(routerType);
    }
}

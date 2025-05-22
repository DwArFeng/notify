package com.dwarfeng.notify.sdk.handler.router;

import com.dwarfeng.notify.sdk.handler.RouterMaker;
import com.dwarfeng.notify.sdk.handler.RouterSupporter;

import java.util.Objects;

/**
 * 抽象路由器注册。
 *
 * @author DwArFeng
 * @since 1.5.0
 */
public abstract class AbstractRouterRegistry implements RouterMaker, RouterSupporter {

    protected String routerType;

    public AbstractRouterRegistry() {
    }

    public AbstractRouterRegistry(String routerType) {
        this.routerType = routerType;
    }

    @Override
    public boolean supportType(String type) {
        return Objects.equals(routerType, type);
    }

    @Override
    public String provideType() {
        return routerType;
    }

    public String getRouterType() {
        return routerType;
    }

    public void setRouterType(String routerType) {
        this.routerType = routerType;
    }

    @Override
    public String toString() {
        return "AbstractRouterRegistry{" +
                "routerType='" + routerType + '\'' +
                '}';
    }
}

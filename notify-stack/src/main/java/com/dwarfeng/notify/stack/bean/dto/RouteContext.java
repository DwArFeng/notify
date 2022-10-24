package com.dwarfeng.notify.stack.bean.dto;

import com.dwarfeng.notify.stack.bean.entity.RouterInfo;
import com.dwarfeng.notify.stack.handler.Router;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 路由上下文。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class RouteContext implements Dto {

    private static final long serialVersionUID = -483339700140592315L;

    private RouterInfo routerInfo;
    private Router router;

    public RouteContext() {
    }

    public RouteContext(RouterInfo routerInfo, Router router) {
        this.routerInfo = routerInfo;
        this.router = router;
    }

    public RouterInfo getRouterInfo() {
        return routerInfo;
    }

    public void setRouterInfo(RouterInfo routerInfo) {
        this.routerInfo = routerInfo;
    }

    public Router getRouter() {
        return router;
    }

    public void setRouter(Router router) {
        this.router = router;
    }

    @Override
    public String toString() {
        return "RouteContext{" +
                "routerInfo=" + routerInfo +
                ", router=" + router +
                '}';
    }
}

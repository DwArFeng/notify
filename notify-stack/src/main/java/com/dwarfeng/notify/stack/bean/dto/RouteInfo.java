package com.dwarfeng.notify.stack.bean.dto;

import com.dwarfeng.notify.stack.handler.Router;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 路由信息。
 *
 * @author DwArFeng
 * @since 1.2.2
 */
public class RouteInfo implements Dto {

    private static final long serialVersionUID = 5962181248195966794L;

    private String type;
    private Router router;

    public RouteInfo() {
    }

    public RouteInfo(String type, Router router) {
        this.type = type;
        this.router = router;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Router getRouter() {
        return router;
    }

    public void setRouter(Router router) {
        this.router = router;
    }

    @Override
    public String toString() {
        return "RouteInfo{" +
                "type='" + type + '\'' +
                ", router=" + router +
                '}';
    }
}

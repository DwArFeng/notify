package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.stack.exception.RouterException;
import com.dwarfeng.notify.stack.exception.UnsupportedRouterTypeException;
import com.dwarfeng.notify.stack.handler.Router;
import com.dwarfeng.notify.stack.handler.RouterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RouterHandlerImpl implements RouterHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouterHandlerImpl.class);

    @Autowired(required = false)
    private List<RouterMaker> routerMakers = new ArrayList<>();

    @Override
    public Router make(String type, String param) throws RouterException {
        try {
            // 生成路由器。
            LOGGER.debug("通过路由器信息构建新的的路由器...");
            RouterMaker routerMaker = routerMakers.stream().filter(maker -> maker.supportType(type))
                    .findFirst().orElseThrow(() -> new UnsupportedRouterTypeException(type));
            Router router = routerMaker.makeRouter(type, param);
            LOGGER.debug("路由器构建成功!");
            LOGGER.debug("路由器: " + router);
            return router;
        } catch (RouterException e) {
            throw e;
        } catch (Exception e) {
            throw new RouterException(e);
        }
    }
}

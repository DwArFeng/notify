package com.dwarfeng.notify.sdk.handler.router;

import com.dwarfeng.notify.stack.handler.Router;

/**
 * 路由器的抽象实现。
 *
 * @author DwArFeng
 * @since 1.5.0
 */
public abstract class AbstractRouter implements Router {

    protected Context context;

    @Override
    public void init(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "AbstractRouter{" +
                "context=" + context +
                '}';
    }
}

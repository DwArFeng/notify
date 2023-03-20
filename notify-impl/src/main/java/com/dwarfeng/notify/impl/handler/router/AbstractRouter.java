package com.dwarfeng.notify.impl.handler.router;

import com.dwarfeng.notify.stack.handler.Router;

/**
 * 路由器的抽象实现。
 *
 * @author DwArFeng
 * @since 1.4.0
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

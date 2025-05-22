package com.dwarfeng.notify.sdk.handler.dispatcher;

import com.dwarfeng.notify.stack.handler.Dispatcher;

/**
 * 分配器的抽象实现。
 *
 * @author DwArFeng
 * @since 1.5.0
 */
public abstract class AbstractDispatcher implements Dispatcher {

    protected Context context;

    @Override
    public void init(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "AbstractDispatcher{" +
                "context=" + context +
                '}';
    }
}

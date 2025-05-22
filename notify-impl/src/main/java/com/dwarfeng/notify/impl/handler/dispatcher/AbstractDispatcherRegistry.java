package com.dwarfeng.notify.impl.handler.dispatcher;

/**
 * 抽象调度器注册。
 *
 * @author DwArFeng
 * @see com.dwarfeng.notify.sdk.handler.dispatcher.AbstractDispatcherRegistry
 * @since 1.1.0
 * @deprecated 该对象已经被废弃，请使用 sdk 模块下的对应对象代替。
 */
@Deprecated
public abstract class AbstractDispatcherRegistry extends
        com.dwarfeng.notify.sdk.handler.dispatcher.AbstractDispatcherRegistry {

    public AbstractDispatcherRegistry() {
    }

    public AbstractDispatcherRegistry(String dispatcherType) {
        super(dispatcherType);
    }
}

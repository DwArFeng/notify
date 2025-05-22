package com.dwarfeng.notify.impl.handler.sender;

/**
 * 抽象发送器注册。
 *
 * @author DwArFeng
 * @see com.dwarfeng.notify.sdk.handler.sender.AbstractSenderRegistry
 * @since 1.0.0
 * @deprecated 该对象已经被废弃，请使用 sdk 模块下的对应对象代替。
 */
@Deprecated
public abstract class AbstractSenderRegistry extends com.dwarfeng.notify.sdk.handler.sender.AbstractSenderRegistry {

    public AbstractSenderRegistry() {
    }

    public AbstractSenderRegistry(String senderType) {
        super(senderType);
    }
}

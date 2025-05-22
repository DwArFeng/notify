package com.dwarfeng.notify.impl.handler.pusher;

/**
 * 推送器适配器。
 *
 * <p>
 * 该类是一个适配器，对所有的事件推送方法均进行空实现。
 *
 * <p>
 * 所有的插件实现推送器时都建议继承该类，这样做的好处是：
 * <ul>
 *     <li>适配器对所有的事件推送方法均进行空实现，因此插件实现推送器时只需要重写需要的方法即可。</li>
 *     <li>当服务版本升级，增加新事件时，旧的推送器实现无须增加代码，因为新的事件推送方法在适配器中已经有了空实现。</li>
 * </ul>
 *
 * @author DwArFeng
 * @see com.dwarfeng.notify.sdk.handler.pusher.PusherAdapter
 * @since 1.4.2
 * @deprecated 该对象已经被废弃，请使用 sdk 模块下的对应对象代替。
 */
@Deprecated
public abstract class PusherAdapter extends com.dwarfeng.notify.sdk.handler.pusher.PusherAdapter {

    public PusherAdapter() {
    }

    public PusherAdapter(String pusherType) {
        super(pusherType);
    }
}

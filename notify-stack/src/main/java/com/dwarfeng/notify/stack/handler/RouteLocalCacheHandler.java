package com.dwarfeng.notify.stack.handler;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 路由本地缓存处理器。
 *
 * <p>处理器在本地保存数据，缓存中的数据可以保证与数据源保持同步。
 * <p>数据存放在本地，必要时才与数据访问层通信，这有助于程序效率的提升。
 * <p>该处理器线程安全。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface RouteLocalCacheHandler extends Handler {

    /**
     * 获取指定主键对应的路由器。
     *
     * @param routerInfoKey 指定的主键。
     * @return 指定部件的评估上下文，如果主键对应的路由器信息不存在，则返回 null。
     * @throws HandlerException 处理器异常。
     */
    Router getRouter(LongIdKey routerInfoKey) throws HandlerException;

    /**
     * 清除本地缓存。
     *
     * @throws HandlerException 处理器异常。
     */
    void clear() throws HandlerException;
}

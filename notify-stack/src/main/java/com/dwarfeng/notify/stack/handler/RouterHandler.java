package com.dwarfeng.notify.stack.handler;

import com.dwarfeng.notify.stack.exception.RouterException;

/**
 * 路由器处理器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface RouterHandler {

    /**
     * 根据指定的判断器信息构造一个判断器。
     *
     * @param type  路由器类型。
     * @param param 路由器参数。
     * @return 构造的判断器。
     * @throws RouterException 路由器异常。
     */
    Router make(String type, String param) throws RouterException;
}

package com.dwarfeng.notify.stack.handler;

import com.dwarfeng.notify.stack.bean.dto.Routing;
import com.dwarfeng.notify.stack.exception.RouterException;

import java.util.List;

/**
 * 路由器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface Router {

    /**
     * 解析路径。
     *
     * @param context 上下文对象。
     * @return 解析的路径组成的列表。
     * @throws RouterException 路由器异常。
     */
    List<Routing> parseRouting(Object context) throws RouterException;
}

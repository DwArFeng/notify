package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.stack.exception.RouterException;
import com.dwarfeng.notify.stack.handler.Router;

/**
 * 路由器构造器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface RouterMaker {

    /**
     * 返回制造器是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 制造器是否支持指定的类型。
     */
    boolean supportType(String type);

    /**
     * 根据指定的路由器信息生成一个路由器对象。
     *
     * <p>
     * 可以保证传入的路由器信息中的类型是支持的。
     *
     * @param type  路由器类型。
     * @param param 路由器参数。
     * @return 制造出的路由器。
     * @throws RouterException 路由器异常。
     */
    Router makeRouter(String type, String param) throws RouterException;
}

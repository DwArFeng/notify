package com.dwarfeng.notify.stack.handler;

import com.dwarfeng.notify.stack.exception.DispatcherException;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

import java.util.List;

/**
 * 调度器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface Dispatcher {

    /**
     * 向指定的用户调度信息。
     *
     * @param userKey 指定的用户主键。
     * @param context 上下文。
     * @throws DispatcherException 调度器异常。
     */
    void dispatch(StringIdKey userKey, Object context) throws DispatcherException;

    /**
     * 向一批用户调度信息。
     *
     * @param userKeys 指定的用户组成的列表。
     * @param context  上下文。
     * @throws DispatcherException 调度器异常
     */
    default void batchDispatch(List<StringIdKey> userKeys, Object context) throws DispatcherException {
        for (StringIdKey userKey : userKeys) {
            dispatch(userKey, context);
        }
    }
}

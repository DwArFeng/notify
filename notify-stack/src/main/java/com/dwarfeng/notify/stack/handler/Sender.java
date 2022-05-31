package com.dwarfeng.notify.stack.handler;

import com.dwarfeng.notify.stack.exception.SenderException;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

import java.util.List;

/**
 * 发送器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface Sender {

    /**
     * 向指定的用户发送信息。
     *
     * @param userKey 指定的用户主键。
     * @param context 上下文。
     * @throws SenderException 发送器异常。
     */
    void send(StringIdKey userKey, Object[] context) throws SenderException;

    /**
     * 向一批用户发送信息。
     *
     * @param userKeys 指定的用户组成的列表。
     * @param context  上下文。
     * @throws SenderException 发送器异常
     */
    default void batchSend(List<StringIdKey> userKeys, Object[] context) throws SenderException {
        for (StringIdKey userKey : userKeys) {
            send(userKey, context);
        }
    }
}

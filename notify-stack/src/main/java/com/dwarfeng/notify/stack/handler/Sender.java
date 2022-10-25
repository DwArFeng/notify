package com.dwarfeng.notify.stack.handler;

import com.dwarfeng.notify.stack.exception.SenderException;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

import java.util.List;

/**
 * 发送器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface Sender {

    /**
     * 发送操作。
     *
     * <p>
     * 实现发送操作时，需要注意以下规约：
     * <ol type="1">
     * <li>
     * <code>userKeys</code> 只可读不可写，禁止以任何形式对 <code>userKeys</code> 以及其中的元素进行更改操作。
     * </li>
     * <li>
     * 返回的发送响应组成的列表的长度必须与 <code>userKeys</code> 的长度相等，
     * 且发送响应的字段 {@link Response#userKey} 必须与 <code>userKeys</code> 相对应（数据一致且顺序相同）。
     * </li>
     * </ol>
     *
     * @param sendInfo 发送信息。
     * @param userKeys 用户列表。
     * @param context  上下文。
     * @return 发送响应组成的列表。
     * @throws SenderException 发送器异常。
     */
    List<Response> send(String sendInfo, List<StringIdKey> userKeys, Context context) throws SenderException;

    /**
     * 响应结构体。
     *
     * @author DwArFeng
     * @since 1.1.0
     */
    class Response {

        private final StringIdKey userKey;
        private final boolean succeedFlag;
        private final String message;

        public Response(StringIdKey userKey, boolean succeedFlag, String message) {
            this.userKey = userKey;
            this.succeedFlag = succeedFlag;
            this.message = message;
        }

        public StringIdKey getUserKey() {
            return userKey;
        }

        public boolean isSucceedFlag() {
            return succeedFlag;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "userKey=" + userKey +
                    ", succeedFlag=" + succeedFlag +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

    /**
     * 上下文。
     *
     * @author DwArFeng
     * @since 1.1.0
     */
    interface Context {

        /**
         * 获取本次通知操作相关的通知设置。
         *
         * @return 通知设置的主键。
         * @throws SenderException 发送器异常。
         */
        LongIdKey getNotifySettingKey() throws SenderException;

        /**
         * 获取本次通知操作相关的主题。
         *
         * @return 主题的主键。
         * @throws SenderException 发送器异常。
         */
        StringIdKey getTopicKey() throws SenderException;

        /**
         * 查询指定的元数据是否存在。
         *
         * @param userKey 元数据用户的主键。
         * @param metaId  元数据的 ID。
         * @return 指定的元数据是否存在。
         * @throws SenderException 发送器异常。
         */
        boolean existsMeta(StringIdKey userKey, String metaId) throws SenderException;

        /**
         * 获取指定的元数据的值。
         *
         * <p>
         * 如果指定的元数据值不存在，则返回 null。
         *
         * @param userKey 元数据用户的主键。
         * @param metaId  元数据的 ID。
         * @return 指定的元数据的值。
         * @throws SenderException 发送器异常。
         */
        String getMeta(StringIdKey userKey, String metaId) throws SenderException;

        /**
         * 获取指定的元数据的默认值。
         *
         * <p>
         * 如果指定的元数据的默认值不存在，则返回 null。
         *
         * @param metaId 元数据的 ID。
         * @return 指定的元数据的默认值。
         * @throws SenderException 发送器异常。
         */
        String getDefaultMeta(String metaId) throws SenderException;

        /**
         * 获取指定的元数据的值或默认值。
         *
         * <p>
         * 如果指定的元数据的值存在，则放回元数据值；否则，返回指定的元数据的默认值；如果默认值也不存在则返回 null。
         *
         * @param userKey 元数据用户的主键。
         * @param metaId  元数据的 ID。
         * @return 指定的元数据的值或默认值。
         * @throws SenderException 发送器异常。
         */
        default String getMetaOrDefault(StringIdKey userKey, String metaId) throws SenderException {
            if (existsMeta(userKey, metaId)) {
                return getMeta(userKey, metaId);
            } else {
                return getDefaultMeta(metaId);
            }
        }

        /**
         * 设置指定的元数据的值。
         *
         * @param userKey 元数据用户的主键。
         * @param metaId  元数据的 ID。
         * @param value   元数据的新值。
         * @throws SenderException 发送器异常。
         */
        void putMeta(StringIdKey userKey, String metaId, String value) throws SenderException;
    }
}

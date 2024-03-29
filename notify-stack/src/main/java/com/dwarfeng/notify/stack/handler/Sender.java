package com.dwarfeng.notify.stack.handler;

import com.dwarfeng.notify.stack.exception.SenderException;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

import java.util.List;
import java.util.Map;

/**
 * 发送器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface Sender {

    /**
     * 初始化发送器。
     *
     * <p>
     * 该方法会在发送器初始化后调用，请将 context 存放在发送器的字段中。<br>
     * 当发送器被触发后，执行上下文中的相应方法即可。
     *
     * @param context 发送器的上下文。
     */
    void init(Context context);

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
     * @param contextInfo 上下文信息。
     * @param sendInfoMap 发送信息映射。
     * @param userKeys    用户列表。
     * @return 发送响应组成的列表。
     * @throws SenderException 发送器异常。
     */
    List<Response> send(ContextInfo contextInfo, Map<String, String> sendInfoMap, List<StringIdKey> userKeys)
            throws SenderException;

    /**
     * 响应结构体。
     *
     * @author DwArFeng
     * @since 1.1.0
     */
    final class Response {

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
         * 查询指定的元数据是否存在。
         *
         * @param notifySettingKey 通知设置的主键。
         * @param topicKey         元数据主题的主键。
         * @param userKey          元数据用户的主键。
         * @param metaId           元数据的 ID。
         * @return 指定的元数据是否存在。
         * @throws Exception 调用过程中发生的任何异常。
         */
        boolean existsMeta(LongIdKey notifySettingKey, StringIdKey topicKey, StringIdKey userKey, String metaId)
                throws Exception;

        /**
         * 获取指定的元数据的值。
         *
         * <p>
         * 如果指定的元数据值不存在，则返回 null。
         *
         * @param notifySettingKey 通知设置的主键。
         * @param topicKey         元数据主题的主键。
         * @param userKey          元数据用户的主键。
         * @param metaId           元数据的 ID。
         * @return 指定的元数据的值。
         * @throws Exception 调用过程中发生的任何异常。
         */
        String getMeta(LongIdKey notifySettingKey, StringIdKey topicKey, StringIdKey userKey, String metaId)
                throws Exception;

        /**
         * 获取指定的元数据的默认值。
         *
         * <p>
         * 如果指定的元数据的默认值不存在，则返回 null。
         *
         * @param notifySettingKey 通知设置的主键。
         * @param topicKey         元数据主题的主键。
         * @param metaId           元数据的 ID。
         * @return 指定的元数据的默认值。
         * @throws Exception 调用过程中发生的任何异常。
         */
        String getDefaultMeta(LongIdKey notifySettingKey, StringIdKey topicKey, String metaId) throws Exception;

        /**
         * 获取指定的元数据的值或默认值。
         *
         * <p>
         * 如果指定的元数据的值存在，则放回元数据值；否则，返回指定的元数据的默认值；如果默认值也不存在则返回 null。
         *
         * @param notifySettingKey 通知设置的主键。
         * @param topicKey         元数据主题的主键。
         * @param userKey          元数据用户的主键。
         * @param metaId           元数据的 ID。
         * @return 指定的元数据的值或默认值。
         * @throws Exception 调用过程中发生的任何异常。
         */
        default String getMetaOrDefault(
                LongIdKey notifySettingKey, StringIdKey topicKey, StringIdKey userKey, String metaId
        ) throws Exception {
            if (existsMeta(notifySettingKey, topicKey, userKey, metaId)) {
                return getMeta(notifySettingKey, topicKey, userKey, metaId);
            } else {
                return getDefaultMeta(notifySettingKey, topicKey, metaId);
            }
        }

        /**
         * 设置指定的元数据的值。
         *
         * @param notifySettingKey 通知设置的主键。
         * @param topicKey         元数据主题的主键。
         * @param userKey          元数据用户的主键。
         * @param metaId           元数据的 ID。
         * @param value            元数据的新值。
         * @throws Exception 调用过程中发生的任何异常。
         */
        void putMeta(
                LongIdKey notifySettingKey, StringIdKey topicKey, StringIdKey userKey, String metaId, String value
        ) throws Exception;
    }

    /**
     * 上下文信息。
     *
     * @author DwArFeng
     * @since 1.4.0
     */
    final class ContextInfo {

        private final LongIdKey notifySettingKey;
        private final StringIdKey topicKey;

        public ContextInfo(LongIdKey notifySettingKey, StringIdKey topicKey) {
            this.notifySettingKey = notifySettingKey;
            this.topicKey = topicKey;
        }

        public LongIdKey getNotifySettingKey() {
            return notifySettingKey;
        }

        public StringIdKey getTopicKey() {
            return topicKey;
        }

        @Override
        public String toString() {
            return "ContextInfo{" +
                    "notifySettingKey=" + notifySettingKey +
                    ", topicKey=" + topicKey +
                    '}';
        }
    }
}

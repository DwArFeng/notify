package com.dwarfeng.notify.stack.handler;

import com.dwarfeng.notify.stack.exception.DispatcherException;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

import java.util.List;
import java.util.Map;

/**
 * 调度器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface Dispatcher {

    /**
     * 初始化调度器。
     *
     * <p>
     * 该方法会在调度器初始化后调用，请将 context 存放在调度器的字段中。<br>
     * 当调度器被触发后，执行上下文中的相应方法即可。
     *
     * @param context 调度器的上下文。
     */
    void init(Context context);

    /**
     * 调度操作。
     *
     * <p>
     * 调度操作需要在给定的用户空间 <code>userKeys</code> 中决定具体的调度用户。<br>
     * 通常，用户的元数据和过去的调度信息会决定是否需要调度指定的用户，这些信息可以在 <code>context</code> 中获取。
     *
     * <p>
     * 实现调度操作时，需要注意以下规约：
     * <ol type="1">
     * <li>
     * <code>userKeys</code> 只可读不可写，禁止以任何形式对 <code>userKeys</code> 以及其中的元素进行更改操作。
     * </li>
     * <li>
     * 返回的结果必须是 <code>userKeys</code> 的子集，结果可以与 <code>userKeys</code>，
     * 不能含有 <code>userKeys</code> 不存在的元素。
     * </li>
     * </ol>
     *
     * @param contextInfo     上下文信息。
     * @param dispatchInfoMap 调度信息映射。
     * @param userKeys        用户空间。
     * @return 调度返回的用户主键组成的列表。
     * @throws DispatcherException 调度器异常。
     */
    List<StringIdKey> dispatch(
            ContextInfo contextInfo, Map<String, String> dispatchInfoMap, List<StringIdKey> userKeys
    ) throws DispatcherException;

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

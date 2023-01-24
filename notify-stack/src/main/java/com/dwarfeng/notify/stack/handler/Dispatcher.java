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
     * <p>
     * 如果无法确定用户的规范性，可以调用 {@link Context#filterUser(List)} 方法筛选出一组用户中符合要求的用户。<br>
     * 这个操作会有额外的性能开销，如果有其它的途径保证返回结果的规范性
     * （尤其是服务集成在一个工程中，与其它服务组合使用时），则不需要调用此方法。
     *
     * @param dispatchInfoMap 调度信息映射。
     * @param userKeys        用户空间。
     * @param context         上下文。
     * @return 调度返回的用户主键组成的列表。
     * @throws DispatcherException 调度器异常。
     */
    List<StringIdKey> dispatch(Map<String, String> dispatchInfoMap, List<StringIdKey> userKeys, Context context)
            throws DispatcherException;

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
         * @throws DispatcherException 调度器异常。
         */
        LongIdKey getNotifySettingKey() throws DispatcherException;

        /**
         * 获取本次通知操作相关的主题。
         *
         * @return 主题的主键。
         * @throws DispatcherException 调度器异常。
         */
        StringIdKey getTopicKey() throws DispatcherException;

        /**
         * 查询指定的元数据是否存在。
         *
         * @param userKey 元数据用户的主键。
         * @param metaId  元数据的 ID。
         * @return 指定的元数据是否存在。
         * @throws DispatcherException 调度器异常。
         */
        boolean existsMeta(StringIdKey userKey, String metaId)
                throws DispatcherException;

        /**
         * 获取指定的元数据的值。
         *
         * <p>
         * 如果指定的元数据值不存在，则返回 null。
         *
         * @param userKey 元数据用户的主键。
         * @param metaId  元数据的 ID。
         * @return 指定的元数据的值。
         * @throws DispatcherException 调度器异常。
         */
        String getMeta(StringIdKey userKey, String metaId) throws DispatcherException;

        /**
         * 获取指定的元数据的默认值。
         *
         * <p>
         * 如果指定的元数据的默认值不存在，则返回 null。
         *
         * @param metaId 元数据的 ID。
         * @return 指定的元数据的默认值。
         * @throws DispatcherException 调度器异常。
         */
        String getDefaultMeta(String metaId) throws DispatcherException;

        /**
         * 获取指定的元数据的值或默认值。
         *
         * <p>
         * 如果指定的元数据的值存在，则放回元数据值；否则，返回指定的元数据的默认值；如果默认值也不存在则返回 null。
         *
         * @param userKey 元数据用户的主键。
         * @param metaId  元数据的 ID。
         * @return 指定的元数据的值或默认值。
         * @throws DispatcherException 调度器异常。
         */
        default String getMetaOrDefault(StringIdKey userKey, String metaId) throws DispatcherException {
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
         * @throws DispatcherException 调度器异常。
         */
        void putMeta(StringIdKey userKey, String metaId, String value) throws DispatcherException;

        /**
         * 过滤用户。
         *
         * <p>
         * 该方法接收一个任意的用户列表，遍历并获取其中所有符合规范的用户，将其组合成列表并返回。
         *
         * @param userKeys 任意的用户列表。
         * @return 任意列表中符合规范的用户组成的子列表。
         * @throws DispatcherException 调度器异常。
         */
        List<StringIdKey> filterUser(List<StringIdKey> userKeys) throws DispatcherException;
    }
}

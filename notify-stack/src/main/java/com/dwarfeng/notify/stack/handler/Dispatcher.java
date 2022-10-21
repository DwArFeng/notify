package com.dwarfeng.notify.stack.handler;

import com.dwarfeng.notify.stack.exception.DispatcherException;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

import java.util.List;

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
     * 通常，用户的偏好和过去的调度信息会决定是否需要调度指定的用户，这些信息可以在 <code>context</code> 中获取。
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
     * @param dispatchInfo 调度信息。
     * @param userKeys     用户空间。
     * @param context      上下文。
     * @return 调度返回的用户主键组成的列表。
     * @throws DispatcherException 调度器异常。
     */
    List<StringIdKey> dispatch(String dispatchInfo, List<StringIdKey> userKeys, Context context)
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
         * 查询指定的偏好是否存在。
         *
         * @param userKey      偏好用户的主键。
         * @param preferenceId 偏好的 ID。
         * @return 指定的偏好是否存在。
         * @throws DispatcherException 调度器异常。
         */
        boolean existsPreference(StringIdKey userKey, String preferenceId)
                throws DispatcherException;

        /**
         * 获取指定的偏好的值。
         *
         * <p>
         * 如果指定的偏好值不存在，则返回 null。
         *
         * @param userKey      偏好用户的主键。
         * @param preferenceId 偏好的 ID。
         * @return 指定的偏好的值。
         * @throws DispatcherException 调度器异常。
         */
        String getPreference(StringIdKey userKey, String preferenceId) throws DispatcherException;

        /**
         * 获取指定的偏好的默认值。
         *
         * <p>
         * 如果指定的偏好的默认值不存在，则返回 null。
         *
         * @param preferenceId 偏好的 ID。
         * @return 指定的偏好的默认值。
         * @throws DispatcherException 调度器异常。
         */
        String getDefaultPreference(String preferenceId) throws DispatcherException;

        /**
         * 获取指定的偏好的值或默认值。
         *
         * <p>
         * 如果指定的偏好的值存在，则放回偏好值；否则，返回指定的偏好的默认值；如果默认值也不存在则返回 null。
         *
         * @param userKey      偏好用户的主键。
         * @param preferenceId 偏好的 ID。
         * @return 指定的偏好的值或默认值。
         * @throws DispatcherException 调度器异常。
         */
        default String getPreferenceOrDefault(StringIdKey userKey, String preferenceId) throws DispatcherException {
            if (existsPreference(userKey, preferenceId)) {
                return getPreference(userKey, preferenceId);
            } else {
                return getDefaultPreference(preferenceId);
            }
        }

        /**
         * 查询指定的变量是否存在。
         *
         * @param userKey    变量用户的主键。
         * @param variableId 变量的 ID。
         * @return 指定的变量是否存在。
         * @throws DispatcherException 调度器异常。
         */
        boolean existsVariable(StringIdKey userKey, String variableId) throws DispatcherException;

        /**
         * 获取指定的变量的默认值。
         *
         * <p>
         * 如果指定的变量的默认值不存在，则返回 null。
         *
         * @param variableId 变量的 ID。
         * @return 指定的变量的默认值。
         * @throws DispatcherException 调度器异常。
         */
        String getVariable(StringIdKey userKey, String variableId) throws DispatcherException;

        /**
         * 获取指定的变量的默认值。
         *
         * <p>
         * 如果指定的变量的默认值不存在，则返回 null。
         *
         * @param variableId 变量的 ID。
         * @return 指定的变量的默认值。
         * @throws DispatcherException 调度器异常。
         */
        String getDefaultVariable(String variableId) throws DispatcherException;

        /**
         * 获取指定的变量的值或默认值。
         *
         * <p>
         * 如果指定的变量的值存在，则放回变量值；否则，返回指定的变量的默认值；如果默认值也不存在则返回 null。
         *
         * @param userKey    变量用户的主键。
         * @param variableId 变量的 ID。
         * @return 指定的变量的值或默认值。
         * @throws DispatcherException 调度器异常。
         */
        default String getVariableOrDefault(StringIdKey userKey, String variableId) throws DispatcherException {
            if (existsVariable(userKey, variableId)) {
                return getVariable(userKey, variableId);
            } else {
                return getDefaultVariable(variableId);
            }
        }

        /**
         * 设置指定的变量的值。
         *
         * @param userKey    变量用户的主键。
         * @param variableId 变量的 ID。
         * @param value      变量的新值。
         * @throws DispatcherException 调度器异常。
         */
        void putVariable(StringIdKey userKey, String variableId, String value) throws DispatcherException;

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

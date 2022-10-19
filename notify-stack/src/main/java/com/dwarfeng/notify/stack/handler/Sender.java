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
     * 返回的发送结果组成的列表的长度必须与 <code>userKeys</code> 的长度相等，
     * 且发送结果的字段 {@link Result#userKey} 必须与 <code>userKeys</code> 相对应（数据一致且顺序相同）。
     * </li>
     * </ol>
     *
     * @param sendInfo 发送信息。
     * @param userKeys 用户列表。
     * @param context  上下文。
     * @return 发送结果组成的列表。
     * @throws SenderException 发送器异常。
     */
    List<Result> send(String sendInfo, List<StringIdKey> userKeys, Context context) throws SenderException;

    /**
     * 结果结构体。
     *
     * @author DwArFeng
     * @since 1.1.0
     */
    class Result {

        private final StringIdKey userKey;
        private final boolean succeedFlag;
        private final String message;

        public Result(StringIdKey userKey, boolean succeedFlag, String message) {
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
            return "Result{" +
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
         * 查询指定的偏好是否存在。
         *
         * @param userKey      偏好用户的主键。
         * @param preferenceId 偏好的 ID。
         * @return 指定的偏好是否存在。
         * @throws SenderException 发送器异常。
         */
        boolean existsPreference(StringIdKey userKey, String preferenceId) throws SenderException;

        /**
         * 获取指定的偏好的值。
         *
         * <p>
         * 如果指定的偏好值不存在，则返回 null。
         *
         * @param userKey      偏好用户的主键。
         * @param preferenceId 偏好的 ID。
         * @return 指定的偏好的值。
         * @throws SenderException 发送器异常。
         */
        String getPreference(StringIdKey userKey, String preferenceId) throws SenderException;

        /**
         * 获取指定的偏好的默认值。
         *
         * <p>
         * 如果指定的偏好的默认值不存在，则返回 null。
         *
         * @param preferenceId 偏好的 ID。
         * @return 指定的偏好的默认值。
         * @throws SenderException 发送器异常。
         */
        String getDefaultPreference(StringIdKey userKey, String preferenceId) throws SenderException;

        /**
         * 获取指定的偏好的值或默认值。
         *
         * <p>
         * 如果指定的偏好的值存在，则放回偏好值；否则，返回指定的偏好的默认值；如果默认值也不存在则返回 null。
         *
         * @param userKey      偏好用户的主键。
         * @param preferenceId 偏好的 ID。
         * @return 指定的偏好的值或默认值。
         * @throws SenderException 发送器异常。
         */
        default String getPreferenceOrDefault(StringIdKey userKey, String preferenceId) throws SenderException {
            if (existsPreference(userKey, preferenceId)) {
                return getPreference(userKey, preferenceId);
            } else {
                return getDefaultPreference(userKey, preferenceId);
            }
        }

        /**
         * 获取指定的变量的默认值。
         *
         * <p>
         * 如果指定的变量的默认值不存在，则返回 null。
         *
         * @param variableId 变量的 ID。
         * @return 指定的变量的默认值。
         * @throws SenderException 发送器异常。
         */
        boolean existsVariable(StringIdKey userKey, String variableId) throws SenderException;

        /**
         * 获取指定的变量的默认值。
         *
         * <p>
         * 如果指定的变量的默认值不存在，则返回 null。
         *
         * @param variableId 变量的 ID。
         * @return 指定的变量的默认值。
         * @throws SenderException 发送器异常。
         */
        String getVariable(StringIdKey userKey, String variableId) throws SenderException;

        /**
         * 设置指定的变量的值。
         *
         * @param userKey    变量用户的主键。
         * @param variableId 变量的 ID。
         * @param value      变量的新值。
         * @throws SenderException 发送器异常。
         */
        void putVariable(StringIdKey userKey, String variableId, String value) throws SenderException;
    }
}

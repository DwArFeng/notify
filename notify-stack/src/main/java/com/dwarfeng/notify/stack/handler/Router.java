package com.dwarfeng.notify.stack.handler;

import com.dwarfeng.notify.stack.bean.entity.User;
import com.dwarfeng.notify.stack.exception.RouterException;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

import java.util.List;
import java.util.Map;

/**
 * 路由器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface Router {

    /**
     * 路由操作。
     *
     * <p>
     * 通过该方法确定一个通知的用户空间。
     *
     * <p>
     * 实现路由操作时，需要注意以下规约：
     * <ol type="1">
     * <li>
     * 返回的结果中，所有的用户主键对应的用户必须是本通知系统存在的用户，
     * 而且 {@link User#isEnabled()} 必须为 <code>true</code>。
     * </li>
     * </ol>
     *
     * <p>
     * 如果无法确定用户的规范性，可以调用 {@link Context#filterUser(List)} 方法筛选出一组用户中符合要求的用户。<br>
     * 这个操作会有额外的性能开销，如果有其它的途径保证返回结果的规范性
     * （尤其是服务集成在一个工程中，与其它服务组合使用时），则不需要调用此方法。
     *
     * @param routeInfoMap 路由信息映射。
     * @param context      上下文。
     * @return 路由返回的用户主键组成的列表。
     * @throws RouterException 路由器异常。
     */
    List<StringIdKey> route(Map<String, String> routeInfoMap, Context context) throws RouterException;

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
         * @throws RouterException 路由器异常。
         */
        LongIdKey getNotifySettingKey() throws RouterException;

        /**
         * 获取本次通知相关的所有可用的主题。
         *
         * @return 所有主题的主键组成的列表。
         * @throws RouterException 路由器异常。
         */
        List<StringIdKey> availableTopicKeys() throws RouterException;

        /**
         * 查询指定的元数据是否存在。
         *
         * @param topicKey 元数据主题的主键。
         * @param userKey  元数据用户的主键。
         * @param metaId   元数据的 ID。
         * @return 指定的元数据是否存在。
         * @throws RouterException 路由器异常。
         */
        boolean existsMeta(StringIdKey topicKey, StringIdKey userKey, String metaId)
                throws RouterException;

        /**
         * 获取指定的元数据的值。
         *
         * <p>
         * 如果指定的元数据值不存在，则返回 null。
         *
         * @param topicKey 元数据主题的主键。
         * @param userKey  元数据用户的主键。
         * @param metaId   元数据的 ID。
         * @return 指定的元数据的值。
         * @throws RouterException 路由器异常。
         */
        String getMeta(StringIdKey topicKey, StringIdKey userKey, String metaId) throws RouterException;

        /**
         * 获取指定的元数据的默认值。
         *
         * <p>
         * 如果指定的元数据的默认值不存在，则返回 null。
         *
         * @param topicKey 元数据主题的主键。
         * @param metaId   元数据的 ID。
         * @return 指定的元数据的默认值。
         * @throws RouterException 路由器异常。
         */
        String getDefaultMeta(StringIdKey topicKey, String metaId) throws RouterException;

        /**
         * 获取指定的元数据的值或默认值。
         *
         * <p>
         * 如果指定的元数据的值存在，则放回元数据值；否则，返回指定的元数据的默认值；如果默认值也不存在则返回 null。
         *
         * @param topicKey 元数据主题的主键。
         * @param userKey  元数据用户的主键。
         * @param metaId   元数据的 ID。
         * @return 指定的元数据的值或默认值。
         * @throws RouterException 路由器异常。
         */
        default String getMetaOrDefault(StringIdKey topicKey, StringIdKey userKey, String metaId)
                throws RouterException {
            if (existsMeta(topicKey, userKey, metaId)) {
                return getMeta(topicKey, userKey, metaId);
            } else {
                return getDefaultMeta(topicKey, metaId);
            }
        }

        /**
         * 设置指定的元数据的值。
         *
         * @param topicKey 元数据主题的主键。
         * @param userKey  元数据用户的主键。
         * @param metaId   元数据的 ID。
         * @param value    元数据的新值。
         * @throws RouterException 路由器异常。
         */
        void putMeta(StringIdKey topicKey, StringIdKey userKey, String metaId, String value)
                throws RouterException;

        /**
         * 过滤用户。
         *
         * <p>
         * 该方法接收一个任意的用户列表，遍历并获取其中所有符合规范的用户，将其组合成列表并返回。
         *
         * @param userKeys 任意的用户列表。
         * @return 任意列表中符合规范的用户组成的子列表。
         * @throws RouterException 路由器异常。
         */
        List<StringIdKey> filterUser(List<StringIdKey> userKeys) throws RouterException;
    }
}

import com.dwarfeng.notify.impl.handler.dispatcher.GroovyDispatcherRegistry
import com.dwarfeng.notify.stack.exception.DispatcherException
import com.dwarfeng.notify.stack.handler.Dispatcher
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Component

import java.util.stream.Collectors

/**
 * 示例调度器。
 *
 * <p>
 * 该调度器以 {@link #DELIMITER} 为分隔符，对 dispatchInfoMap 进行分隔，并将结果数组作为用户的 ID，获取用户主键。<br>
 * 调度器将上一步得到的用户主键与 userKeys 取交集，返回最终结果。
 */
@SuppressWarnings("GrPackage")
@Component
class ExampleDispatcherProcessor implements GroovyDispatcherRegistry.Processor {

    /**
     * 调度信息主键。
     */
    private static final String DISPATCH_INFO_KEY = "key"

    /**
     * 调度实现中 dispatchInfoMap 的分隔符。
     */
    private static final String DELIMITER = ","

    @Override
    List<StringIdKey> dispatch(
            Map<String, String> dispatchInfoMap, List<StringIdKey> userKeys, Dispatcher.Context context
    ) throws DispatcherException {
        String dispatchInfo = Optional.ofNullable(dispatchInfoMap).map(map -> map.get(DISPATCH_INFO_KEY))
                .orElse(StringUtils.EMPTY)
        if (StringUtils.isEmpty(dispatchInfo)) {
            return Collections.emptyList()
        }
        List<StringIdKey> tempUserKeys = Arrays.stream(dispatchInfo.split(DELIMITER)).map(StringIdKey::new)
                .collect(Collectors.toList())
        tempUserKeys.retainAll(userKeys)
        return tempUserKeys
    }
}

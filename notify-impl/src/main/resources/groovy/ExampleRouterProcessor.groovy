package groovy

import com.dwarfeng.notify.impl.handler.router.GroovyRouterRegistry
import com.dwarfeng.notify.stack.exception.RouterException
import com.dwarfeng.notify.stack.handler.Router
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Component

import java.util.stream.Collectors

/**
 * 示例路由器。
 *
 * <p>
 * 该路由器以 {@link #DELIMITER} 为分隔符，对 routerInfo 进行分隔，并将结果数组作为用户的 ID 进行返回。
 */
@SuppressWarnings("GrPackage")
@Component
class ExampleRouterProcessor implements GroovyRouterRegistry.Processor {

    /**
     * 路由信息主键。
     */
    private static final String ROUTE_INFO_KEY = "key"

    /**
     * 路由实现中 routeInfoMap 的分隔符。
     */
    private static final String DELIMITER = ","

    @Override
    List<StringIdKey> route(Map<String, String> routeInfoMap, Router.Context context) throws RouterException {
        String routeInfo = Optional.ofNullable(routeInfoMap).map(map -> map.get(ROUTE_INFO_KEY))
                .orElse(StringUtils.EMPTY)
        if (StringUtils.isEmpty(routeInfo)) {
            return Collections.emptyList()
        }
        return Arrays.stream(routeInfo.split(DELIMITER)).map(StringIdKey::new).collect(Collectors.toList())
    }
}

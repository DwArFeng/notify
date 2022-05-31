package groovy

import com.dwarfeng.notify.impl.handler.router.GroovyRouterRegistry
import com.dwarfeng.notify.stack.bean.dto.Routing
import com.dwarfeng.notify.stack.bean.entity.Topic
import com.dwarfeng.notify.stack.bean.entity.User
import com.dwarfeng.notify.stack.exception.RouterException
import com.dwarfeng.notify.stack.service.TopicMaintainService
import com.dwarfeng.notify.stack.service.UserMaintainService
import com.dwarfeng.subgrade.stack.bean.dto.PagingInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * 示例路由器。
 *
 * <p>
 * 该路由器将从主题表中取出最多前 {@link #MAX_TOPIC} 个主题；并从用户表中取出最多 {@link #MAX_USER} 个用户。<br>
 * 随后，将用主题和用户排列，返回其所有的排列组合。
 */
@SuppressWarnings("GrPackage")
@Component
class ExampleRouterProcessor implements GroovyRouterRegistry.Processor {

    private static final int MAX_TOPIC = 2
    private static final int MAX_USER = 3

    @Autowired
    private TopicMaintainService topicMaintainService
    @Autowired
    private UserMaintainService userMaintainService

    @Override
    List<Routing> parseRouting(Object context) throws RouterException {
        List<Topic> topics = topicMaintainService.lookupAsList(new PagingInfo(0, MAX_TOPIC))
        List<User> users = userMaintainService.lookupAsList(new PagingInfo(0, MAX_USER))
        List<Routing> result = new ArrayList<>()
        for (Topic topic in topics) {
            for (User user in users) {
                result.add(new Routing(topic.getKey(), user.getKey()))
            }
        }
        return result
    }
}

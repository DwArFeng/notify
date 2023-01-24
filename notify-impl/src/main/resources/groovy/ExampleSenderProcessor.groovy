package groovy

import com.dwarfeng.notify.impl.handler.sender.GroovySenderRegistry
import com.dwarfeng.notify.stack.exception.SenderException
import com.dwarfeng.notify.stack.handler.Sender
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * 示例发送器。
 *
 * <p>
 * 将发送的内容以 info 等级打印在日志上。
 */
@SuppressWarnings("GrPackage")
@Component
class ExampleSenderProcessor implements GroovySenderRegistry.Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleSenderProcessor.class)

    @Override
    List<Sender.Response> send(Map<String, String> sendInfo, List<StringIdKey> userKeys, Sender.Context context)
            throws SenderException {
        List<Sender.Response> results = new ArrayList<>()
        for (StringIdKey userKey : userKeys) {
            LOGGER.info("向用户 {} 发生信息, 内容为 {}", userKey, context)
            results.add(new Sender.Response(userKey, new Date(), true, "发送成功"))
        }
        return results
    }
}

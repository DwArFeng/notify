import com.dwarfeng.notify.impl.handler.sender.GroovySenderRegistry
import com.dwarfeng.notify.stack.exception.SenderException
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * 示例发送器。
 *
 * <p>
 * 该发生器将会将发送信息输出到日志中。
 */
@SuppressWarnings("GrPackage")
class ExampleSenderProcessor implements GroovySenderRegistry.Processor {

    private final Logger LOGGER = LoggerFactory.getLogger(ExampleSenderProcessor.class)

    @Override
    void send(StringIdKey userKey, Object context) throws SenderException {
        LOGGER.info("向用户 {} 发生信息, 内容为 {}", userKey, context)
    }
}

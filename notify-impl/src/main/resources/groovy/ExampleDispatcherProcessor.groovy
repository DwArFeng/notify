import com.dwarfeng.notify.impl.handler.dispatcher.GroovyDispatcherRegistry
import com.dwarfeng.notify.stack.exception.DispatcherException
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * 示例调度器。
 *
 * <p>
 * 该调度器将会将调度信息输出到日志中。
 */
@SuppressWarnings("GrPackage")
class ExampleDispatcherProcessor implements GroovyDispatcherRegistry.Processor {

    private final Logger LOGGER = LoggerFactory.getLogger(ExampleDispatcherProcessor.class)

    @Override
    void dispatch(StringIdKey userKey, Object context) throws DispatcherException {
        LOGGER.info("向用户 {} 调度信息, 内容为 {}", userKey, context)
    }
}

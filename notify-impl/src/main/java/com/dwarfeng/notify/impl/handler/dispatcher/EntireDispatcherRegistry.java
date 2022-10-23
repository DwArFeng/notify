package com.dwarfeng.notify.impl.handler.dispatcher;

import com.dwarfeng.notify.stack.exception.DispatcherException;
import com.dwarfeng.notify.stack.exception.DispatcherMakeException;
import com.dwarfeng.notify.stack.handler.Dispatcher;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 全体调度器注册。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class EntireDispatcherRegistry extends AbstractDispatcherRegistry {

    public static final String DISPATCHER_TYPE = "entire_dispatcher";

    private final ApplicationContext ctx;

    public EntireDispatcherRegistry(ApplicationContext ctx) {
        super(DISPATCHER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "全体调度器";
    }

    @Override
    public String provideDescription() {
        return "接受调度过程中用户空间中的所有用户";
    }

    @Override
    public String provideExampleParam() {
        return "";
    }

    @Override
    public Dispatcher makeDispatcher(String type, String param) throws DispatcherException {
        try {
            // 构建过滤器对象并返回。
            return ctx.getBean(EntireDispatcher.class);
        } catch (Exception e) {
            throw new DispatcherMakeException(e, type, param);
        }
    }

    @Override
    public String toString() {
        return "EntireDispatcherRegistry{" +
                "dispatcherType='" + dispatcherType + '\'' +
                '}';
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class EntireDispatcher implements Dispatcher {

        @Override
        public List<StringIdKey> dispatch(String dispatchInfo, List<StringIdKey> userKeys, Context context) {
            return userKeys;
        }

        @Override
        public String toString() {
            return "EntireDispatcher{}";
        }
    }
}

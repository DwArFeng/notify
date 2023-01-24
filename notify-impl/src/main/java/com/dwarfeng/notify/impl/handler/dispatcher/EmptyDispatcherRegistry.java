package com.dwarfeng.notify.impl.handler.dispatcher;

import com.dwarfeng.notify.stack.exception.DispatcherException;
import com.dwarfeng.notify.stack.exception.DispatcherMakeException;
import com.dwarfeng.notify.stack.handler.Dispatcher;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 空调度器注册。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
@Component
public class EmptyDispatcherRegistry extends AbstractDispatcherRegistry {

    public static final String DISPATCHER_TYPE = "empty_dispatcher";

    private final ApplicationContext ctx;

    public EmptyDispatcherRegistry(ApplicationContext ctx) {
        super(DISPATCHER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "空调度器";
    }

    @Override
    public String provideDescription() {
        return "不返回任何用户的调度器";
    }

    @Override
    public String provideExampleParam() {
        return "";
    }

    @Override
    public Dispatcher makeDispatcher(String type, String param) throws DispatcherException {
        try {
            // 构建过滤器对象并返回。
            return ctx.getBean(EmptyDispatcher.class);
        } catch (Exception e) {
            throw new DispatcherMakeException(e, type, param);
        }
    }

    @Override
    public String toString() {
        return "EmptyDispatcherRegistry{" +
                "dispatcherType='" + dispatcherType + '\'' +
                '}';
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class EmptyDispatcher implements Dispatcher {

        @Override
        public List<StringIdKey> dispatch(Map<String, String> dispatchInfoMap, List<StringIdKey> userKeys, Context context) {
            return Collections.emptyList();
        }

        @Override
        public String toString() {
            return "EmptyDispatcher{}";
        }
    }
}

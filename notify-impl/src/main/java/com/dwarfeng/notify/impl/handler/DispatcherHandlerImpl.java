package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.stack.exception.DispatcherException;
import com.dwarfeng.notify.stack.exception.UnsupportedDispatcherTypeException;
import com.dwarfeng.notify.stack.handler.Dispatcher;
import com.dwarfeng.notify.stack.handler.DispatcherHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DispatcherHandlerImpl implements DispatcherHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DispatcherHandlerImpl.class);

    @Autowired(required = false)
    private List<DispatcherMaker> dispatcherMakers = new ArrayList<>();

    @Override
    public Dispatcher make(String type, String param) throws DispatcherException {
        try {
            // 生成调度器。
            LOGGER.debug("通过调度器信息构建新的的调度器...");
            DispatcherMaker dispatcherMaker = dispatcherMakers.stream().filter(maker -> maker.supportType(type))
                    .findFirst().orElseThrow(() -> new UnsupportedDispatcherTypeException(type));
            Dispatcher dispatcher = dispatcherMaker.makeDispatcher(type, param);
            LOGGER.debug("调度器构建成功!");
            LOGGER.debug("调度器: " + dispatcher);
            return dispatcher;
        } catch (DispatcherException e) {
            throw e;
        } catch (Exception e) {
            throw new DispatcherException(e);
        }
    }
}

package com.dwarfeng.notify.stack.bean.dto;

import com.dwarfeng.notify.stack.bean.entity.DispatcherInfo;
import com.dwarfeng.notify.stack.handler.Dispatcher;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 调度上下文。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class DispatchContext implements Dto {

    private static final long serialVersionUID = -3179599249870151543L;

    private DispatcherInfo dispatcherInfo;
    private Dispatcher dispatcher;

    public DispatchContext() {
    }

    public DispatchContext(DispatcherInfo dispatcherInfo, Dispatcher dispatcher) {
        this.dispatcherInfo = dispatcherInfo;
        this.dispatcher = dispatcher;
    }

    public DispatcherInfo getDispatcherInfo() {
        return dispatcherInfo;
    }

    public void setDispatcherInfo(DispatcherInfo dispatcherInfo) {
        this.dispatcherInfo = dispatcherInfo;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public String toString() {
        return "DispatchContext{" +
                "dispatcherInfo=" + dispatcherInfo +
                ", dispatcher=" + dispatcher +
                '}';
    }
}

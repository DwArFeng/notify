package com.dwarfeng.notify.impl.handler.dispatcher;

import com.dwarfeng.notify.impl.handler.DispatcherMaker;
import com.dwarfeng.notify.impl.handler.DispatcherSupporter;

import java.util.Objects;

/**
 * 抽象调度器注册。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public abstract class AbstractDispatcherRegistry implements DispatcherMaker, DispatcherSupporter {

    protected String dispatcherType;

    public AbstractDispatcherRegistry() {
    }

    public AbstractDispatcherRegistry(String dispatcherType) {
        this.dispatcherType = dispatcherType;
    }

    @Override
    public boolean supportType(String type) {
        return Objects.equals(dispatcherType, type);
    }

    @Override
    public String provideType() {
        return dispatcherType;
    }

    public String getDispatcherType() {
        return dispatcherType;
    }

    public void setDispatcherType(String dispatcherType) {
        this.dispatcherType = dispatcherType;
    }

    @Override
    public String toString() {
        return "AbstractDispatcherRegistry{" +
                "dispatcherType='" + dispatcherType + '\'' +
                '}';
    }
}

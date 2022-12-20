package com.dwarfeng.notify.stack.bean.dto;

import com.dwarfeng.notify.stack.handler.Dispatcher;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 调度信息。
 *
 * @author DwArFeng
 * @since 1.2.2
 */
public class DispatchInfo implements Dto {

    private static final long serialVersionUID = -804750650048225180L;

    private String type;
    private Dispatcher dispatcher;

    public DispatchInfo() {
    }

    public DispatchInfo(String type, Dispatcher dispatcher) {
        this.type = type;
        this.dispatcher = dispatcher;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public String toString() {
        return "DispatchInfo{" +
                "type='" + type + '\'' +
                ", dispatcher=" + dispatcher +
                '}';
    }
}

package com.dwarfeng.notify.impl.handler.resetter;

import org.springframework.stereotype.Component;

/**
 * 永远不执行重置的重置器。
 *
 * @author DwArFeng
 * @since 1.2.2
 */
@Component
public class NeverResetter extends AbstractResetter {

    @Override
    protected void doStart() {
    }

    @Override
    protected void doStop() {
    }

    @Override
    public String toString() {
        return "NeverResetter{}";
    }
}

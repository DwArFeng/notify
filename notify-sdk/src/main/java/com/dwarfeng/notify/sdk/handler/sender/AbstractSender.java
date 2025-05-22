package com.dwarfeng.notify.sdk.handler.sender;

import com.dwarfeng.notify.stack.handler.Sender;

/**
 * 发送器的抽象实现。
 *
 * @author DwArFeng
 * @since 1.5.0
 */
public abstract class AbstractSender implements Sender {

    protected Context context;

    @Override
    public void init(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "AbstractSender{" +
                "context=" + context +
                '}';
    }
}

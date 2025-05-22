package com.dwarfeng.notify.sdk.handler.sender;

import com.dwarfeng.notify.sdk.handler.SenderMaker;
import com.dwarfeng.notify.sdk.handler.SenderSupporter;

import java.util.Objects;

/**
 * 抽象发送器注册。
 *
 * @author DwArFeng
 * @since 1.5.0
 */
public abstract class AbstractSenderRegistry implements SenderMaker, SenderSupporter {

    protected String senderType;

    public AbstractSenderRegistry() {
    }

    public AbstractSenderRegistry(String senderType) {
        this.senderType = senderType;
    }

    @Override
    public boolean supportType(String type) {
        return Objects.equals(senderType, type);
    }

    @Override
    public String provideType() {
        return senderType;
    }

    public String getSenderType() {
        return senderType;
    }

    public void setSenderType(String senderType) {
        this.senderType = senderType;
    }

    @Override
    public String toString() {
        return "AbstractSenderRegistry{" +
                "senderType='" + senderType + '\'' +
                '}';
    }
}

package com.dwarfeng.notify.stack.bean.dto;

import com.dwarfeng.notify.stack.bean.entity.SenderInfo;
import com.dwarfeng.notify.stack.handler.Sender;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 发送上下文。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class SendContext implements Dto {

    private static final long serialVersionUID = -7045997741899948474L;

    private SenderInfo senderInfo;
    private Sender sender;

    public SendContext() {
    }

    public SendContext(SenderInfo senderInfo, Sender sender) {
        this.senderInfo = senderInfo;
        this.sender = sender;
    }

    public SenderInfo getSenderInfo() {
        return senderInfo;
    }

    public void setSenderInfo(SenderInfo senderInfo) {
        this.senderInfo = senderInfo;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "SendContext{" +
                "senderInfo=" + senderInfo +
                ", sender=" + sender +
                '}';
    }
}

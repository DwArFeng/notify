package com.dwarfeng.notify.stack.bean.dto;

import com.dwarfeng.notify.stack.handler.Sender;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 发送信息。
 *
 * @author DwArFeng
 * @since 1.2.2
 */
public class SendInfo implements Dto {

    private static final long serialVersionUID = -8151267589472397434L;

    private String type;
    private Sender sender;

    public SendInfo() {
    }

    public SendInfo(String type, Sender sender) {
        this.type = type;
        this.sender = sender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "SendInfo{" +
                "type='" + type + '\'' +
                ", sender=" + sender +
                '}';
    }
}

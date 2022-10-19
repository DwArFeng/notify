package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.stack.bean.entity.SendHistory;
import com.dwarfeng.notify.stack.handler.PushHandler;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class PushHandlerImpl implements PushHandler {

    @Autowired(required = false)
    private List<Pusher> pushers = new ArrayList<>();

    @Value("${pusher.type}")
    private String pusherType;

    private Pusher pusher;

    @PostConstruct
    public void init() throws HandlerException {
        this.pusher = pushers.stream().filter(p -> p.supportType(pusherType)).findAny()
                .orElseThrow(() -> new HandlerException("未知的 pusher 类型: " + pusherType));
    }

    @Override
    public void notifySent(SendHistory sendHistory) throws HandlerException {
        pusher.notifySent(sendHistory);
    }

    @Override
    public void notifySent(List<SendHistory> sendHistories) throws HandlerException {
        pusher.notifySent(sendHistories);
    }
}

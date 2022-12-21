package com.dwarfeng.notify.impl.handler.pusher;

import com.dwarfeng.notify.stack.bean.entity.SendHistory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 简单的丢弃掉所有信息的推送器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
@Component
public class DrainPusher extends AbstractPusher {

    public static final String SUPPORT_TYPE = "drain";

    public DrainPusher() {
        super(SUPPORT_TYPE);
    }

    @Override
    public void notifySent(SendHistory sendHistory) {
    }

    @Override
    public void notifySent(List<SendHistory> sendHistories) {
    }

    @Override
    public void routeReset() {
    }

    @Override
    public void dispatchReset() {
    }

    @Override
    public void sendReset() {
    }
}

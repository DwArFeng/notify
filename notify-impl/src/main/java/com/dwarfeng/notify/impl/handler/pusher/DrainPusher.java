package com.dwarfeng.notify.impl.handler.pusher;

import com.dwarfeng.notify.stack.bean.dto.NotifyHistoryRecordInfo;
import org.springframework.stereotype.Component;

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
    public void notifyHistoryRecorded(NotifyHistoryRecordInfo info) {
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

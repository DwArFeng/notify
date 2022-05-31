package com.dwarfeng.notify.impl.handler.pusher;

import com.dwarfeng.notify.impl.handler.Pusher;
import com.dwarfeng.notify.stack.bean.entity.NotifySetting;
import com.dwarfeng.notify.stack.bean.entity.Topic;
import com.dwarfeng.notify.stack.bean.entity.User;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 同时将消息推送给所有代理的多重推送器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
@Component
public class MultiPusher extends AbstractPusher {

    public static final String SUPPORT_TYPE = "multi";

    private static final Logger LOGGER = LoggerFactory.getLogger(MultiPusher.class);

    @Autowired
    private List<Pusher> pushers;

    @Value("${pusher.multi.delegate_types}")
    private String delegateTypes;

    private final List<Pusher> delegates = new ArrayList<>();

    public MultiPusher() {
        super(SUPPORT_TYPE);
    }

    @PostConstruct
    public void init() throws HandlerException {
        StringTokenizer st = new StringTokenizer(delegateTypes, ",");
        while (st.hasMoreTokens()) {
            String delegateType = st.nextToken();
            delegates.add(pushers.stream().filter(p -> p.supportType(delegateType)).findAny()
                    .orElseThrow(() -> new HandlerException("未知的 pusher 类型: " + delegateType)));
        }
    }

    @Override
    public void notifyHappened(NotifySetting notifySetting, Topic topic, User user, Object[] context) {
        for (Pusher delegate : delegates) {
            try {
                delegate.notifyHappened(notifySetting, topic, user, context);
            } catch (Exception e) {
                LOGGER.warn("代理推送器推送数据失败，异常信息如下: ", e);
            }
        }
    }

    @Override
    public void notifyHappened(NotifySetting notifySetting, Topic topic, List<User> users, Object[] context) {
        for (Pusher delegate : delegates) {
            try {
                delegate.notifyHappened(notifySetting, topic, users, context);
            } catch (Exception e) {
                LOGGER.warn("代理推送器推送数据失败，异常信息如下: ", e);
            }
        }
    }

    @Override
    public String toString() {
        return "MultiPusher{" +
                "pusherType='" + pusherType + '\'' +
                ", delegateTypes='" + delegateTypes + '\'' +
                ", delegates=" + delegates +
                '}';
    }
}

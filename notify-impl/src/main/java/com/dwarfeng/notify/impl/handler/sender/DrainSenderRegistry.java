package com.dwarfeng.notify.impl.handler.sender;

import com.dwarfeng.notify.stack.exception.SenderException;
import com.dwarfeng.notify.stack.exception.SenderMakeException;
import com.dwarfeng.notify.stack.handler.Sender;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 下水道发送器注册。
 *
 * @author DwArFeng
 * @since 1.0.1
 */
@Component
public class DrainSenderRegistry extends AbstractSenderRegistry {

    public static final String SENDER_TYPE = "drain_sender";

    private final ApplicationContext ctx;

    public DrainSenderRegistry(ApplicationContext ctx) {
        super(SENDER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "下水道发送器";
    }

    @Override
    public String provideDescription() {
        return "丢弃掉整个发送信息，不做任何动作";
    }

    @Override
    public String provideExampleParam() {
        return StringUtils.EMPTY;
    }

    @Override
    public Sender makeSender(String type, String param) throws SenderException {
        try {
            return ctx.getBean(DrainSender.class);
        } catch (Exception e) {
            throw new SenderMakeException(e, type, param);
        }
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class DrainSender implements Sender {

        @Override
        public List<Response> send(String sendInfo, List<StringIdKey> userKeys, Context context) {
            List<Response> responses = new ArrayList<>();

            Date currentDate = new Date();
            for (StringIdKey userKey : userKeys) {
                responses.add(new Response(userKey, currentDate, true, "发送成功"));
            }

            return responses;
        }

        @Override
        public String toString() {
            return "DrainSender{}";
        }
    }
}

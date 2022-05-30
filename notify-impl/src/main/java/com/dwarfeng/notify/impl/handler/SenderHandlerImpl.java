package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.stack.exception.SenderException;
import com.dwarfeng.notify.stack.exception.UnsupportedSenderTypeException;
import com.dwarfeng.notify.stack.handler.Sender;
import com.dwarfeng.notify.stack.handler.SenderHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SenderHandlerImpl implements SenderHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SenderHandlerImpl.class);

    @Autowired(required = false)
    private List<SenderMaker> senderMakers = new ArrayList<>();

    @Override
    public Sender make(String type, String param) throws SenderException {
        try {
            // 生成发送器。
            LOGGER.debug("通过发送器信息构建新的的发送器...");
            SenderMaker senderMaker = senderMakers.stream().filter(maker -> maker.supportType(type))
                    .findFirst().orElseThrow(() -> new UnsupportedSenderTypeException(type));
            Sender sender = senderMaker.makeSender(type, param);
            LOGGER.debug("发送器构建成功!");
            LOGGER.debug("发送器: " + sender);
            return sender;
        } catch (SenderException e) {
            throw e;
        } catch (Exception e) {
            throw new SenderException(e);
        }
    }
}

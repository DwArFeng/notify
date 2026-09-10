package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.notify.impl.internal.i18n.ImplMessages;

import com.dwarfeng.notify.sdk.handler.SenderMaker;
import com.dwarfeng.notify.stack.bean.entity.Meta;
import com.dwarfeng.notify.stack.bean.entity.MetaIndicator;
import com.dwarfeng.notify.stack.bean.key.MetaIndicatorKey;
import com.dwarfeng.notify.stack.bean.key.MetaKey;
import com.dwarfeng.notify.stack.exception.SenderException;
import com.dwarfeng.notify.stack.exception.UnsupportedSenderTypeException;
import com.dwarfeng.notify.stack.handler.Sender;
import com.dwarfeng.notify.stack.handler.SenderHandler;
import com.dwarfeng.notify.stack.service.MetaIndicatorMaintainService;
import com.dwarfeng.notify.stack.service.MetaMaintainService;
import com.dwarfeng.subgrade.basic.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SenderHandlerImpl implements SenderHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SenderHandlerImpl.class);

    private final MetaMaintainService metaMaintainService;
    private final MetaIndicatorMaintainService metaIndicatorMaintainService;

    private final HandlerValidator handlerValidator;

    private final List<SenderMaker> senderMakers;

    private final InternalSenderContext senderContext = new InternalSenderContext();

    public SenderHandlerImpl(
            MetaMaintainService metaMaintainService,
            MetaIndicatorMaintainService metaIndicatorMaintainService,
            HandlerValidator handlerValidator,
            List<SenderMaker> senderMakers
    ) {
        this.metaMaintainService = metaMaintainService;
        this.metaIndicatorMaintainService = metaIndicatorMaintainService;
        this.handlerValidator = handlerValidator;
        this.senderMakers = Optional.ofNullable(senderMakers).orElse(Collections.emptyList());
    }

    @Override
    public Sender make(String type, String param) throws SenderException {
        try {
            // 生成发送器。
            LOGGER.debug(ImplMessages.message(ImplMessageKey.LOG_SENDER_BUILDING));
            SenderMaker senderMaker = senderMakers.stream().filter(maker -> maker.supportType(type))
                    .findFirst().orElseThrow(() -> new UnsupportedSenderTypeException(type));
            Sender sender = senderMaker.makeSender(type, param);
            LOGGER.debug(ImplMessages.message(ImplMessageKey.LOG_SENDER_BUILT));
            sender.init(senderContext);
            LOGGER.debug(ImplMessages.message(ImplMessageKey.LOG_SENDER_INITIALIZED));
            LOGGER.debug(ImplMessages.message(ImplMessageKey.LOG_SENDER_DETAIL, sender));
            return sender;
        } catch (SenderException e) {
            throw e;
        } catch (Exception e) {
            throw new SenderException(e);
        }
    }

    private class InternalSenderContext implements Sender.Context {

        @Override
        public boolean existsMeta(LongIdKey notifySettingKey, StringIdKey topicKey, StringIdKey userKey, String metaId)
                throws SenderException {
            try {
                return metaMaintainService.exists(new MetaKey(
                        notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(), metaId
                ));
            } catch (Exception e) {
                throw new SenderException(e);
            }
        }

        @Override
        public String getMeta(LongIdKey notifySettingKey, StringIdKey topicKey, StringIdKey userKey, String metaId)
                throws SenderException {
            try {
                // 参数有效性验证。
                handlerValidator.makeSureUserExists(userKey);

                Meta meta = metaMaintainService.getIfExists(new MetaKey(
                        notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(), metaId
                ));
                if (Objects.isNull(meta)) {
                    return null;
                }
                return meta.getValue();
            } catch (Exception e) {
                throw new SenderException(e);
            }
        }

        @Override
        public String getDefaultMeta(LongIdKey notifySettingKey, StringIdKey topicKey, String metaId)
                throws SenderException {
            try {
                MetaIndicator metaIndicator = metaIndicatorMaintainService.getIfExists(
                        new MetaIndicatorKey(topicKey.getStringId(), metaId)
                );
                if (Objects.isNull(metaIndicator)) {
                    return null;
                }
                return metaIndicator.getDefaultValue();
            } catch (Exception e) {
                throw new SenderException(e);
            }
        }

        @Override
        public void putMeta(
                LongIdKey notifySettingKey, StringIdKey topicKey, StringIdKey userKey, String metaId, String value
        ) throws SenderException {
            try {
                // 参数有效性验证。
                handlerValidator.makeSureUserExists(userKey);

                Meta meta = new Meta(
                        new MetaKey(
                                notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(),
                                metaId
                        ),
                        value,
                        ImplMessages.message(
                                ImplMessageKey.META_REMARK_INTERNAL_ROUTER_CONTEXT_UPDATED,
                                new Date().toString()
                        )
                );
                metaMaintainService.insertOrUpdate(meta);
            } catch (Exception e) {
                throw new SenderException(e);
            }
        }
    }
}

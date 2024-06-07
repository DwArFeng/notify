package com.dwarfeng.notify.impl.handler;

import com.dwarfeng.notify.stack.bean.entity.Meta;
import com.dwarfeng.notify.stack.bean.entity.MetaIndicator;
import com.dwarfeng.notify.stack.bean.key.MetaIndicatorKey;
import com.dwarfeng.notify.stack.bean.key.MetaKey;
import com.dwarfeng.notify.stack.exception.DispatcherException;
import com.dwarfeng.notify.stack.exception.UnsupportedDispatcherTypeException;
import com.dwarfeng.notify.stack.handler.Dispatcher;
import com.dwarfeng.notify.stack.handler.DispatcherHandler;
import com.dwarfeng.notify.stack.service.MetaIndicatorMaintainService;
import com.dwarfeng.notify.stack.service.MetaMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DispatcherHandlerImpl implements DispatcherHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DispatcherHandlerImpl.class);

    private final MetaMaintainService metaMaintainService;
    private final MetaIndicatorMaintainService metaIndicatorMaintainService;

    private final HandlerValidator handlerValidator;

    private final List<DispatcherMaker> dispatcherMakers;

    private final InternalDispatcherContext dispatcherContext = new InternalDispatcherContext();

    public DispatcherHandlerImpl(
            MetaMaintainService metaMaintainService,
            MetaIndicatorMaintainService metaIndicatorMaintainService,
            HandlerValidator handlerValidator,
            List<DispatcherMaker> dispatcherMakers
    ) {
        this.metaMaintainService = metaMaintainService;
        this.metaIndicatorMaintainService = metaIndicatorMaintainService;
        this.handlerValidator = handlerValidator;
        this.dispatcherMakers = Optional.ofNullable(dispatcherMakers).orElse(Collections.emptyList());
    }

    @Override
    public Dispatcher make(String type, String param) throws DispatcherException {
        try {
            // 生成调度器。
            LOGGER.debug("通过调度器信息构建新的的调度器...");
            DispatcherMaker dispatcherMaker = dispatcherMakers.stream().filter(maker -> maker.supportType(type))
                    .findFirst().orElseThrow(() -> new UnsupportedDispatcherTypeException(type));
            Dispatcher dispatcher = dispatcherMaker.makeDispatcher(type, param);
            LOGGER.debug("调度器构建成功!");
            dispatcher.init(dispatcherContext);
            LOGGER.debug("调度器初始化成功!");
            LOGGER.debug("调度器: {}", dispatcher);
            return dispatcher;
        } catch (DispatcherException e) {
            throw e;
        } catch (Exception e) {
            throw new DispatcherException(e);
        }
    }

    private class InternalDispatcherContext implements Dispatcher.Context {

        @Override
        public boolean existsMeta(LongIdKey notifySettingKey, StringIdKey topicKey, StringIdKey userKey, String metaId)
                throws DispatcherException {
            try {
                return metaMaintainService.exists(new MetaKey(
                        notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(), metaId
                ));
            } catch (Exception e) {
                throw new DispatcherException(e);
            }
        }

        @Override
        public String getMeta(LongIdKey notifySettingKey, StringIdKey topicKey, StringIdKey userKey, String metaId)
                throws DispatcherException {
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
                throw new DispatcherException(e);
            }
        }

        @Override
        public String getDefaultMeta(LongIdKey notifySettingKey, StringIdKey topicKey, String metaId)
                throws DispatcherException {
            try {
                MetaIndicator metaIndicator = metaIndicatorMaintainService.getIfExists(
                        new MetaIndicatorKey(topicKey.getStringId(), metaId)
                );
                if (Objects.isNull(metaIndicator)) {
                    return null;
                }
                return metaIndicator.getDefaultValue();
            } catch (Exception e) {
                throw new DispatcherException(e);
            }
        }

        @Override
        public void putMeta(
                LongIdKey notifySettingKey, StringIdKey topicKey, StringIdKey userKey, String metaId, String value
        ) throws DispatcherException {
            try {
                // 参数有效性验证。
                handlerValidator.makeSureUserExists(userKey);

                Meta meta = new Meta(
                        new MetaKey(
                                notifySettingKey.getLongId(), topicKey.getStringId(), userKey.getStringId(),
                                metaId
                        ),
                        value, "通过 InternalRouterContext 更新, 更新日期: " + new Date()
                );
                metaMaintainService.insertOrUpdate(meta);
            } catch (Exception e) {
                throw new DispatcherException(e);
            }
        }
    }
}

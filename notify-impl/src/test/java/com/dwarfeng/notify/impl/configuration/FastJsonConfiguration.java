package com.dwarfeng.notify.impl.configuration;

import com.alibaba.fastjson2.JSONFactory;
import com.dwarfeng.notify.sdk.bean.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FastJsonConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(FastJsonConfiguration.class);

    public FastJsonConfiguration() {
        LOGGER.info("正在配置 FastJson auto-type 白名单...");
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonUser.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonRouterInfo.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonRouterSupport.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonNotifySetting.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonSenderInfo.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonSenderSupport.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonTopic.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonDispatcherInfo.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonDispatcherSupport.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonMeta.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonMetaIndicator.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonNotifyHistory.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonNotifyInfoRecord.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonNotifySendRecord.class.getCanonicalName());
        LOGGER.debug("FastJson auto-type 白名单配置完毕");
    }
}

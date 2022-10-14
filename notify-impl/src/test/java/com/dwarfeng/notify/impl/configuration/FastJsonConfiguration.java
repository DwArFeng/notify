package com.dwarfeng.notify.impl.configuration;

import com.alibaba.fastjson.parser.ParserConfig;
import com.dwarfeng.notify.sdk.bean.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FastJsonConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(FastJsonConfiguration.class);

    public FastJsonConfiguration() {
        LOGGER.info("正在配置 FastJson auto-type 白名单...");
        ParserConfig.getGlobalInstance().addAccept(FastJsonUser.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonRouterInfo.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonRouterSupport.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonNotifySetting.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonSenderInfo.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonSenderSupport.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonTopic.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonRelation.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonDispatcherInfo.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonDispatcherSupport.class.getCanonicalName());
        LOGGER.debug("FastJson auto-type 白名单配置完毕");
    }
}

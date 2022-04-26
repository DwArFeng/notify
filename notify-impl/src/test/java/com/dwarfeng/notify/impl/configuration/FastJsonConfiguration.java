package com.dwarfeng.notify.impl.configuration;

import com.alibaba.fastjson.parser.ParserConfig;
import com.dwarfeng.notify.sdk.bean.entity.FastJsonRouterInfo;
import com.dwarfeng.notify.sdk.bean.entity.FastJsonRouterSupport;
import com.dwarfeng.notify.sdk.bean.entity.FastJsonUser;
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
        LOGGER.debug("FastJson auto-type 白名单配置完毕");
    }
}

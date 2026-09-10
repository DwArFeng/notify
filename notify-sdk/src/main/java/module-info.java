module com.dwarfeng.notify.sdk {

    requires com.dwarfeng.notify.base;
    requires com.dwarfeng.notify.stack;
    requires com.dwarfeng.subgrade.basic;
    requires com.dwarfeng.subgrade.data;
    requires com.dwarfeng.subgrade.web;
    requires com.alibaba.fastjson2;
    requires jakarta.validation;
    requires org.apache.commons.lang3;
    requires org.hibernate.validator;
    requires org.mapstruct;
    requires org.slf4j;

    exports com.dwarfeng.notify.sdk.bean;
    exports com.dwarfeng.notify.sdk.bean.dto;
    exports com.dwarfeng.notify.sdk.bean.entity;
    exports com.dwarfeng.notify.sdk.bean.key;
    exports com.dwarfeng.notify.sdk.bean.key.formatter;
    exports com.dwarfeng.notify.sdk.exception;
    exports com.dwarfeng.notify.sdk.handler;
    exports com.dwarfeng.notify.sdk.handler.dispatcher;
    exports com.dwarfeng.notify.sdk.handler.pusher;
    exports com.dwarfeng.notify.sdk.handler.resetter;
    exports com.dwarfeng.notify.sdk.handler.router;
    exports com.dwarfeng.notify.sdk.handler.sender;
    exports com.dwarfeng.notify.sdk.util;

    opens com.dwarfeng.notify.sdk.i18n to com.dwarfeng.notify.base;
}

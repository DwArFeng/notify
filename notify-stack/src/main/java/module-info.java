module com.dwarfeng.notify.stack {

    requires com.dwarfeng.notify.base;
    requires com.dwarfeng.subgrade.basic;
    requires com.dwarfeng.subgrade.data;
    requires com.dwarfeng.subgrade.cache;
    requires com.dwarfeng.subgrade.lifecycle;
    requires com.dwarfeng.subgrade.lock;
    requires static org.jetbrains.annotations;

    exports com.dwarfeng.notify.stack.bean.dto;
    exports com.dwarfeng.notify.stack.bean.entity;
    exports com.dwarfeng.notify.stack.bean.key;
    exports com.dwarfeng.notify.stack.cache;
    exports com.dwarfeng.notify.stack.dao;
    exports com.dwarfeng.notify.stack.exception;
    exports com.dwarfeng.notify.stack.handler;
    exports com.dwarfeng.notify.stack.service;

    opens com.dwarfeng.notify.stack.i18n to com.dwarfeng.notify.base;
}

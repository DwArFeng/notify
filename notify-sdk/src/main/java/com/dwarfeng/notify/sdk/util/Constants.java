package com.dwarfeng.notify.sdk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 常量工具类。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public final class Constants {

    private static final Logger LOGGER = LoggerFactory.getLogger(Constants.class);

    @NotifyInfoRecordType
    public static final int NOTIFY_INFO_RECORD_TYPE_ROUTE_INFO = 0;
    @NotifyInfoRecordType
    public static final int NOTIFY_INFO_RECORD_TYPE_DISPATCH_INFO = 1;
    @NotifyInfoRecordType
    public static final int NOTIFY_INFO_RECORD_TYPE_SEND_INFO = 2;

    private static final Lock LOCK = new ReentrantLock();

    private static List<Integer> notifyInfoRecordTypeSpace = null;

    /**
     * 获取产线组件属性类型的空间。
     *
     * @return 产线组件属性类型的空间。
     */
    public static List<Integer> notifyInfoRecordTypeSpace() {
        if (Objects.nonNull(notifyInfoRecordTypeSpace)) {
            return notifyInfoRecordTypeSpace;
        }
        // 基于线程安全的懒加载初始化结果列表。
        LOCK.lock();
        try {
            if (Objects.nonNull(notifyInfoRecordTypeSpace)) {
                return notifyInfoRecordTypeSpace;
            }
            initNotifyInfoRecordTypeSpace();
            return notifyInfoRecordTypeSpace;
        } finally {
            LOCK.unlock();
        }
    }

    private static void initNotifyInfoRecordTypeSpace() {
        List<Integer> result = new ArrayList<>();

        Field[] declaredFields = Constants.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (!declaredField.isAnnotationPresent(NotifyInfoRecordType.class)) {
                continue;
            }
            Integer value;
            try {
                value = (Integer) declaredField.get(null);
                result.add(value);
            } catch (Exception e) {
                LOGGER.error("初始化异常, 请检查代码, 信息如下: ", e);
            }
        }

        notifyInfoRecordTypeSpace = Collections.unmodifiableList(result);
    }

    private Constants() {
        throw new IllegalStateException("禁止实例化");
    }
}

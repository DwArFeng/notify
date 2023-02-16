package com.dwarfeng.notify.sdk.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 通知信息记录类型。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface NotifyInfoRecordType {
}

package com.dwarfeng.notify.stack.exception;

import com.dwarfeng.notify.stack.internal.i18n.StackMessageKey;
import com.dwarfeng.notify.stack.internal.i18n.StackMessages;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.basic.stack.exception.HandlerException;

import java.io.Serial;

/**
 * 主题不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class TopicNotExistsException extends HandlerException {

    @Serial
    private static final long serialVersionUID = -6796457400428165093L;

    private final StringIdKey topicKey;

    public TopicNotExistsException(StringIdKey topicKey) {
        this.topicKey = topicKey;
    }

    public TopicNotExistsException(Throwable cause, StringIdKey topicKey) {
        super(cause);
        this.topicKey = topicKey;
    }

    @Override
    public String getMessage() {
        return StackMessages.message(StackMessageKey.TOPIC_NOT_EXISTS, topicKey);
    }
}

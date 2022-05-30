package com.dwarfeng.notify.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 主题不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class TopicNotExistsException extends HandlerException {

    private static final long serialVersionUID = 4807952907793339517L;

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
        return "主题 " + topicKey + " 不存在";
    }
}

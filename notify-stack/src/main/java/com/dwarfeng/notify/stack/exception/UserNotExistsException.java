package com.dwarfeng.notify.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 用户不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class UserNotExistsException extends HandlerException {

    private static final long serialVersionUID = 6568028946144150218L;

    private final StringIdKey topicKey;

    public UserNotExistsException(StringIdKey topicKey) {
        this.topicKey = topicKey;
    }

    public UserNotExistsException(Throwable cause, StringIdKey topicKey) {
        super(cause);
        this.topicKey = topicKey;
    }

    @Override
    public String getMessage() {
        return "用户 " + topicKey + " 不存在";
    }
}

package com.dwarfeng.notify.stack.exception;

import com.dwarfeng.notify.stack.internal.i18n.StackMessageKey;
import com.dwarfeng.notify.stack.internal.i18n.StackMessages;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.basic.stack.exception.HandlerException;

import java.io.Serial;

/**
 * 用户不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class UserNotExistsException extends HandlerException {

    @Serial
    private static final long serialVersionUID = 3063554023868026669L;

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
        return StackMessages.message(StackMessageKey.USER_NOT_EXISTS, topicKey);
    }
}

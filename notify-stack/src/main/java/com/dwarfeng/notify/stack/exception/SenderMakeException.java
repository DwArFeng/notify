package com.dwarfeng.notify.stack.exception;

/**
 * 发送器构造异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class SenderMakeException extends SenderException {

    private static final long serialVersionUID = 319531295182011844L;

    private final String senderType;
    private final String param;

    public SenderMakeException(String senderType, String param) {
        this.senderType = senderType;
        this.param = param;
    }

    public SenderMakeException(Throwable cause, String senderType, String param) {
        super(cause);
        this.senderType = senderType;
        this.param = param;
    }

    @Override
    public String getMessage() {
        return "发送器构造异常, 类型为: " + senderType + ", 参数为: " + param;
    }
}

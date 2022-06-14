package com.cuukenn.common.netty.util;

import com.cuukenn.common.netty.enums.ApplicationType;
import protocol.Message;

/**
 * @author changgg
 */
public class ProtocolUtil {
    public static Message.TransportProtocol.Builder createProtocol(ApplicationType type) {
        return Message.TransportProtocol.newBuilder().setId(IdUtil.generateId(type));
    }

    public static Message.TransportProtocol createError(ApplicationType type, int code, String message) {
        return createProtocol(type).setType(Message.ProtocolType.ERROR)
                .setError(Message.ErrorMessage.newBuilder()
                .setCode(code).setMessage(message)).build();
    }
}

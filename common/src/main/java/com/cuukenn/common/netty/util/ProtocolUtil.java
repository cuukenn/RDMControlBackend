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

    public static Message.TransportProtocol empty(ApplicationType type, Message.ProtocolType protocolType) {
        return createProtocol(type, protocolType).setNullValue(Message.NullValue.NULL_VALUE).build();
    }

    public static Message.TransportProtocol.Builder createProtocol(ApplicationType type, Message.ProtocolType protocolType) {
        return createProtocol(type).setType(protocolType);
    }

    public static Message.TransportProtocol error(ApplicationType type, int code, String message) {
        return createProtocol(type, Message.ProtocolType.ERROR).setError(Message.ErrorMessage.newBuilder().setCode(code).setMessage(message).build()).build();
    }
}

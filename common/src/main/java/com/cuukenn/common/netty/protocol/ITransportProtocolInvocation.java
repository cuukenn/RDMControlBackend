package com.cuukenn.common.netty.protocol;

import protocol.Message;

/**
 * @author changgg
 */
public interface ITransportProtocolInvocation extends IProtocolInvocation<Message.TransportProtocol> {
    /**
     * 获取支持的类型
     *
     * @return type
     */
    Message.ProtocolType getSupportType();

    @Override
    default boolean support(Object message) {
        if (message == null) {
            return false;
        }
        if (message instanceof Message.TransportProtocol) {
            return ((Message.TransportProtocol) message).getType() == getSupportType();
        }
        return false;
    }
}

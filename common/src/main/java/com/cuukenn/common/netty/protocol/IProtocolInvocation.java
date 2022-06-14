package com.cuukenn.common.netty.protocol;

import io.netty.channel.ChannelHandlerContext;

/**
 * @author changgg
 */
public interface IProtocolInvocation<T> {
    /**
     * 判断是否符合要求
     *
     * @param message 消息
     * @return 是否可处理
     */
    boolean support(Object message);

    /**
     * 处理消息
     *
     * @param ctx     netty上下文
     * @param message 消息
     */
    void invoke(ChannelHandlerContext ctx, T message);
}

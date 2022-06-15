package com.cuukenn.common.netty.client.handler;

import com.cuukenn.common.netty.client.config.BaseNettyClientProperties;
import com.cuukenn.common.netty.client.handler.bound.ConnectionStateWatchDog;
import com.cuukenn.common.netty.protocol.TransportProtocolInvocation;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.RequiredArgsConstructor;
import protocol.Message;

import java.util.concurrent.TimeUnit;

/**
 * @author changgg
 */
@RequiredArgsConstructor
public class NettyClientChannelInitializer extends ChannelInitializer<NioSocketChannel> {
    private final BaseNettyClientProperties properties;
    private final NettyClient nettyClient;

    @Override
    protected void initChannel(NioSocketChannel socketChannel) throws Exception {
        socketChannel.pipeline()
                .addLast(
                        new IdleStateHandler(
                                0,
                                properties.getWriterIdleTimeSeconds(),
                                0,
                                TimeUnit.SECONDS)
                )
                .addLast(new ProtobufVarint32FrameDecoder())
                .addLast(new ProtobufDecoder(Message.TransportProtocol.getDefaultInstance()))
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                .addLast(new ProtobufEncoder())
                .addLast(new TransportProtocolInvocation());
        initChannel0(socketChannel);
    }

    protected void initChannel0(NioSocketChannel socketChannel) {
        socketChannel.pipeline().addLast(new ConnectionStateWatchDog(nettyClient::reconnect, properties.getApplicationType()));
    }
}

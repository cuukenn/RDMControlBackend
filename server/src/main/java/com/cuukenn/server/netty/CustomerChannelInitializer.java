package com.cuukenn.server.netty;

import com.cuukenn.common.netty.protocol.TransportProtocolInvocation;
import com.cuukenn.server.netty.config.ServerNettyProperties;
import com.cuukenn.server.netty.handler.bound.ConnectionHolderTrigger;
import com.cuukenn.server.netty.handler.bound.ConnectorIdleStateTrigger;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
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
public class CustomerChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final ServerNettyProperties properties;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline()
                .addLast(
                        new IdleStateHandler(
                                properties.getReaderIdleTimeSeconds(),
                                0,
                                0,
                                TimeUnit.SECONDS
                        ))
                .addLast(new ConnectorIdleStateTrigger())
                .addLast(new ProtobufVarint32FrameDecoder())
                .addLast(new ProtobufDecoder(Message.TransportProtocol.getDefaultInstance()))
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                .addLast(new ProtobufEncoder())
                .addLast(new ConnectionHolderTrigger())
                .addLast(new TransportProtocolInvocation());
    }
}

package com.cuukenn.common.netty.handler;

import com.cuukenn.common.netty.protocol.TransportProtocolInvocation;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import lombok.RequiredArgsConstructor;
import protocol.Message;

/**
 * @author changgg
 */
@RequiredArgsConstructor
public class CustomerChannelInitializer extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel socketChannel) throws Exception {
        initChannel0(socketChannel);
        initChannel1(socketChannel);
        initChannel2(socketChannel);
    }

    protected void initChannel0(NioSocketChannel socketChannel) {
    }

    protected void initChannel1(NioSocketChannel socketChannel) {
        socketChannel.pipeline()
                .addLast(new ProtobufVarint32FrameDecoder())
                .addLast(new ProtobufDecoder(Message.TransportProtocol.getDefaultInstance()))
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                .addLast(new ProtobufEncoder());
    }

    protected void initChannel2(NioSocketChannel socketChannel) {
        socketChannel.pipeline().addLast(new TransportProtocolInvocation());
    }
}

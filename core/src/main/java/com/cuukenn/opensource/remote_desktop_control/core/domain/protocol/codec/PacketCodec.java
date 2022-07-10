package com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.codec;

import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.Packet;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.serialization.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

/**
 * @author changgg
 */
public class PacketCodec extends ByteToMessageCodec<Packet> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        byte[] data = SerializationUtil.serialize(msg);
        out.writeInt(1 + data.length);
        out.writeByte(msg.getType());
        out.writeBytes(data);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < Integer.BYTES) {
            return;
        }
        in.markReaderIndex();
        int dataLength = in.readInt();
        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }
        //读取类型
        byte type = in.readByte();
        //类型占了一位，剔除掉
        byte[] data = new byte[dataLength - 1];
        in.readBytes(data);
        out.add(SerializationUtil.deserialize(data, Packet.get(type)));
    }
}

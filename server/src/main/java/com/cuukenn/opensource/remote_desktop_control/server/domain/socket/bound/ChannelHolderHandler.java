package com.cuukenn.opensource.remote_desktop_control.server.domain.socket.bound;

import com.cuukenn.opensource.remote_desktop_control.core.infrastructure.enums.ApplicationType;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.AbstractMetadataPacket;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.util.IdUtil;
import com.cuukenn.opensource.remote_desktop_control.server.infrastructure.util.ChannelHolderUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author changgg
 */
@Slf4j
public class ChannelHolderHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        read0(ctx, msg);
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ChannelHolderUtil.offline(ctx.channel());
        super.channelInactive(ctx);
    }

    private void read0(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof AbstractMetadataPacket) {
            ApplicationType type = IdUtil.getType(((AbstractMetadataPacket) msg).getId());
            switch (type) {
                case CLIENT:
                    ChannelHolderUtil.online(IdUtil.getMachineId(((AbstractMetadataPacket) msg).getId()), ctx.channel());
                    break;
                case PUPPET:
                    ChannelHolderUtil.online(((AbstractMetadataPacket) msg).getPuppetId(), ctx.channel());
                    break;
                default:
                    break;
            }
        }
    }
}

package com.cuukenn.opensource.remote_desktop_control.puppet.domain.socket.bound;

import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.AbstractMetadataPacket;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.PackUtil;
import com.cuukenn.opensource.remote_desktop_control.core.domain.socket.bound.IdInfoRegisterHandler;
import com.cuukenn.opensource.remote_desktop_control.puppet.domain.socket.config.NetworkProperties;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

/**
 * @author changgg
 */
public class PuppetIdRegisterHandler extends IdInfoRegisterHandler {
    public PuppetIdRegisterHandler(NetworkProperties properties) {
        super(properties);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof AbstractMetadataPacket) {
            PackUtil.fillPuppetId(((AbstractMetadataPacket) msg), ((NetworkProperties) properties).getPuppetId());
        }
        super.write(ctx, msg, promise);
    }
}

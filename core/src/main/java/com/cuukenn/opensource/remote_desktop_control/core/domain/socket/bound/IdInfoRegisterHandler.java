package com.cuukenn.opensource.remote_desktop_control.core.domain.socket.bound;

import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.AbstractMetadataPacket;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.PackUtil;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.util.IdUtil;
import com.cuukenn.opensource.remote_desktop_control.core.domain.socket.config.BaseNetworkProperties;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.RequiredArgsConstructor;

/**
 * @author changgg
 */
@RequiredArgsConstructor
public class IdInfoRegisterHandler extends ChannelOutboundHandlerAdapter {
    protected final BaseNetworkProperties properties;

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof AbstractMetadataPacket) {
            PackUtil.fillId(((AbstractMetadataPacket) msg), IdUtil.generateId(properties.getApplicationType()));
        }
        super.write(ctx, msg, promise);
    }
}

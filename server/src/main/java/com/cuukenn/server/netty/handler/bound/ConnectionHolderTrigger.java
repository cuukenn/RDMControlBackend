package com.cuukenn.server.netty.handler.bound;

import cn.hutool.core.util.StrUtil;
import com.cuukenn.common.netty.enums.ApplicationType;
import com.cuukenn.common.netty.util.IdUtil;
import com.cuukenn.server.netty.pojo.ChannelPair;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import protocol.Message;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author changgg
 */
@Slf4j
public class ConnectionHolderTrigger extends ChannelInboundHandlerAdapter {
    private static final Map<String, ChannelPair> CONNECTED_CHANNEL_PAIRS = new ConcurrentHashMap<>();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        read0(ctx, msg);
        super.channelRead(ctx, msg);
    }

    private void read0(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof Message.TransportProtocol) {
            Message.TransportProtocol protocol = (Message.TransportProtocol) msg;
            if (StrUtil.isBlank(protocol.getPuppetName())) {
                return;
            }
            ApplicationType type = IdUtil.getType(protocol.getId());
            if (type == ApplicationType.PUPPET) {
                CONNECTED_CHANNEL_PAIRS.computeIfAbsent(protocol.getPuppetName(), (key) -> new ChannelPair()).setPuppet(ctx.channel());
            }
        }
    }

    /**
     * 根据puppetName获取连接对
     *
     * @param puppetName 傀儡机名称
     * @return 连接对
     */
    public static ChannelPair getChannelPair(String puppetName) {
        return CONNECTED_CHANNEL_PAIRS.get(puppetName);
    }

    /**
     * 移除非活动连接对
     *
     * @return 失效channelPair条数
     */
    public static int removeInactiveChannelPair() {
        int total = 0;
        Iterator<Map.Entry<String, ChannelPair>> iterator = CONNECTED_CHANNEL_PAIRS.entrySet().iterator();
        while (iterator.hasNext()) {
            ChannelPair channelPair = iterator.next().getValue();
            Channel client = channelPair.getClient();
            Channel puppet = channelPair.getPuppet();
            if (!puppet.isActive()) {
                iterator.remove();
                total++;
                break;
            }
            if (!client.isActive()) {
                channelPair.setClient(null);
                total++;
            }
        }
        return total;
    }
}

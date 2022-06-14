package com.cuukenn.server.netty.pojo;

import io.netty.channel.Channel;
import lombok.Data;

/**
 * @author changgg
 */
@Data
public class ChannelPair {
    private Channel client;
    private Channel puppet;
}

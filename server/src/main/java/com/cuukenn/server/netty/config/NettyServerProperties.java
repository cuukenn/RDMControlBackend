package com.cuukenn.server.netty.config;

import com.cuukenn.common.netty.config.NettyBaseProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * netty服务端配置
 *
 * @author changgg
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NettyServerProperties extends NettyBaseProperties {
    public NettyServerProperties() {
        setApplicationType(ApplicationType.SERVER);
    }

    private int port = 7000;
    private int readerIdleTimeSeconds = 10;
}

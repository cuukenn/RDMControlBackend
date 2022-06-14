package com.cuukenn.common.netty.client.config;

import com.cuukenn.common.netty.config.NettyBaseProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * nett客户端配置
 *
 * @author changgg
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NettyClientProperties extends NettyBaseProperties {
    public NettyClientProperties() {
        setApplicationType(ApplicationType.CLIENT);
    }

    private String serverHost = "127.0.0.1";
    private int serverPort = 7000;
    private int writerIdleTimeSeconds = 8;
}

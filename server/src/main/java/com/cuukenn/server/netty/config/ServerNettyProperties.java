package com.cuukenn.server.netty.config;

import com.cuukenn.common.netty.config.BaseNettyProperties;
import com.cuukenn.common.netty.enums.ApplicationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * netty服务端配置
 *
 * @author changgg
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServerNettyProperties extends BaseNettyProperties {
    public ServerNettyProperties() {
        setApplicationType(ApplicationType.SERVER);
    }

    private int port = 7000;
    private int readerIdleTimeSeconds = 10;
}

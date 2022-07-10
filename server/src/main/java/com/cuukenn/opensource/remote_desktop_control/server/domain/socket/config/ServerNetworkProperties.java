package com.cuukenn.opensource.remote_desktop_control.server.domain.socket.config;

import com.cuukenn.opensource.remote_desktop_control.core.domain.socket.config.BaseNetworkProperties;
import com.cuukenn.opensource.remote_desktop_control.core.infrastructure.enums.ApplicationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * netty服务端配置
 *
 * @author changgg
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServerNetworkProperties extends BaseNetworkProperties {
    public ServerNetworkProperties() {
        setApplicationType(ApplicationType.SERVER);
    }

    private int port = 7000;
    private int readerIdleTimeSeconds = 10;
}

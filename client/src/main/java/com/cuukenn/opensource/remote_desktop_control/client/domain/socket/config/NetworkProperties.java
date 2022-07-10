package com.cuukenn.opensource.remote_desktop_control.client.domain.socket.config;

import com.cuukenn.opensource.remote_desktop_control.core.infrastructure.enums.ApplicationType;
import com.cuukenn.opensource.remote_desktop_control.core.domain.socket.client.config.BaseNetworkClientProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * nett客户端配置
 *
 * @author changgg
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NetworkProperties extends BaseNetworkClientProperties {
    public NetworkProperties() {
        setApplicationType(ApplicationType.CLIENT);
    }
}

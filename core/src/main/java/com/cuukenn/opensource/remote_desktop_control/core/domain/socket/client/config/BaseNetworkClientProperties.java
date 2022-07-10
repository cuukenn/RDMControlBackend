package com.cuukenn.opensource.remote_desktop_control.core.domain.socket.client.config;

import com.cuukenn.opensource.remote_desktop_control.core.domain.socket.config.BaseNetworkProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author changgg
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseNetworkClientProperties extends BaseNetworkProperties {
    private String serverAddress = "127.0.0.1";
    private int writerIdleTimeSeconds = 8;
    private String puppetId;
}

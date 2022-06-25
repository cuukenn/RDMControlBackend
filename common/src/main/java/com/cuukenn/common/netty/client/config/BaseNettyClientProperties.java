package com.cuukenn.common.netty.client.config;

import com.cuukenn.common.netty.config.BaseNettyProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author changgg
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseNettyClientProperties extends BaseNettyProperties {
    private String serverAddress = "127.0.0.1";
    private int writerIdleTimeSeconds = 8;
}

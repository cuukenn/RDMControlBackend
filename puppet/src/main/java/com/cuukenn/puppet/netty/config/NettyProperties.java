package com.cuukenn.puppet.netty.config;

import com.cuukenn.common.netty.client.config.BaseNettyClientProperties;
import com.cuukenn.common.netty.enums.ApplicationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * nett客户端配置
 *
 * @author changgg
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NettyProperties extends BaseNettyClientProperties {
    public NettyProperties() {
        setApplicationType(ApplicationType.PUPPET);
    }
}

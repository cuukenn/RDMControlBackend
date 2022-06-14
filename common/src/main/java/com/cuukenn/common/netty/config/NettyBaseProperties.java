package com.cuukenn.common.netty.config;

import lombok.Data;

/**
 * @author changgg
 */
@Data
public class NettyBaseProperties {
    private ApplicationType applicationType;

    public enum ApplicationType {
        /**
         * netty客户端
         */
        CLIENT,
        /**
         * netty服务端
         */
        SERVER
    }
}

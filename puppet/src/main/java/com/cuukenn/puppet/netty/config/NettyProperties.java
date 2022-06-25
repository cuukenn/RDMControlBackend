package com.cuukenn.puppet.netty.config;

import cn.hutool.core.util.IdUtil;
import com.cuukenn.common.netty.client.config.BaseNettyClientProperties;
import com.cuukenn.common.netty.enums.ApplicationType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * nett客户端配置
 *
 * @author changgg
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class NettyProperties extends BaseNettyClientProperties {
    private String puppetName = IdUtil.fastUUID();

    {
        log.info("puppetName:{}", puppetName);
    }

    public NettyProperties() {
        setApplicationType(ApplicationType.PUPPET);
    }
}

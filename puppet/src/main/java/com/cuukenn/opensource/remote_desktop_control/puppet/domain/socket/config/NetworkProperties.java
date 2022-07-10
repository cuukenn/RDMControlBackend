package com.cuukenn.opensource.remote_desktop_control.puppet.domain.socket.config;

import cn.hutool.core.util.IdUtil;
import com.cuukenn.opensource.remote_desktop_control.core.domain.socket.client.config.BaseNetworkClientProperties;
import com.cuukenn.opensource.remote_desktop_control.core.infrastructure.enums.ApplicationType;
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
public class NetworkProperties extends BaseNetworkClientProperties {
    public NetworkProperties() {
        setApplicationType(ApplicationType.PUPPET);
        this.setPuppetId(IdUtil.fastUUID());
    }
}

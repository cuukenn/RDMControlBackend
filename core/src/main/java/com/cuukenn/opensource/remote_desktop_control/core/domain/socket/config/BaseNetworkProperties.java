package com.cuukenn.opensource.remote_desktop_control.core.domain.socket.config;

import com.cuukenn.opensource.remote_desktop_control.core.infrastructure.enums.ApplicationType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * @author changgg
 */
@Data
public abstract class BaseNetworkProperties {
    @Setter(AccessLevel.PROTECTED)
    private ApplicationType applicationType;
    private int port = 7000;
}

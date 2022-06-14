package com.cuukenn.common.netty.config;

import com.cuukenn.common.netty.enums.ApplicationType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * @author changgg
 */
@Data
public abstract class BaseNettyProperties {
    @Setter(AccessLevel.PROTECTED)
    private ApplicationType applicationType;
    private int port = 7000;
}

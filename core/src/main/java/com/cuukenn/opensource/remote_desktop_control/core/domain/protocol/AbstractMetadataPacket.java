package com.cuukenn.opensource.remote_desktop_control.core.domain.protocol;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author changgg
 */
@EqualsAndHashCode(callSuper = true)
@ToString
public abstract class AbstractMetadataPacket extends Packet {
    @Setter(AccessLevel.MODULE)
    @Getter
    private String id;
    @Setter(AccessLevel.MODULE)
    @Getter
    private String puppetId;
}

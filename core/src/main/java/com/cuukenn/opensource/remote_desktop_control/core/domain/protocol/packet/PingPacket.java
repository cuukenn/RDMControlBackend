package com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet;

import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.AbstractMetadataPacket;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author changgg
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@NoArgsConstructor
public class PingPacket extends AbstractMetadataPacket {
    private static final long serialVersionUID = -1L;

    @Override
    public byte getType() {
        return 2;
    }
}

package com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet;

import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.AbstractMetadataPacket;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class ErrorPacket extends AbstractMetadataPacket {
    private static final long serialVersionUID = -1L;
    private Integer code;
    private String message;

    @Override
    public byte getType() {
        return 5;
    }
}

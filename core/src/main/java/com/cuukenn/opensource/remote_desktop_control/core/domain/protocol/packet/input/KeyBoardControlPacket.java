package com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.input;

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
public class KeyBoardControlPacket extends AbstractMetadataPacket {
    private static final long serialVersionUID = -1L;
    /**
     * 是否按住alt
     */
    private boolean altDown;
    /**
     * 是否按住ctrl
     */
    private boolean ctrlDown;
    /**
     * 是否按住shift
     */
    private boolean shiftDown;
    /**
     * 是否按住截屏键
     */
    private boolean shortcutDown;
    private Integer keyCode;

    @Override
    public byte getType() {
        return 4;
    }
}

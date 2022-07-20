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
@ToString(callSuper = true)
@NoArgsConstructor
public class MouseControlPacket extends AbstractMetadataPacket {
    private static final long serialVersionUID = -1L;
    private int[] point;
    /**
     * 鼠标按键
     */
    private Integer mouseButton;
    /**
     * 双击
     */
    private boolean doubleClick;
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

    public MouseButton getMouseButton(Integer code) {
        if (code == null) {
            return MouseButton.NONE;
        }
        for (MouseButton type : MouseButton.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return MouseButton.NONE;
    }

    @Override
    public byte getType() {
        return 3;
    }
}

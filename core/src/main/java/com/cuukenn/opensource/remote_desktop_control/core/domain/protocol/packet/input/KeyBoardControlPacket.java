package com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.input;

import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.AbstractMetadataPacket;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author changgg
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@NoArgsConstructor
public class KeyBoardControlPacket extends AbstractMetadataPacket {
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
    private int keyStatus;
    private int keyCode;

    public KeyStatus getKeyStatus(Integer code) {
        if (code == null) {
            return KeyStatus.NULL;
        }
        for (KeyStatus type : KeyStatus.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return KeyStatus.NULL;
    }

    @Override
    public byte getType() {
        return 4;
    }

    /**
     * 键位状态
     */
    @RequiredArgsConstructor
    @Getter
    public enum KeyStatus {
        NULL(0),
        /**
         * 按下
         */
        PRESSED(1),
        /**
         * 松开
         */
        RELEASED(2),
        /**
         * 按下后松开
         */
        TYPED(3);
        private final int code;
    }
}

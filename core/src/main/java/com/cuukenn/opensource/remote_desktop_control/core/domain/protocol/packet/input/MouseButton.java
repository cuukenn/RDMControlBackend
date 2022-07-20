package com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.input;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author changgg
 */
@RequiredArgsConstructor
@Getter
public enum MouseButton {
    /**
     * 无操作
     */
    NONE(0),
    /**
     * 鼠标左键
     */
    LEFT(1),
    /**
     * 鼠标右键
     */
    RIGHT(2),

    /**
     * 鼠标滚轮
     */
    MIDDLE(3);
    private final int code;

    public static MouseButton transform(Integer code) {
        if (code == null) {
            return NONE;
        }
        for (MouseButton type : MouseButton.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return NONE;
    }
}

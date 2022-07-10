package com.cuukenn.opensource.remote_desktop_control.core.infrastructure.enums;

import lombok.Getter;

/**
 * @author changgg
 */
public enum ApplicationType {
    //未知
    NULL('N'),
    //服务端
    SERVER('S'),
    //客户端
    CLIENT('C'),
    //傀儡端
    PUPPET('P');
    @Getter
    private final char value;

    ApplicationType(char value) {
        this.value = value;
    }

    public static ApplicationType resolve(char value) {
        for (ApplicationType type : ApplicationType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return ApplicationType.NULL;
    }
}

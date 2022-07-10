package com.cuukenn.opensource.remote_desktop_control.core.infrastructure.exception;

import lombok.Data;

/**
 * @author changgg
 */
@Data
public class BizException extends RuntimeException {
    private Integer code;

    public BizException(String message) {
        super(message);
        this.code = -200;
    }

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}

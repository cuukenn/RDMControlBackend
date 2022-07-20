package com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.serializer;

import com.cuukenn.opensource.remote_desktop_control.core.infrastructure.exception.BizException;

/**
 * @author changgg
 */
public class SerializeException extends BizException {
    private static final long serialVersionUID = -4572957553877879163L;

    public SerializeException(String message) {
        this(message, null);
    }

    public SerializeException(String message, Throwable throwable) {
        super(-600, message, throwable);
    }
}

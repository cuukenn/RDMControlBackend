package com.cuukenn.common.netty.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import com.cuukenn.common.netty.enums.ApplicationType;
import io.netty.util.internal.MacAddressUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author changgg
 */
public final class IdUtil {
    private static final AtomicInteger SEQUENCE = new AtomicInteger();
    private static final String MACHINE_ID = Base64.encode(MacAddressUtil.defaultMachineId());

    public static String generateId(ApplicationType type) {
        return getId(type, MACHINE_ID, SEQUENCE.getAndIncrement());
    }

    public static String getId(ApplicationType type, String mac, Integer sequence) {
        return type.getValue() + mac + sequence;
    }

    public static ApplicationType getType(String id) {
        if (StrUtil.isEmpty(id)) {
            return ApplicationType.NULL;
        }
        return ApplicationType.resolve(id.charAt(0));
    }
}

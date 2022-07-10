package com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.util;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import com.cuukenn.opensource.remote_desktop_control.core.infrastructure.enums.ApplicationType;
import io.netty.util.internal.MacAddressUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author changgg
 */
public final class IdUtil {
    private static final AtomicInteger SEQUENCE = new AtomicInteger();
    private static final String MACHINE_ID = HexUtil.encodeHexStr(MacAddressUtil.defaultMachineId());
    private static final String SPLIT = "_";

    public static String generateId(ApplicationType type) {
        return getId(type, MACHINE_ID, SEQUENCE.getAndIncrement());
    }

    public static String getId(ApplicationType type, String mac, Integer sequence) {
        return type.getValue() + SPLIT + mac + SPLIT + sequence;
    }

    public static String getMachineId(String id) {
        String[] ids = id.split(SPLIT);
        return ids[0] + ids[1];
    }

    public static ApplicationType getType(String id) {
        if (StrUtil.isEmpty(id)) {
            return ApplicationType.NULL;
        }
        return ApplicationType.resolve(id.charAt(0));
    }
}

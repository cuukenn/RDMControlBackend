package com.cuukenn.opensource.remote_desktop_control.core.domain.protocol;

import com.cuukenn.opensource.remote_desktop_control.core.infrastructure.exception.BizException;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author changgg
 */
@Slf4j
public abstract class Packet implements Serializable {
    private final static Map<Byte, Class<? extends Packet>> PACKET_TYPES = new ConcurrentHashMap<>();

    static {
        int count = 0;
        ServiceLoader<Packet> services = ServiceLoader.load(Packet.class);
        for (Packet packet : services) {
            log.info("load packet,[{}.{}]", packet.getType(), packet.getClass().getSimpleName());
            PACKET_TYPES.put(packet.getType(), packet.getClass());
            count++;
        }
        if (count < PACKET_TYPES.size()) {
            throw new BizException("exist some packet type");
        }
    }

    public static Class<? extends Packet> get(Byte command) {
        Class<? extends Packet> aClass = PACKET_TYPES.get(command);
        if (aClass == null) {
            throw new BizException(-305, "can not found the command type:" + command);
        }
        return aClass;
    }

    /**
     * 获取协议指令
     *
     * @return 返回指令值
     */
    abstract public byte getType();
}

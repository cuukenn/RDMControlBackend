package com.cuukenn.opensource.remote_desktop_control.core.domain.protocol;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author changgg
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PackUtil {
    public static void fillId(AbstractMetadataPacket packet, String id) {
        if (packet != null) {
            packet.setId(id);
        }
    }

    public static void fillPuppetId(AbstractMetadataPacket packet, String puppetId) {
        if (packet != null) {
            packet.setPuppetId(puppetId);
        }
    }
}

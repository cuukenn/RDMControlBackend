package com.cuukenn.opensource.remote_desktop_control.client.domain.ui.event;

import com.cuukenn.opensource.remote_desktop_control.core.domain.event.IEvent;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.input.MouseControlPacket;
import lombok.Data;
import lombok.ToString;

/**
 * @author changgg
 */
@Data
@ToString(callSuper = true)
public class MouseControlEvent implements IEvent {
    private final MouseControlPacket packet;
}

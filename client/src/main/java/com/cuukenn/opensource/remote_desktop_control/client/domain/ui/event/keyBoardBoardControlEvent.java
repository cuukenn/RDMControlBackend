package com.cuukenn.opensource.remote_desktop_control.client.domain.ui.event;

import com.cuukenn.opensource.remote_desktop_control.core.domain.event.IEvent;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.input.KeyBoardControlPacket;
import lombok.Data;
import lombok.ToString;

/**
 * @author changgg
 */
@Data
@ToString
public class keyBoardBoardControlEvent implements IEvent {
    private final KeyBoardControlPacket packet;
}

package com.cuukenn.opensource.remote_desktop_control.client.domain.ui.event;

import com.cuukenn.opensource.remote_desktop_control.core.domain.event.IEvent;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author changgg
 */
@Data
@RequiredArgsConstructor
public class DisconnectPuppetEvent implements IEvent {
    private final String puppetId;
}

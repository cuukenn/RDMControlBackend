package com.cuukenn.opensource.remote_desktop_control.server.interfaces.inbound.invocation;

import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.input.MouseControlPacket;
import org.springframework.stereotype.Service;

/**
 * 处理消息
 *
 * @author changgg
 */
@Service
public class MouseControlInvocation extends AbstractForwardMessageInvocation<MouseControlPacket> {
}
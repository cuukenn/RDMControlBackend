package com.cuukenn.opensource.remote_desktop_control.client.domain.ui.listener;

import com.cuukenn.opensource.remote_desktop_control.client.domain.ui.event.MouseControlEvent;
import com.cuukenn.opensource.remote_desktop_control.core.domain.event.EventFactory;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.input.MouseButton;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.input.MouseControlPacket;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author changgg
 */
@Slf4j
@RequiredArgsConstructor
public class MouseEventHandler implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
        log.debug("{}", event);
        MouseControlPacket packet = new MouseControlPacket();
        //鼠标坐标
        packet.setPoint(new int[]{(int) event.getX(), (int) event.getY()});
        //组合键状态
        packet.setCtrlDown(event.isControlDown());
        packet.setShiftDown(event.isShiftDown());
        packet.setAltDown(event.isAltDown());
        //连击数量
        packet.setDoubleClick(event.getClickCount() >= 2);
        //逐鼠标按键
        switch (event.getButton()) {
            case PRIMARY:
                packet.setMouseButton(MouseButton.LEFT.getCode());
                break;
            case SECONDARY:
                packet.setMouseButton(MouseButton.RIGHT.getCode());
            case MIDDLE:
                packet.setMouseButton(MouseButton.MIDDLE.getCode());
                break;
            default:
                packet.setMouseButton(MouseButton.NONE.getCode());
                break;
        }
        EventFactory.postAsync(new MouseControlEvent(packet));
    }
}

package com.cuukenn.opensource.remote_desktop_control.client.domain.ui.listener;

import com.cuukenn.opensource.remote_desktop_control.client.domain.ui.event.keyBoardBoardControlEvent;
import com.cuukenn.opensource.remote_desktop_control.core.domain.event.EventFactory;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.input.KeyBoardControlPacket;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author changgg
 */
@Slf4j
public class KeyboardEventHandler implements EventHandler<KeyEvent> {
    @Override
    public void handle(KeyEvent event) {
        log.debug("key:{}", event);
        KeyBoardControlPacket packet = new KeyBoardControlPacket();
        packet.setCtrlDown(event.isControlDown());
        packet.setShiftDown(event.isShiftDown());
        packet.setAltDown(event.isAltDown());
        packet.setShortcutDown(event.isShortcutDown());
        if (event.getCode() != KeyCode.UNDEFINED) {
            packet.setKeyCode(event.getCode().getCode());
        }
        EventFactory.postAsync(new keyBoardBoardControlEvent(packet));
        event.consume();
    }
}

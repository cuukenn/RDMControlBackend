package com.cuukenn.opensource.remote_desktop_control.client.domain.ui.listener;

import com.cuukenn.opensource.remote_desktop_control.client.domain.ui.event.keyBoardBoardControlEvent;
import com.cuukenn.opensource.remote_desktop_control.core.domain.event.EventFactory;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.input.KeyBoardControlPacket;
import javafx.event.EventHandler;
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
        //按键类型
        if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            packet.setKeyStatus(KeyBoardControlPacket.KeyStatus.PRESSED.getCode());
        } else if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
            packet.setKeyStatus(KeyBoardControlPacket.KeyStatus.RELEASED.getCode());
        } else if (event.getEventType().equals(KeyEvent.KEY_TYPED)) {
            packet.setKeyStatus(KeyBoardControlPacket.KeyStatus.TYPED.getCode());
        } else {
            packet.setKeyStatus(KeyBoardControlPacket.KeyStatus.NULL.getCode());
        }
        packet.setKeyCode(event.getCode().getCode());
        EventFactory.postAsync(new keyBoardBoardControlEvent(packet));
    }
}

package com.cuukenn.opensource.remote_desktop_control.puppet.domain.screen;

import cn.hutool.core.io.FileUtil;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.input.KeyBoardControlPacket;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.input.MouseButton;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.input.MouseControlPacket;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @author changgg
 */
@Slf4j
public class DefaultReplayTest {
    @Test
    @SneakyThrows
    public void test_move_mouse() {
        IReplay replay = new DefaultReplay();
        {
            MouseControlPacket packet = new MouseControlPacket();
            packet.setPoint(new int[]{100, 100});
            replay.mouseAction(packet);
        }
        TimeUnit.SECONDS.sleep(1);
        {
            MouseControlPacket packet = new MouseControlPacket();
            packet.setPoint(new int[]{200, 200});
            replay.mouseAction(packet);
        }
    }

    @Test
    public void test_mouse_left_click() {
        IReplay replay = new DefaultReplay();
        MouseControlPacket packet = new MouseControlPacket();
        packet.setPoint(new int[]{100, 100});
        packet.setMouseButton(MouseButton.LEFT.getCode());
        replay.mouseAction(packet);
    }

    @Test
    public void test_mouse_right_click() {
        IReplay replay = new DefaultReplay();
        MouseControlPacket packet = new MouseControlPacket();
        packet.setPoint(new int[]{100, 100});
        packet.setMouseButton(MouseButton.RIGHT.getCode());
        replay.mouseAction(packet);
    }

    @Test
    public void test_mouse_left_click_double() {
        IReplay replay = new DefaultReplay();
        MouseControlPacket packet = new MouseControlPacket();
        packet.setPoint(new int[]{100, 100});
        packet.setMouseButton(MouseButton.LEFT.getCode());
        packet.setDoubleClick(true);
        replay.mouseAction(packet);
    }

    @Test
    public void test_mouse_right_click_double() {
        IReplay replay = new DefaultReplay();
        MouseControlPacket packet = new MouseControlPacket();
        packet.setPoint(new int[]{100, 100});
        packet.setMouseButton(MouseButton.LEFT.getCode());
        packet.setDoubleClick(true);
        replay.mouseAction(packet);
    }

    @Test
    public void test_key_pressed() {
        IReplay replay = new DefaultReplay();
        KeyBoardControlPacket packet = new KeyBoardControlPacket();
        packet.setKeyCode(KeyEvent.VK_S);
        replay.keyBoardAction(packet);
    }

    @Test
    public void test_screen_snapshot() {
        IReplay replay = new DefaultReplay();
        log.info("image byte {}", replay.getScreenSnapshot().length);
    }

    @Test
    public void test_screen_snapshot_file() {
        IReplay replay = new DefaultReplay();
        File file = new File("target\\snapshot.png");
        FileUtil.writeBytes(replay.getScreenSnapshot(), file);
        log.info("path:{}", file.getAbsoluteFile());
    }
}
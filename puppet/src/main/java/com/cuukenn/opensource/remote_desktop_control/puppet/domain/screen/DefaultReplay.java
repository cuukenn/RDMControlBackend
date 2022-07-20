package com.cuukenn.opensource.remote_desktop_control.puppet.domain.screen;

import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.input.KeyBoardControlPacket;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.input.MouseButton;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.input.MouseControlPacket;
import com.cuukenn.opensource.remote_desktop_control.core.infrastructure.util.JavacvUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author changgg
 */
@Service
@Slf4j
public class DefaultReplay implements IReplay {
    private final Robot robot;

    public DefaultReplay() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void mouseAction(MouseControlPacket packet) {
        log.debug("action:{}", packet);
        if (packet.getPoint() != null) {
            robot.mouseMove(packet.getPoint()[0], packet.getPoint()[1]);
        }
        MouseButton button = MouseButton.transform(packet.getMouseButton());
        switch (button) {
            case LEFT:
                mouseClick(InputEvent.BUTTON1_DOWN_MASK, packet.isDoubleClick());
                break;
            case RIGHT:
                mouseClick(InputEvent.BUTTON3_DOWN_MASK, packet.isDoubleClick());
                break;
            case MIDDLE:
                mouseClick(InputEvent.BUTTON2_DOWN_MASK, packet.isDoubleClick());
                break;
            default:
                break;
        }
    }

    /**
     * click mouse
     *
     * @param buttons     mouse button
     * @param doubleClick 是否双击
     */
    private void mouseClick(int buttons, boolean doubleClick) {
        if (doubleClick) {
            robot.mousePress(buttons);
            robot.mouseRelease(buttons);
            robot.mousePress(buttons);
            robot.mouseRelease(buttons);
        } else {
            robot.mousePress(buttons);
            robot.mouseRelease(buttons);
        }
    }

    @Override
    public void keyBoardAction(KeyBoardControlPacket packet) {
        log.info("action:{}", packet);
        if (packet.isCtrlDown()) {
            robot.keyPress(KeyEvent.VK_CONTROL);
        }
        if (packet.isAltDown()) {
            robot.keyPress((KeyEvent.VK_ALT));
        }
        if (packet.isShiftDown()) {
            robot.keyPress((KeyEvent.VK_SHIFT));
        }
        if (packet.getKeyCode() != null) {
            robot.keyPress(packet.getKeyCode());
        }
        if (packet.isCtrlDown()) {
            robot.keyRelease(KeyEvent.VK_CONTROL);
        }
        if (packet.isAltDown()) {
            robot.keyRelease((KeyEvent.VK_ALT));
        }
        if (packet.isShiftDown()) {
            robot.keyRelease((KeyEvent.VK_SHIFT));
        }
        if (packet.getKeyCode() != null) {
            robot.keyRelease(packet.getKeyCode());
        }
    }

    /**
     * 反射获取pixel[]
     * pixel[]转byte[]
     * byte通过opencv转png
     *
     * @return png byte[]
     */
    @Override
    @SneakyThrows
    public byte[] getScreenSnapshot() {
        //获取屏幕分辨率
        //toolkit获取的没有计算屏幕缩放比率，与实际数据存在比例放缩
        GraphicsDevice graphDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode disMode = graphDevice.getDisplayMode();
        int width = disMode.getWidth();
        int height = disMode.getHeight();
        int[] snapshot = snapshot(width, height);
        return JavacvUtil.imgPixelByteToPng(JavacvUtil.intToByte(snapshot), width, height);
    }

    @SneakyThrows
    private int[] snapshot(int width, int height) {
        //获取robot的peerField对象
        Field peerField = robot.getClass().getDeclaredField("peer");
        peerField.setAccessible(true);
        Object peer = peerField.get(robot);
        //得到这个获取像素数组的方法
        Method getPixelsMethod = peer.getClass().getDeclaredMethod("getRGBPixels", Rectangle.class);
        getPixelsMethod.setAccessible(true);
        //获取像素数组
        return (int[]) getPixelsMethod.invoke(peer, new Rectangle(width, height));
    }
}

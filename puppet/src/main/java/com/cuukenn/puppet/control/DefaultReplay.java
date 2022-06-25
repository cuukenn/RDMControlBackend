package com.cuukenn.puppet.control;

import com.cuukenn.common.netty.util.JavacvUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import protocol.Message;

import java.awt.*;
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
    public void keyPress(Message.KeyControlMessage keyEvent) {

    }

    @Override
    public void keyRelease(Message.KeyControlMessage keyEvent) {

    }

    @Override
    public void mouseClick(Message.MouseControlMessage mouseEvent) {

    }

    @Override
    public void mouseWheel(Message.MouseControlMessage mouseEvent) {

    }

    @Override
    public void mousePress(Message.MouseControlMessage mouseEvent) {

    }

    @Override
    public void mouseRelease(Message.MouseControlMessage mouseEvent) {

    }

    @Override
    public void mouseMove(int[] site) {

    }

    @Override
    public void mouseDoubleClick(Message.MouseControlMessage mouseEvent) {

    }

    @Override
    public void mouseDragged(Message.MouseControlMessage mouseEvent, int[] site) {

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

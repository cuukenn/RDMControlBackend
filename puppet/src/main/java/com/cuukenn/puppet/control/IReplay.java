package com.cuukenn.puppet.control;

import protocol.Message;

/**
 * @author changgg
 */
public interface IReplay {
    /**
     * 键按下
     *
     * @param keyEvent
     */
    void keyPress(Message.KeyControlMessage keyEvent);

    /**
     * 键释放
     *
     * @param keyEvent
     */
    void keyRelease(Message.KeyControlMessage keyEvent);

    /**
     * 鼠标单击
     *
     * @param mouseEvent
     */
    void mouseClick(Message.MouseControlMessage mouseEvent);

    /**
     * 滚轮滚动
     *
     * @param mouseEvent
     */
    void mouseWheel(Message.MouseControlMessage mouseEvent);

    /**
     * 鼠标键按下
     *
     * @param mouseEvent
     */
    void mousePress(Message.MouseControlMessage mouseEvent);

    /**
     * 鼠标键释放
     *
     * @param mouseEvent
     */
    void mouseRelease(Message.MouseControlMessage mouseEvent);

    /**
     * 鼠标移动
     *
     * @param site
     */
    void mouseMove(int[] site);

    /**
     * 鼠标双击
     *
     * @param mouseEvent
     */
    void mouseDoubleClick(Message.MouseControlMessage mouseEvent);

    /**
     * 鼠标拖动
     *
     * @param mouseEvent
     * @param site
     */
    void mouseDragged(Message.MouseControlMessage mouseEvent, int[] site);


    /**
     * 截屏
     *
     * @return 图像字节数组
     */
    byte[] getScreenSnapshot();
}

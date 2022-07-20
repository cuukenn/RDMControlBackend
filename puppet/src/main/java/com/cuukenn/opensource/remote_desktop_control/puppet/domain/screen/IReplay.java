package com.cuukenn.opensource.remote_desktop_control.puppet.domain.screen;

import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.input.KeyBoardControlPacket;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.input.MouseControlPacket;

/**
 * @author changgg
 */
public interface IReplay {
    /**
     * 鼠标动作回放
     *
     * @param packet 协议数据
     */
    void mouseAction(MouseControlPacket packet);

    /**
     * 键盘动作回放
     *
     * @param packet 协议数据
     */
    void keyBoardAction(KeyBoardControlPacket packet);

    /**
     * 截屏
     *
     * @return 图像字节数组
     */
    byte[] getScreenSnapshot();
}

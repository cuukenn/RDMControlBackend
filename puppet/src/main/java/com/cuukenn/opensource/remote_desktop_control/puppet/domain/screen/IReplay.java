package com.cuukenn.opensource.remote_desktop_control.puppet.domain.screen;

import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.input.KeyBoardControlPacket;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.input.MouseControlPacket;

/**
 * @author changgg
 */
public interface IReplay {
    void mouseAction(MouseControlPacket packet);

    void keyBoardAction(KeyBoardControlPacket packet);

    /**
     * 截屏
     *
     * @return 图像字节数组
     */
    byte[] getScreenSnapshot();
}

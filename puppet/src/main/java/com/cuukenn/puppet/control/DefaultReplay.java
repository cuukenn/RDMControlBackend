package com.cuukenn.puppet.control;

import org.springframework.stereotype.Service;
import protocol.Message;

/**
 * @author changgg
 */
@Service
public class DefaultReplay implements IReplay {

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

    @Override
    public byte[] getScreenSnapshot() {
        return null;
    }
}

package com.cuukenn.opensource.remote_desktop_control.puppet.control;

import cn.hutool.core.io.FileUtil;
import com.cuukenn.opensource.remote_desktop_control.puppet.domain.screen.DefaultReplay;
import com.cuukenn.opensource.remote_desktop_control.puppet.domain.screen.IReplay;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * @author changgg
 */
@Slf4j
public class DefaultReplayTest {
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
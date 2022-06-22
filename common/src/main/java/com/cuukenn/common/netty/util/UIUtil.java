package com.cuukenn.common.netty.util;

import javafx.application.Platform;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author changgg
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UIUtil {
    public static void runUITask(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (Platform.isFxApplicationThread()) {
            runnable.run();
        } else {
            Platform.runLater(runnable);
        }
    }
}

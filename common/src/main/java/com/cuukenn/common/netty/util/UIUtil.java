package com.cuukenn.common.netty.util;

import javafx.application.Platform;
import javafx.scene.control.Alert;
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

    public static void errorMessage(Integer code, String message) {
        runUITask(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("异常");
            alert.setHeaderText(String.format("错误代码：%s", code));
            alert.setContentText(String.format("错误消息：%s", message));
            alert.showAndWait();
        });
    }
}

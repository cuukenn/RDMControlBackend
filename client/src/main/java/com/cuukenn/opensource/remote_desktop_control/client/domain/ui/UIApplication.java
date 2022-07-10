package com.cuukenn.opensource.remote_desktop_control.client.domain.ui;

import cn.hutool.extra.spring.SpringUtil;
import com.cuukenn.opensource.remote_desktop_control.client.domain.ui.controller.IndexController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author changgg
 */
@Slf4j
public class UIApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("控制端");
        primaryStage.setScene(new Scene(IndexController.getFXMLLoader().load()));
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> SpringUtil.getBean(IndexController.class).close(event));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        Platform.exit();
        Runtime.getRuntime().exit(0);
    }
}
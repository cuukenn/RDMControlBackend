package com.cuukenn.puppet.ui;

import com.cuukenn.puppet.ui.controller.IndexController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author changgg
 */
@Slf4j
public class UIApplication extends Application {

    @Override
    @SneakyThrows
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("傀儡端");
        primaryStage.setScene(new Scene(IndexController.getFXMLLoader().load()));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        Platform.exit();
        Runtime.getRuntime().exit(0);
    }
}
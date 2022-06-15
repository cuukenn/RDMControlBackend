package com.cuukenn.puppet.ui;

import cn.hutool.extra.spring.SpringUtil;
import com.cuukenn.common.netty.client.ui.StageController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;

/**
 * @author changgg
 */
@Slf4j
public class ViewLaunch extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            stage.setTitle("puppet");
            StageController stageController = SpringUtil.getBean(StageController.class);
            stageController.loadStage(FxmlConstant.MAIN, StageStyle.DECORATED);
            stageController.setPrimaryStage(stage);
            stageController.setStage(FxmlConstant.MAIN);
        } catch (Exception e) {
            log.error("exception", e);
            throw e;
        }
    }

    @Override
    public void stop() throws Exception {
        Runtime.getRuntime().exit(0);
    }
}
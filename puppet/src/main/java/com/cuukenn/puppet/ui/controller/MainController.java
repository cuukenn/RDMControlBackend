package com.cuukenn.puppet.ui.controller;

import cn.hutool.extra.spring.SpringUtil;
import com.cuukenn.common.netty.client.handler.NettyClient;
import com.cuukenn.common.netty.client.ui.BaseControlledStage;
import com.cuukenn.puppet.netty.config.NettyProperties;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author changgg
 */
@Slf4j
public class MainController extends BaseControlledStage implements Initializable {
    @FXML
    private Label puppetName;

    @FXML
    public void connect(ActionEvent event) {
        log.info("connect server action");
        SpringUtil.getBean(NettyClient.class).start();
    }

    @FXML
    public void disconnect(ActionEvent event) {
        log.info("disconnect server action");
        SpringUtil.getBean(NettyClient.class).stop();
    }

    @FXML
    public void startControl(ActionEvent event) {
        log.info("startControl puppet action,{}", puppetName.getText());
    }

    @FXML
    public void stopControl(ActionEvent event) {
        log.info("stopControl puppet action");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        puppetName.setText(SpringUtil.getBean(NettyProperties.class).getPuppetName());
    }
}

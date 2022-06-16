package com.cuukenn.client.ui.controller;

import cn.hutool.extra.spring.SpringUtil;
import com.cuukenn.common.netty.client.handler.NettyClient;
import com.cuukenn.common.netty.client.ui.BaseControlledStage;
import com.cuukenn.common.netty.enums.ApplicationType;
import com.cuukenn.common.netty.util.ProtocolUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;
import protocol.Message;

/**
 * @author changgg
 */
@Slf4j
public class MainController extends BaseControlledStage {
    @FXML
    private TextField puppetName;

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
        SpringUtil.getBean(NettyClient.class).getChannel()
                .ifPresent(channel -> {
                    log.info("send connect message");
                    channel.writeAndFlush(
                            ProtocolUtil.createProtocol(ApplicationType.CLIENT)
                                    .setType(Message.ProtocolType.CONNECT)
                                    .setPuppetName(puppetName.getText()).build()
                    );
                });
    }

    @FXML
    public void stopControl(ActionEvent event) {
        log.info("stopControl puppet action,{}", puppetName.getText());
        SpringUtil.getBean(NettyClient.class).getChannel()
                .ifPresent(channel -> {
                    log.info("send disconnect message");
                    channel.writeAndFlush(
                            ProtocolUtil.createProtocol(ApplicationType.CLIENT)
                                    .setType(Message.ProtocolType.DISCONNECT)
                                    .setPuppetName(puppetName.getText()).build()
                    );
                });
    }
}

package com.cuukenn.client.ui.controller;

import cn.hutool.extra.spring.SpringUtil;
import com.cuukenn.client.ui.view.IndexView;
import com.cuukenn.common.netty.client.handler.NettyClient;
import com.cuukenn.common.netty.enums.ApplicationType;
import com.cuukenn.common.netty.util.ProtocolUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import lombok.extern.slf4j.Slf4j;
import protocol.Message;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author changgg
 */
@Slf4j
public class IndexController extends IndexView {
    public static final String RESOURCE = "/fxml/index.fxml";

    public static FXMLLoader getFXMLLoader() {
        return new FXMLLoader(Objects.requireNonNull(IndexController.class.getResource(RESOURCE)));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpringUtil.registerBean(getClass().getName(), this);
    }

    @FXML
    public void connect() {
        log.info("connect to server");
        SpringUtil.getBean(NettyClient.class).start();
    }

    @FXML
    public void disconnect() {
        log.info("disconnect to server");
        SpringUtil.getBean(NettyClient.class).stop();
    }

    @FXML
    public void connectPuppet() {
        log.info("connect to puppet");
        SpringUtil.getBean(NettyClient.class).getChannel()
                .ifPresent(channel -> {
                    log.info("send connect message");
                    channel.writeAndFlush(
                            ProtocolUtil.createProtocol(ApplicationType.CLIENT)
                                    .setType(Message.ProtocolType.CONNECT)
                                    .setPuppetName(getPuppetName().getText()).build()
                    );
                });
    }

    public void disconnectPuppet() {
        SpringUtil.getBean(NettyClient.class).getChannel()
                .ifPresent(channel -> {
                    log.info("send disconnect message");
                    channel.writeAndFlush(
                            ProtocolUtil.createProtocol(ApplicationType.CLIENT)
                                    .setType(Message.ProtocolType.DISCONNECT)
                                    .setPuppetName(getPuppetName().getText()).build()
                    );
                });
    }
}

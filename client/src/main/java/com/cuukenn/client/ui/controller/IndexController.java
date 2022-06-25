package com.cuukenn.client.ui.controller;

import cn.hutool.extra.spring.SpringUtil;
import com.cuukenn.client.ui.view.IndexView;
import com.cuukenn.common.netty.client.handler.NettyClient;
import com.cuukenn.common.netty.enums.ApplicationType;
import com.cuukenn.common.netty.util.ProtocolUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.Slf4j;
import protocol.Message;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author changgg
 */
@Slf4j
public class IndexController extends IndexView {
    private volatile boolean open = false;
    public static final String RESOURCE = "/fxml/index.fxml";

    public static FXMLLoader getFXMLLoader() {
        return new FXMLLoader(Objects.requireNonNull(IndexController.class.getResource(RESOURCE)));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpringUtil.registerBean(getClass().getName(), this);
        closeScreenTab();
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
        SpringUtil.getBean(NettyClient.class).getChannelOrError()
                .ifPresent(channel -> {
                    log.info("send connect puppet message");
                    channel.writeAndFlush(
                            ProtocolUtil.createProtocol(ApplicationType.CLIENT)
                                    .setType(Message.ProtocolType.CONNECT)
                                    .setPuppetName(getPuppetName().getText()).build()
                    );
                });
    }

    @FXML
    public void disconnectPuppet() {
        SpringUtil.getBean(NettyClient.class).getChannelOrError()
                .ifPresent(channel -> {
                    log.info("send disconnect puppet message");
                    channel.writeAndFlush(
                            ProtocolUtil.createProtocol(ApplicationType.CLIENT)
                                    .setType(Message.ProtocolType.DISCONNECT)
                                    .setPuppetName(getPuppetName().getText()).build()
                    );
                });
    }

    public synchronized void openScreenTab() {
        if (!getTabPane().getTabs().contains(getScreenTab())) {
            getTabPane().getTabs().add(getScreenTab());
        }
        getTabPane().getSelectionModel().select(getScreenTab());
        open = true;
    }

    public synchronized void closeScreenTab() {
        getTabPane().getTabs().remove(getScreenTab());
        open = false;
    }

    public void refreshScreenImage(byte[] bytes) {
        if (!open) {
            return;
        }
        getImageView().setImage(new Image(new ByteArrayInputStream(bytes)));
    }

    @FXML
    public void onKeyPressed(KeyEvent event) {
        if (getScreenTab().isSelected()) {
            log.info("onKeyPressed,event:{}", event);
        }
    }

    @FXML
    public void onMouseClicked(MouseEvent event) {
        log.info("onMouseClicked,event:{}", event);
    }
}

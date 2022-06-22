package com.cuukenn.puppet.ui.controller;

import cn.hutool.extra.spring.SpringUtil;
import com.cuukenn.puppet.netty.config.NettyProperties;
import com.cuukenn.puppet.ui.view.IndexView;
import javafx.fxml.FXMLLoader;
import lombok.extern.slf4j.Slf4j;

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
        getPuppetName().setText(SpringUtil.getBean(NettyProperties.class).getPuppetName());
    }
}

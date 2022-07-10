package com.cuukenn.opensource.remote_desktop_control.client.domain.ui.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.cuukenn.opensource.remote_desktop_control.client.domain.ui.event.DisconnectPuppetEvent;
import com.cuukenn.opensource.remote_desktop_control.client.domain.ui.listener.KeyboardEventHandler;
import com.cuukenn.opensource.remote_desktop_control.client.domain.ui.listener.MouseEventHandler;
import com.cuukenn.opensource.remote_desktop_control.client.domain.ui.view.ScreenView;
import com.cuukenn.opensource.remote_desktop_control.core.domain.event.EventFactory;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.ScreenPacket;
import com.cuukenn.opensource.remote_desktop_control.core.infrastructure.util.RingBuffer;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;
import lombok.Setter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author changgg
 */
public class ScreenController extends ScreenView {
    private volatile boolean isOpen;
    @Setter
    private String puppetId;
    public static final String RESOURCE = "/fxml/screen.fxml";
    private final RingBuffer<ScreenPacket> screenSnapshots = new RingBuffer<>(120);
    private final AnimationTimer refreshTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            ScreenPacket screenSnapshot = screenSnapshots.take();
            if (screenSnapshot != null) {
                drawScreen(screenSnapshot);
            }
        }
    };

    public static FXMLLoader getFXMLLoader() {
        return new FXMLLoader(Objects.requireNonNull(IndexController.class.getResource(RESOURCE)));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getPane().addEventHandler(KeyEvent.ANY, new KeyboardEventHandler());
        getCanvas().addEventHandler(MouseEvent.ANY, new MouseEventHandler());
        isOpen = true;
        refreshTimer.start();
        //drawScreen(new ScreenPacket("test001", 2, 2, FileUtil.readBytes(new File("C:\\Users\\cuukenn\\Desktop\\screensnapshot.png"))));
    }

    public void refreshScreen(ScreenPacket packet) {
        screenSnapshots.put(packet);
    }

    private void drawScreen(ScreenPacket packet) {
        Image image = new Image(new ByteArrayInputStream(packet.getScreenSnapshot()));
        double width = image.getWidth() / packet.getScaleX();
        double height = image.getHeight() / packet.getScaleY();
        if (getCanvas().getHeight() != height) {
            getCanvas().setHeight(height);
        }
        if (getCanvas().getWidth() != width) {
            getCanvas().setWidth(width);
        }
        getCanvas().getGraphicsContext2D().drawImage(image, 0.0, 0.0, width, height);
    }

    public synchronized void close(WindowEvent event) {
        if (!isOpen) {
            return;
        }
        SpringUtil.getBean(IndexController.class).closeScreenControl(puppetId);
        EventFactory.postAsync(new DisconnectPuppetEvent(puppetId));
    }
}

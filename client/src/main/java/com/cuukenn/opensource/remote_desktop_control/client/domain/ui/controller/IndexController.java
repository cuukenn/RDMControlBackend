package com.cuukenn.opensource.remote_desktop_control.client.domain.ui.controller;

import com.cuukenn.opensource.remote_desktop_control.client.domain.ui.event.ConnectEvent;
import com.cuukenn.opensource.remote_desktop_control.client.domain.ui.event.ConnectPuppetEvent;
import com.cuukenn.opensource.remote_desktop_control.client.domain.ui.event.DisconnectEvent;
import com.cuukenn.opensource.remote_desktop_control.client.domain.ui.event.DisconnectPuppetEvent;
import com.cuukenn.opensource.remote_desktop_control.client.domain.ui.view.IndexView;
import com.cuukenn.opensource.remote_desktop_control.core.domain.event.EventFactory;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.ScreenPacket;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author changgg
 */
@Slf4j
public class IndexController extends IndexView {
    public static final String RESOURCE = "/fxml/index.fxml";
    private final Map<String, PuppetScreenHolder> CONTROL_STAGE = new HashMap<>();

    public static FXMLLoader getFXMLLoader() {
        return new FXMLLoader(Objects.requireNonNull(IndexController.class.getResource(RESOURCE)));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //openScreenControl("test001");
    }

    @FXML
    public void connect() {
        EventFactory.postAsync(new ConnectEvent());
    }

    @FXML
    public void disconnect() {
        EventFactory.postAsync(new DisconnectEvent());
    }

    @FXML
    public void connectPuppet() {
        EventFactory.postAsync(new ConnectPuppetEvent(getPuppetName().getText()));
    }

    public void disconnectPuppet() {
        EventFactory.postAsync(new DisconnectPuppetEvent(getPuppetName().getText()));
    }

    /**
     * 打开新窗口
     *
     * @param puppetId 傀儡机ID
     */
    public synchronized void openScreenControl(String puppetId) {
        log.info("open control screen:{}", puppetId);
        PuppetScreenHolder holder = CONTROL_STAGE.get(puppetId);
        if (holder == null) {
            holder = createNewControlStage(puppetId);
            CONTROL_STAGE.put(puppetId, holder);
        }
        holder.getStage().show();
    }

    /**
     * 关闭一个子控制窗口
     *
     * @param puppetId 控制端ID
     */
    public synchronized void closeScreenControl(String puppetId) {
        log.info("close control screen:{}", puppetId);
        PuppetScreenHolder holder = CONTROL_STAGE.remove(puppetId);
        if (holder != null) {
            holder.getStage().close();
        }
    }

    public void refreshScreen(ScreenPacket packet) {
        PuppetScreenHolder holder = CONTROL_STAGE.get(packet.getPuppetId());
        if (holder != null) {
            holder.getController().refreshScreen(packet);
        }
    }

    @SneakyThrows
    private PuppetScreenHolder createNewControlStage(String puppetId) {
        Stage stage = new Stage();
        stage.setTitle("控制中:" + puppetId);
        stage.setWidth(800);
        stage.setHeight(600);
        FXMLLoader loader = ScreenController.getFXMLLoader();
        stage.setScene(new Scene(loader.load()));
        PuppetScreenHolder holder = new PuppetScreenHolder(stage, loader.getController());
        stage.setOnCloseRequest(holder.getController()::close);
        holder.getController().setPuppetId(puppetId);
        return holder;
    }

    public synchronized void close(WindowEvent event) {
        for (PuppetScreenHolder holder : CONTROL_STAGE.values()) {
            holder.getStage().close();
        }
        CONTROL_STAGE.clear();
    }

    @RequiredArgsConstructor
    @Data
    static class PuppetScreenHolder {
        private final Stage stage;
        private final ScreenController controller;
    }
}

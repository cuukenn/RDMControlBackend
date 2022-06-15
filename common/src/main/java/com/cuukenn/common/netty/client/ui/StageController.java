package com.cuukenn.common.netty.client.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author changgg
 */
@Slf4j
public class StageController {
    private final Map<String, Stage> stages;
    @Getter
    private Stage primaryStage;

    public StageController() {
        stages = new ConcurrentHashMap<>();
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * 将加载好的Stage放到Map中进行管理
     *
     * @param name  设定Stage的名称
     * @param stage Stage的对象
     */
    public void addStage(String name, Stage stage) {
        stages.put(name, stage);
    }

    /**
     * 通过Stage名称获取Stage对象
     *
     * @param name Stage的名称
     * @return 对应的Stage对象
     */
    public Stage getStage(String name) {
        return stages.get(name);
    }

    /**
     * 加载窗口地址，需要fxml资源文件属于独立的窗口并用Pane容器或其子类继承
     *
     * @param resource fxml资源地址
     * @param styles   可变参数，init使用的初始化样式资源设置
     */
    public void loadStage(String resource, StageStyle... styles) {
        try {
            //加载FXML资源文件
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(resource)));
            Pane tempPane = loader.load();
            //通过Loader获取FXML对应的ViewCtr，并将本StageController注入到ViewCtr中
            ControlledStage controlledStage = loader.getController();
            controlledStage.setStageController(this);
            //构造对应的Stage
            Scene tempScene = new Scene(tempPane);
            Stage tempStage = new Stage();
            tempStage.setScene(tempScene);
            //配置initStyle
            for (StageStyle style : styles) {
                tempStage.initStyle(style);
            }
            //将设置好的Stage放到HashMap中
            this.addStage(resource, tempStage);
        } catch (Exception e) {
            log.error("loadStage failed", e);
        }
    }

    /**
     * 显示Stage但不隐藏任何Stage
     *
     * @param name 需要显示的窗口的名称
     */
    public void setStage(String name) {
        this.getStage(name).show();
    }


    /**
     * 显示Stage并隐藏对应的窗口
     *
     * @param show  需要显示的窗口
     * @param close 需要删除的窗口
     */
    public void setStage(String show, String close) {
        getStage(close).close();
        setStage(show);
    }

    /**
     * 在Map中删除Stage加载对象
     *
     * @param name 需要删除的fxml窗口文件名
     * @return 是否删除成功
     */
    public boolean unloadStage(String name) {
        if (stages.remove(name) == null) {
            log.warn("stage not exist,please check the name");
            System.out.println("窗口不存在，请检查名称");
            return false;
        } else {
            log.warn("remove stage successful");
            return true;
        }
    }
}

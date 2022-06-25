package com.cuukenn.client.ui.view;

import com.cuukenn.common.netty.client.ui.ControlledStage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import lombok.Getter;

/**
 * @author changgg
 */
@Getter
public abstract class IndexView implements Initializable, ControlledStage {
    @FXML
    private TabPane tabPane;
    @FXML
    private TextField puppetName;
    @FXML
    private Tab screenTab;
    @FXML
    private ImageView imageView;
}

package com.cuukenn.client.ui.view;

import com.cuukenn.common.netty.client.ui.ControlledStage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import lombok.Data;

/**
 * @author changgg
 */
@Data
public abstract class IndexView implements Initializable, ControlledStage {
    @FXML
    private TabPane tabPane;
    @FXML
    private TextField puppetName;
}

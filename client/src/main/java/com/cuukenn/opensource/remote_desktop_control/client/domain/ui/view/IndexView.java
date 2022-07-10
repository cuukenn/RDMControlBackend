package com.cuukenn.opensource.remote_desktop_control.client.domain.ui.view;

import com.cuukenn.opensource.remote_desktop_control.core.domain.ui.AbstractRegistrableControlledStage;
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
public abstract class IndexView extends AbstractRegistrableControlledStage implements Initializable {
    @FXML
    private TabPane tabPane;
    @FXML
    private TextField puppetName;
    @FXML
    private Tab screenTab;
    @FXML
    private ImageView imageView;
}

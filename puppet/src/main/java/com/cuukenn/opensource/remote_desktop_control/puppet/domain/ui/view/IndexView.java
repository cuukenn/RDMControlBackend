package com.cuukenn.opensource.remote_desktop_control.puppet.domain.ui.view;

import com.cuukenn.opensource.remote_desktop_control.core.domain.ui.AbstractRegistrableControlledStage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import lombok.Data;

/**
 * @author changgg
 */
@Data
public abstract class IndexView extends AbstractRegistrableControlledStage implements Initializable {
    @FXML
    private TextField puppetName;
}

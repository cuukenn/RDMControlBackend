package com.cuukenn.opensource.remote_desktop_control.client.domain.ui.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import lombok.Getter;

/**
 * @author changgg
 */
@Getter
public abstract class ScreenView implements Initializable {
    @FXML
    private ScrollPane pane;
    @FXML
    private Canvas canvas;
}

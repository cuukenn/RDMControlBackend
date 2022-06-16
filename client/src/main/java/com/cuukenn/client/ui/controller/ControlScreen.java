package com.cuukenn.client.ui.controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;

/**
 * @author changgg
 */
public class ControlScreen extends MainController {
    public ImageView image;

    public void drawImage(byte[] bytes) {
        this.image.setImage(new Image(new ByteArrayInputStream(bytes)));
    }
}

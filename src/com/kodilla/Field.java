package com.kodilla;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Field extends ImageView {

    public Field(Image image) {
        super(image);
        this.setOnMouseClicked((MouseEvent event) -> mouseClicked());
    }

    private void mouseClicked() {
        if (getImage() != Consts.EMPTY) {

        } else {
            setImage(Consts.CROSS);
        }
    }
}

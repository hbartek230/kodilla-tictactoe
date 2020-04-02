package com.kodilla;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class FieldView extends ImageView {

    ImageType imageType;

    public FieldView(ImageType type) {
        super(new Image(type.getImageAdress()));
        this.imageType = type;
        this.setOnMouseClicked((MouseEvent event) -> mouseClicked(imageType));
    }

    private void mouseClicked(ImageType type) {
        if (imageType == ImageType.EMPTY) {
            this.setImage(new Image(ImageType.CROSS.getImageAdress()));
            this.imageType = ImageType.CROSS;
        }
    }
}

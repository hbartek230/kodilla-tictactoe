package com.kodilla;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class FieldView extends ImageView {

    private final static String ALERT_TITLE = "Illegal move";
    private final static String ALERT_TEXT = "You can put a mark only on empty field!";
    ImageType imageType;

    public FieldView(ImageType type) {
        super(new Image(type.getImageAdress()));
        this.imageType = type;
        this.setOnMouseClicked((MouseEvent event) -> mouseClicked(imageType));
    }

    private void mouseClicked(ImageType type) {
        if (type == ImageType.EMPTY) {
            this.setImage(new Image(ImageType.CROSS.getImageAdress()));
            this.imageType = ImageType.CROSS;
        } else {
            showErrorMessage();
        }
    }

    private void showErrorMessage() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(ALERT_TITLE);
        alert.setHeaderText(null);
        alert.setContentText(ALERT_TEXT);

        alert.showAndWait();
    }
}

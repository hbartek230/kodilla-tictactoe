package com.kodilla.ui;

import com.kodilla.controls.GameStatus;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class FieldView extends ImageView {

    private final static String ALERT_TITLE = "Illegal move";
    private final static String ALERT_TEXT = "You can put a mark only on empty field!";
    GameImageType imageType;
    GameStatus gameStatus;
    Image image;

    public FieldView(GameImageType type, GameStatus status) {
        super(new Image(type.getImageAdress()));
        this.imageType = type;
        this.gameStatus = status;
        this.setOnMouseClicked((MouseEvent event) -> mouseClicked(imageType, gameStatus));
        this.image = new Image(GameImageType.CROSS.getImageAdress());
    }

    private void mouseClicked(GameImageType type, GameStatus status) {
        if (type == GameImageType.EMPTY) {
            this.setImage(image);
            this.imageType = GameImageType.CROSS;
            status.mouseClicked(String.valueOf(imageType), GridPane.getRowIndex(this), GridPane.getColumnIndex(this));
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

package com.kodilla.ui;

import com.kodilla.controls.BoardPresenter;
import com.kodilla.controls.Connector;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class FieldView extends ImageView {

    private final static String ALERT_TITLE = "Illegal move";
    private final static String ALERT_TEXT = "You can put a mark only on empty field!";
    GameImageType imageType;
    BoardPresenter boardPresenter;
    Image image;

    public FieldView(GameImageType type, BoardPresenter boardPresenter) {
        super(new Image(type.getImageAdress()));
        this.imageType = type;
        this.boardPresenter = boardPresenter;
        this.setOnMouseClicked((MouseEvent event) -> mouseClicked(imageType, boardPresenter));
        this.image = new Image(GameImageType.CROSS.getImageAdress());
    }

    private void mouseClicked(GameImageType type, BoardPresenter boardPresenter) {
        if (type == GameImageType.EMPTY) {
            this.imageType = GameImageType.CROSS;
            boardPresenter.userMouseClicked(String.valueOf(imageType), GridPane.getRowIndex(this), GridPane.getColumnIndex(this));
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

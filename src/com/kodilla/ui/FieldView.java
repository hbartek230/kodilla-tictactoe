package com.kodilla.ui;

import com.kodilla.controls.BoardPresenter;
import com.kodilla.controls.clicklistener.UserClickListener;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class FieldView extends ImageView {

    private final static String ALERT_TITLE = "Illegal move";
    private final static String ALERT_TEXT = "You can put a mark only on empty field!";
    private final UserClickListener clickListener;

    public FieldView(GameImageType type, UserClickListener listener) {
        super(new Image(type.getImageAdress()));
        this.setOnMouseClicked((MouseEvent event) -> mouseClicked(type));
        this.clickListener = listener;
    }

    private void mouseClicked(GameImageType type) {
        if (type == GameImageType.EMPTY) {
            clickListener.
                    selectedFieldByUser(
                            GridPane.getRowIndex(this),
                            GridPane.getColumnIndex(this));
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

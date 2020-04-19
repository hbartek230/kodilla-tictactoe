package com.kodilla.controls;

import javafx.stage.Stage;

import java.util.List;

public interface Connector {

    interface Presenter {
        void userMouseClicked(String userMark, int rowClicker, int columnClicked);

        void setView(com.kodilla.ui.Board view, Stage primaryStage);
    }

    interface Board {
        void fillGameBoard(List<FieldState> gameBoard);
    }

}

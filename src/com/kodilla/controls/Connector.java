package com.kodilla.controls;

import javafx.stage.Stage;

import java.util.List;

public interface Connector {

    interface Presenter {
        void setView(View view, Stage primaryStage);
    }

    interface View {
        void fillGameBoard(List<FieldState> gameBoard);
    }

}

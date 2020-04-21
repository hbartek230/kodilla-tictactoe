package com.kodilla;

import com.kodilla.ui.BoardView;
import javafx.application.Application;
import javafx.stage.Stage;

public class GameMain extends Application {

    private BoardView boardView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        boardView = new BoardView();
        boardView.start(primaryStage);

    }
}

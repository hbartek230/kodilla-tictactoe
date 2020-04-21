package com.kodilla;

import com.kodilla.ui.Board;
import javafx.application.Application;
import javafx.stage.Stage;

public class GameMain extends Application {

    private Board board;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        board = new Board();
        board.start(primaryStage);

    }
}

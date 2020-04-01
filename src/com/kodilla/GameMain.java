package com.kodilla;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(Board.setScene(), 900, 900);

        primaryStage.setTitle("TicTacToe - My First Kodilla Project");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}

package com.kodilla;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameMain extends Application {

    private final static String APP_NAME = "TicTacToe - My First Kodilla Class";
    private final static int SCENE_WIDTH = 900;
    private final static int SCENE_HEIGHT = 900;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(Board.setScene(), SCENE_WIDTH, SCENE_HEIGHT);

        primaryStage.setTitle(APP_NAME);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}

package com.kodilla.ui;

import com.kodilla.controls.BoardPresenter;
import com.kodilla.controls.BoardSettings;
import com.kodilla.controls.Connector;
import com.kodilla.controls.FieldState;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class Board extends Application implements Connector.Board {

    private final static String APP_NAME = "TicTacToe - My First Kodilla Class";
    private final static String BACKGROUND_ADRESS = "file:src/resources/background.jpg";
    private final static int SCENE_WIDTH = 900;
    private final static int SCENE_HEIGHT = 900;
    private static final int GAME_BOARD_COLUMN_WIDTH = 270;
    private static final int GAME_BOARD_ROW_HEIGHT = 270;
    private static final int GAME_BOARD_PREF_WIDTH = 810;
    private static final int GAME_BOARD_PREF_HEIGHT = 810;
    private static final int GAME_BOARD_MAX_COLUMNS = 3;
    private static final int GAME_BOARD_MAX_ROWS = 3;
    private static final int GAME_BOARD_HGAP = 15;
    private static final int GAME_BOARD_VGAP = 15;
    private GridPane viewGameBoard;
    private BoardPresenter presenter;

    @Override
    public void start(Stage primaryStage) {
        createPresenter(primaryStage);
        primaryStage.setTitle(APP_NAME);
        primaryStage.setScene(createScene());
        primaryStage.show();
    }

    private void createPresenter(Stage primaryStage) {
        presenter = new BoardPresenter(new BoardSettings(GAME_BOARD_MAX_ROWS, GAME_BOARD_MAX_COLUMNS));
        presenter.setView(this, primaryStage);
    }

    private Scene createScene() {
        viewGameBoard = new GridPane();
        viewGameBoard.setBackground(createBackground());

        return new Scene(setGameBoardParams(viewGameBoard), SCENE_WIDTH, SCENE_HEIGHT);
    }

    private GridPane setGameBoardParams(GridPane gameBoard) {
        setGameBoardColumns(gameBoard);
        setGameBoardRows(gameBoard);
        presenter.firstViewOfGameBoard();

        gameBoard.setPrefSize(GAME_BOARD_PREF_WIDTH, GAME_BOARD_PREF_HEIGHT);
        gameBoard.setAlignment(Pos.CENTER);
        gameBoard.setHgap(GAME_BOARD_HGAP);
        gameBoard.setVgap(GAME_BOARD_VGAP);

        return gameBoard;
    }

    private void setGameBoardColumns(GridPane gameBoard) {
        for (int columnIndex = 0; columnIndex < GAME_BOARD_MAX_COLUMNS; columnIndex++) {
            ColumnConstraints column = new ColumnConstraints(GAME_BOARD_COLUMN_WIDTH);
            column.setHalignment(HPos.CENTER);
            gameBoard.getColumnConstraints().add(column);
        }
    }

    private void setGameBoardRows(GridPane gameBoard) {
        for (int rowIndex = 0; rowIndex < GAME_BOARD_MAX_ROWS; rowIndex++) {
            RowConstraints row = new RowConstraints(GAME_BOARD_ROW_HEIGHT);
            gameBoard.getRowConstraints().add(row);
        }
    }

    @Override
    public void fillGameBoard(List<FieldState> gameBoard) {
        viewGameBoard.getChildren().clear();
        gameBoard.forEach(fieldState ->
                viewGameBoard.add(new FieldView(GameImageType.fromFigureType(fieldState.getType()), presenter),
                        fieldState.getColNumber(), fieldState.getRowNumber())
        );
    }

    private Background createBackground() {
        Image backgroundImage = new Image(BACKGROUND_ADRESS);
        BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
        BackgroundImage setBackgroundImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

        return new Background(setBackgroundImage);
    }
}

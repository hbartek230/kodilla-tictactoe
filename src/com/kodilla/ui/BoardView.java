package com.kodilla.ui;

import com.kodilla.controls.BoardContract;
import com.kodilla.controls.BoardPresenter;
import com.kodilla.controls.BoardSettings;
import com.kodilla.controls.FieldState;
import com.kodilla.controls.clicklistener.UserClickListener;
import com.kodilla.controls.data.winconditions.AdvancedWinChecker;
import com.kodilla.controls.data.winconditions.ColumnWinChecker;
import com.kodilla.controls.data.winconditions.DiagonalWinChecker;
import com.kodilla.controls.data.winconditions.RowWinChecker;
import com.kodilla.controls.domain.WinConditionChecker;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

public class BoardView extends Application implements BoardContract.View, UserClickListener {

    private static final String APP_NAME = "TicTacToe - My First Kodilla Class";
    private static final String BACKGROUND_ADRESS = "file:src/resources/background.jpg";
    private static final String WIN_MESSAGE_TITLE = "You Won";
    private static final String WIN_MESSAGE_TEXT = "You won this game. Do you wanna play again?";
    private static final int SCENE_WIDTH = 900;
    private static final int SCENE_HEIGHT = 900;
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
    private Stage myStage;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(APP_NAME);
        primaryStage.setScene(createScene());
        bindControls();
        primaryStage.show();
        myStage = primaryStage;
    }

    private void bindControls() {
        presenter = new BoardPresenter(new BoardSettings(GAME_BOARD_MAX_ROWS, GAME_BOARD_MAX_COLUMNS),
                prepareWinConditions());
        presenter.setView(this);
    }

    private WinConditionChecker prepareWinConditions() {
        return new AdvancedWinChecker(new ColumnWinChecker(), new RowWinChecker(), new DiagonalWinChecker());
    }

    private Scene createScene() {
        viewGameBoard = new GridPane();
        viewGameBoard.setBackground(createBackground());

        return new Scene(setGameBoardParams(viewGameBoard), SCENE_WIDTH, SCENE_HEIGHT);
    }

    private GridPane setGameBoardParams(GridPane gameBoard) {
        setGameBoardColumns(gameBoard);
        setGameBoardRows(gameBoard);

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
                viewGameBoard.add(new FieldView(GameImageType.fromFigureType(fieldState.getType()), this),
                        fieldState.getColNumber(), fieldState.getRowNumber())
        );
    }

    private Background createBackground() {
        Image backgroundImage = new Image(BACKGROUND_ADRESS);
        BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true,
                true, false, false);
        BackgroundImage setBackgroundImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

        return new Background(setBackgroundImage);
    }

    @Override
    public void selectedFieldByUser(int rowClicked, int columnClicked) {
        presenter.selectedFieldByUser(rowClicked, columnClicked);
    }

    @Override
    public void showWinMessage() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(WIN_MESSAGE_TITLE);
        alert.setHeaderText(null);
        alert.setContentText(WIN_MESSAGE_TEXT);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            presenter.prepareBoardToNewGame();
        } else {
            myStage.close();
        }
    }
}

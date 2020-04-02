package com.kodilla;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class Board {

    private static final int GAME_BOARD_COLUMN_WIDTH = 270;
    private static final int GAME_BOARD_ROW_HEIGHT = 270;
    private static final int GAME_BOARD_PREF_WIDTH = 810;
    private static final int GAME_BOARD_PREF_HEIGHT = 810;
    private static final int GAME_BOARD_MAX_COLUMNS = 3;
    private static final int GAME_BOARD_MAX_ROWS = 3;
    private static final int GAME_BOARD_HGAP = 15;
    private static final int GAME_BOARD_VGAP = 15;
    private static GridPane grid;

    public static GridPane setScene() {
        grid = new GridPane();
        grid.setBackground(createBackground());

        setGameBoardParams();

        return grid;
    }

    private static void setGameBoardParams() {
        setGameBoardColumns();
        setGameBoardRows();
        fillGameBoard();
        grid.setPrefSize(GAME_BOARD_PREF_WIDTH, GAME_BOARD_PREF_HEIGHT);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(GAME_BOARD_HGAP);
        grid.setVgap(GAME_BOARD_VGAP);
    }

    private static void setGameBoardColumns() {
        for (int i = 0; i < GAME_BOARD_MAX_COLUMNS; i++) {
            ColumnConstraints column = new ColumnConstraints(GAME_BOARD_COLUMN_WIDTH);
            column.setHalignment(HPos.CENTER);
            grid.getColumnConstraints().add(column);
        }
    }

    private static void setGameBoardRows() {
        for (int i = 0; i < GAME_BOARD_MAX_ROWS; i++) {
            RowConstraints row = new RowConstraints(GAME_BOARD_ROW_HEIGHT);
            grid.getRowConstraints().add(row);
        }
}


    private static void fillGameBoard() {
        for (int i = 0; i < GAME_BOARD_MAX_ROWS; i++) {
            for (int j = 0; j < GAME_BOARD_MAX_COLUMNS; j++) {
                grid.add(new FieldView(ImageType.EMPTY), i, j);
            }
        }
    }

    private static Background createBackground() {

        Image backgroundImage = new Image("file:src/resources/background.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
        BackgroundImage setBackgroundImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(setBackgroundImage);

        return background;
    }
}

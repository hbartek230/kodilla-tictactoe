package com.kodilla;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class Board {

    private static GridPane grid;

    public static GridPane setScene() {
        grid = new GridPane();

        BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(Consts.BACKGROUND, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        grid.setBackground(background);

        for (int i = 0; i < 3; i++) {
            ColumnConstraints column = new ColumnConstraints(270);
            column.setHalignment(HPos.CENTER);
            grid.getColumnConstraints().add(column);
            RowConstraints row = new RowConstraints(270);
            grid.getRowConstraints().add(row);
        }

        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                grid.add(new Field(Consts.EMPTY), i, j);
            }
        }

        grid.setPrefSize(810, 810);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(15);
        grid.setVgap(15);

        return grid;
    }
}

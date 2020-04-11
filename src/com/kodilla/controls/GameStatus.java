package com.kodilla.controls;

import com.kodilla.ui.ImageType;
import javafx.scene.control.Alert;

import java.util.LinkedList;

public class GameStatus {

    private LinkedList<WinConditions> winConditions;
    private ImageType[][] gameBoard;
    private int maxRows, maxColumns;

    public GameStatus(int maxRows, int maxColumns) {
        this.maxRows = maxRows;
        this.maxColumns = maxColumns;
        this.gameBoard = new ImageType[maxRows][maxColumns];
    }

    public void setElement(ImageType type, int rowIndex, int columnIndex) {
        this.gameBoard[rowIndex][columnIndex] = type;
    }

    public void mouseClicked(ImageType type, int rowClicked, int columnClicked) {
        gameBoard[rowClicked][columnClicked] = type;
        checkGameStatus(type);
    }

    public void checkGameStatus(ImageType type) {
        this.winConditions = new LinkedList<>();
        for (int rowIndex = 0; rowIndex < maxRows; rowIndex++) {
            winConditions.add(new WinConditions(gameBoard[rowIndex][0], gameBoard[rowIndex][1], gameBoard[rowIndex][2]));
        }

        for (int columnIndex = 0; columnIndex < maxColumns; columnIndex++) {
            winConditions.add(new WinConditions(gameBoard[0][columnIndex], gameBoard[1][columnIndex], gameBoard[2][columnIndex]));
        }

        winConditions.add(new WinConditions(gameBoard[0][0], gameBoard[1][1], gameBoard[2][2]));
        winConditions.add(new WinConditions(gameBoard[2][0], gameBoard[1][1], gameBoard[0][2]));

        if (checkIfWin(type)) {
            winningMessage();
        }
    }

    public boolean checkIfWin(ImageType type) {
        System.out.println("CHEKING...");
        return this.winConditions.stream()
                .anyMatch(r -> r.ifWin(type));
    }

    public void winningMessage() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("You Won");
        alert.setHeaderText(null);
        alert.setContentText("You won this game. Do you wanna play again?");

        alert.showAndWait();
    }
}

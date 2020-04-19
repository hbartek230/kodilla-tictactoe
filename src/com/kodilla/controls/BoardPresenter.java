package com.kodilla.controls;

import com.kodilla.controls.winConditions.ColumnWinChecker;
import com.kodilla.controls.winConditions.RowWinChecker;
import com.kodilla.ui.Board;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BoardPresenter implements Connector.Presenter {

    private final List<FieldState> gameBoard;
    private final BoardSettings settings;
    private Board view;
    private ComputerControls computerControl;
    private RowWinChecker rwc;
    private ColumnWinChecker cwc;

    public BoardPresenter(BoardSettings settings) {
        this.settings = settings;
        this.rwc = new RowWinChecker();
        this.cwc = new ColumnWinChecker();
        gameBoard = createDefaultField(settings);
    }

    @Override
    public void setView(Board view) {
        this.view = view;
    }

    public void firstViewOfGameBoard() {
        view.fillGameBoard(gameBoard);
    }

    public List<FieldState> getGameStatus() {
        return gameBoard;
    }

    private List<FieldState> createDefaultField(BoardSettings settings) {
        List<FieldState> defaultFields = new ArrayList<>();
        for (int rowNumber = 0; rowNumber < settings.getRowCount(); rowNumber++) {
            for (int columnNumber = 0; columnNumber < settings.getColumnCount(); columnNumber++) {
                defaultFields.add(new FieldState(rowNumber, columnNumber, FigureType.EMPTY));
            }
        }
        return defaultFields;
    }

    @Override
    public void userMouseClicked(String userMark, int rowClicked, int columnClicked) {
        computerControl = new ComputerControls();
        prepareGameBoardToShow(userMark, rowClicked, columnClicked);
        makeComputerMove();
    }

    private void prepareGameBoardToShow(String gameMark, int rowClicked, int columnClicked) {
        int index = findIndexOfClickedElement(rowClicked, columnClicked);
        List<FieldState> listAfterClicked = setNewElementOnBoard(index, rowClicked, columnClicked, gameMark);
        view.fillGameBoard(listAfterClicked);
        winChecker(listAfterClicked, rowClicked, columnClicked, gameMark);
    }

    private void makeComputerMove() {
        String computerMark = String.valueOf(FigureType.CIRCLE);
        FieldState computerFieldState;
        List<FieldState> availableFields = gameBoard.stream()
                .filter(r -> r.getType() == FigureType.EMPTY)
                .collect(Collectors.toList());

        if (availableFields.size() > 1) {
            computerFieldState = computerControl.selectComputerFigure(availableFields);
            prepareGameBoardToShow(computerMark, computerFieldState.getRowNumber(), computerFieldState.getColNumber());
        }
    }

    private int findIndexOfClickedElement(int rowClicked, int columnClicked) {
        FieldState fieldState = gameBoard.stream()
                .filter(r -> r.getRowNumber() == rowClicked && r.getColNumber() == columnClicked)
                .findFirst()
                .map(fieldState1 ->
                        new FieldState(fieldState1.getRowNumber(), fieldState1.getColNumber(), fieldState1.getType()))
                .orElseThrow(NullPointerException::new);

        return gameBoard.indexOf(fieldState);
    }

    public List<FieldState> setNewElementOnBoard(int index, int rowClicked, int columnClicked, String figureType) {
        FieldState newFieldState = new FieldState(rowClicked, columnClicked, FigureType.valueOf(figureType));
        gameBoard.set(index, newFieldState);
        return gameBoard;
    }

    private void winChecker(List<FieldState> actualGameBoard, int rowClicked, int columnClicked, String gameMark){
        if(rwc.checkIfWin(actualGameBoard, rowClicked, columnClicked, gameMark) || cwc.checkIfWin(actualGameBoard, rowClicked, columnClicked, gameMark)) {
            winMessage();
        }
    }

    private void winMessage() {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("You Won");
            alert.setHeaderText(null);
            alert.setContentText("You won this game. Do you wanna play again?");

            alert.showAndWait();

    }
}

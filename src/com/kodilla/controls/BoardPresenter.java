package com.kodilla.controls;

import com.kodilla.controls.winconditions.ColumnWinChecker;
import com.kodilla.controls.winconditions.DiagonalWinChecker;
import com.kodilla.controls.winconditions.RowWinChecker;
import com.kodilla.controls.winconditions.WinConditionChecker;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoardPresenter implements Connector.Presenter {

    private final static String WIN_MESSAGE_TITLE = "You Won";
    private final static String WIN_MESSAGE_TEXT = "You won this game. Do you wanna play again?";
    private final static String userMark = "CROSS";
    private final static String computerMark = "CIRCLE";
    private final BoardSettings settings;
    private final ComputerControl computerControl;
    private final List<WinConditionChecker> winChecker;
    private boolean ifWin;
    private List<FieldState> gameBoard;
    private List<FieldState> availableFields;
    private Connector.View view;
    private Stage primaryStage;

    public BoardPresenter(BoardSettings settings) {
        this.settings = settings;
        this.winChecker = createWinCheckerConditions();
        computerControl = new ComputerControl();
    }

    @Override
    public void setView(Connector.View view, Stage primaryStage) {
        this.view = view;
        this.primaryStage = primaryStage;
        prepareGameBoardToShow();
    }

    public void prepareGameBoardToShow(){
        gameBoard = createDefaultField(settings);
        availableFields = createDefaultField(settings);
    }

    public void firstViewOfGameBoard() {
        view.fillGameBoard(gameBoard);
    }

    public List<FieldState> getGameStatus() {
        return gameBoard;
    }

    public void selectedFieldByUser(int rowClicked, int columnClicked) {
        FieldState selectedByUser = new FieldState(rowClicked, columnClicked, FigureType.valueOf(userMark));

        prepareGameBoardToShow(selectedByUser);
        if (!ifWin) {
            makeComputerMove();
        }
    }

    private List<WinConditionChecker> createWinCheckerConditions() {
        List<WinConditionChecker> conditionList = new ArrayList<>();
        conditionList.add(new RowWinChecker());
        conditionList.add(new ColumnWinChecker());
        conditionList.add(new DiagonalWinChecker());

        return conditionList;
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

    private void prepareGameBoardToShow(FieldState changedField) {
        int index = findIndexOfClickedElement(changedField.getRowNumber(), changedField.getColNumber());
        List<FieldState> listAfterClicked = setNewElementOnBoard(index, changedField);

        if (availableFields.size() > 1){
            removeClickedElementFromAvailableList(changedField);

        }
        view.fillGameBoard(listAfterClicked);
        ifWin = winChecker(listAfterClicked, changedField);
        if (ifWin) {
            showWinMessage();
        }
    }

    private void removeClickedElementFromAvailableList(FieldState changedField){
        changedField = new FieldState(changedField.getRowNumber(), changedField.getColNumber(), FigureType.EMPTY);
        availableFields.remove(changedField);
    }

    private void makeComputerMove() {
        FieldState computerFieldState;

        if (availableFields.size() > 1) {
            computerFieldState = computerControl.selectComputerFigure(availableFields);
            int computerFigureRow = computerFieldState.getRowNumber();
            int computerFigureColumn = computerFieldState.getColNumber();

            prepareGameBoardToShow(new FieldState(computerFigureRow, computerFigureColumn, FigureType.valueOf(computerMark)));
        }
    }

    private int findIndexOfClickedElement(int rowClicked, int columnClicked) {
        FieldState clickedFieldState = gameBoard.stream()
                .filter(fieldState -> fieldState.getRowNumber() == rowClicked && fieldState.getColNumber() == columnClicked)
                .findFirst()
                .map(fieldState1 ->
                        new FieldState(fieldState1.getRowNumber(), fieldState1.getColNumber(), fieldState1.getType()))
                .orElseThrow(NullPointerException::new);

        return gameBoard.indexOf(clickedFieldState);
    }

    private List<FieldState> setNewElementOnBoard(int index, FieldState changedField) {
        gameBoard.set(index, new FieldState(
                changedField.getRowNumber(), changedField.getColNumber(), changedField.getType())
        );
        return gameBoard;
    }

    private boolean winChecker(List<FieldState> actualGameBoard, FieldState changedField) {
        return winChecker.stream()
                .anyMatch(checker -> checker.checkIfWin(actualGameBoard, changedField)
                );
    }

    private void showWinMessage() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(WIN_MESSAGE_TITLE);
        alert.setHeaderText(null);
        alert.setContentText(WIN_MESSAGE_TEXT);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            firstViewOfGameBoard();
        } else {
            primaryStage.close();
        }
    }
}

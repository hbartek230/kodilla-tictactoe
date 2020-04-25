package com.kodilla.controls;

import com.kodilla.controls.domain.WinConditionChecker;

import java.util.ArrayList;
import java.util.List;

public class BoardPresenter implements BoardContract.Presenter {

    private final static String userMark = "CROSS";

    private final BoardSettings settings;
    private final ComputerControl computerControl;
    private final WinConditionChecker checker;

    private List<FieldState> actualFieldStateList;
    private List<FieldState> availableFields;
    private BoardContract.View view;

    public BoardPresenter(BoardSettings settings, WinConditionChecker checker) {
        this.settings = settings;
        this.checker = checker;
        computerControl = new ComputerControl();
    }

    @Override
    public void setView(BoardContract.View view) {
        this.view = view;
        prepareBoardToNewGame();
    }

    public void prepareBoardToNewGame() {
        actualFieldStateList = createDefaultField(settings);
        availableFields = createDefaultField(settings);
        whoseMove(WhoseTurn.USER);
        view.fillGameBoard(actualFieldStateList);
    }

    public void whoseMove(WhoseTurn turn) {
        if (turn == WhoseTurn.COMPUTER) {
            makeComputerMove();
        }
    }

    public void selectedFieldByUser(int rowClicked, int columnClicked) {
        FieldState selectedByUser = new FieldState(rowClicked, columnClicked, FigureType.valueOf(userMark));
        prepareGameBoardToShow(selectedByUser);
        checkIfGameIsWin(actualFieldStateList, selectedByUser, WhoseTurn.COMPUTER);
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
        replaceNewElementOnBoard(index, changedField);
        removeClickedElementFromAvailableList(changedField);
        view.fillGameBoard(actualFieldStateList);
    }

    private void removeClickedElementFromAvailableList(FieldState changedField) {
        changedField = new FieldState(changedField.getRowNumber(), changedField.getColNumber(), FigureType.EMPTY);
        if (availableFields.size() > 1) {
            availableFields.remove(changedField);
        }
    }

    private int findIndexOfClickedElement(int rowClicked, int columnClicked) {
        return actualFieldStateList.stream()
                .filter(fieldState ->
                        fieldState.getRowNumber() == rowClicked && fieldState.getColNumber() == columnClicked)
                .findFirst()
                .map(fieldState -> actualFieldStateList.indexOf(fieldState))
                .orElseThrow(NullPointerException::new);
    }

    private void replaceNewElementOnBoard(int index, FieldState changedField) {
        actualFieldStateList.set(index, new FieldState(
                changedField.getRowNumber(), changedField.getColNumber(), changedField.getType())
        );
    }

    private void makeComputerMove() {
        if (availableFields.size() > 1) {
            FieldState computerSelectedField = computerControl.selectComputerFigure(availableFields);
            prepareGameBoardToShow(computerSelectedField);
            checkIfGameIsWin(actualFieldStateList, computerSelectedField, WhoseTurn.USER);
        }
    }

    private void checkIfGameIsWin(List<FieldState> actualFieldStateList, FieldState changedField, WhoseTurn turn) {
        if (checker.checkIfWin(actualFieldStateList, changedField)) {
            view.showWinMessage();
        } else {
            whoseMove(turn);
        }
    }
}

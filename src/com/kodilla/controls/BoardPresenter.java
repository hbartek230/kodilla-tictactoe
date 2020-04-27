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
    private WhoseTurn whoseTurn;

    public BoardPresenter(BoardSettings settings, WinConditionChecker checker, ComputerControl computerControl) {
        this.settings = settings;
        this.checker = checker;
        this.computerControl = computerControl;
    }

    @Override
    public void setView(BoardContract.View view) {
        this.view = view;
        createNewGame();
    }

    @Override
    public void restartGame() {
        createNewGame();
    }

    private void createNewGame() {
        actualFieldStateList = createDefaultField(settings);
        availableFields = createDefaultField(settings);
        whoseTurn = WhoseTurn.USER;

        startNewGame();
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

    private void startNewGame() {
        view.fillGameBoard(actualFieldStateList);
        makeNextMove();
    }

    private void makeNextMove() {
        if (whoseTurn == WhoseTurn.USER) {
            view.showInfoAboutUserMove();
        } else {
            view.showInfoAboutComputerMove();
            selectComputerField();
        }
    }

    private void selectComputerField() {
        FieldState computerSelectedField = computerControl.selectComputerFigure(availableFields);
        makeMove(computerSelectedField);
    }

    @Override
    public void selectedFieldByUser(int rowClicked, int columnClicked) {
        FieldState selectedByUser = new FieldState(rowClicked, columnClicked, FigureType.valueOf(userMark));
        makeMove(selectedByUser);
    }

    private void makeMove(FieldState selectedField) {
        updateGameBoard(selectedField);
        boolean isWin = checker.checkIfWin(actualFieldStateList, selectedField);
        if (!isWin) {
            changePlayer();
            makeNextMove();
        } else {
            view.showWinMessage(String.valueOf(selectedField.getType()));
        }
    }

    private void updateGameBoard(FieldState changedField) {
        int index = findIndexOfClickedElement(changedField.getRowNumber(), changedField.getColNumber());
        replaceNewElementOnBoard(index, changedField);
        removeClickedElementFromAvailableList(changedField);
        view.fillGameBoard(actualFieldStateList);
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
        actualFieldStateList.set(index, changedField);
    }

    private void removeClickedElementFromAvailableList(FieldState changedField) {
        changedField = new FieldState(changedField.getRowNumber(), changedField.getColNumber(), FigureType.EMPTY);
        if (availableFields.size() > 1) {
            availableFields.remove(changedField);
        } else {
            throw new IllegalArgumentException("Can`t remove");
        }
    }

    private void changePlayer() {
        if (whoseTurn == WhoseTurn.USER) {
            whoseTurn = WhoseTurn.COMPUTER;
        } else {
            whoseTurn = WhoseTurn.USER;
        }
    }


}

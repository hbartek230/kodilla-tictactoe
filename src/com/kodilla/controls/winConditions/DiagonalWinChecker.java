package com.kodilla.controls.winConditions;

import com.kodilla.controls.FieldState;
import com.kodilla.controls.FigureType;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class DiagonalWinChecker implements WinConditionChecker {

    @Override
    public boolean checkIfWin(List<FieldState> actualGameBoard, int rowClicked, int columnClicked, String gameMark) {
        int maxIndex = findMaxRowIndex(actualGameBoard);

        return checkFirstDiagonal(actualGameBoard, gameMark) || checkSecondDiagonal(actualGameBoard, maxIndex, gameMark);
    }

    private int findMaxRowIndex(List<FieldState> actualGameBoard) {
        return actualGameBoard.stream()
                .max(Comparator.comparing(FieldState::getColNumber))
                .orElseThrow(NoSuchElementException::new)
                .getColNumber();
    }

    private boolean checkFirstDiagonal(List<FieldState> actualGameBoard, String gameMark) {
        return actualGameBoard.stream()
                .filter(fieldState ->
                        fieldState.getRowNumber() == fieldState.getColNumber())
                .allMatch(f ->
                        f.getType().equals(FigureType.valueOf(gameMark)));
    }

    private boolean checkSecondDiagonal(List<FieldState> actualGameBoard, int maxIndex, String gameMark) {
        return actualGameBoard.stream()
                .filter(fieldState ->
                        fieldState.getColNumber() == maxIndex - fieldState.getRowNumber())
                .allMatch(f ->
                        f.getType().equals(FigureType.valueOf(gameMark)));
    }
}

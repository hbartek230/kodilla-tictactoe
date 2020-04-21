package com.kodilla.controls.winconditions;

import com.kodilla.controls.FieldState;
import com.kodilla.controls.FigureType;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class DiagonalWinChecker implements WinConditionChecker {

    @Override
    public boolean checkIfWin(List<FieldState> actualGameBoard, FieldState changedField) {
        int maxIndex = findMaxRowIndex(actualGameBoard);

        return checkDiagonal(actualGameBoard, changedField.getType()) ||
                checkCounterDiagonal(actualGameBoard, maxIndex, changedField.getType());
    }

    private int findMaxRowIndex(List<FieldState> actualGameBoard) {
        return actualGameBoard.stream()
                .max(Comparator.comparing(FieldState::getColNumber))
                .orElseThrow(NoSuchElementException::new)
                .getColNumber();
    }

    private boolean checkDiagonal(List<FieldState> actualGameBoard, FigureType type) {
        return actualGameBoard.stream()
                .filter(fieldState -> fieldState.getRowNumber() == fieldState.getColNumber())
                .allMatch(f -> f.getType().equals(type)
                );
    }

    private boolean checkCounterDiagonal(List<FieldState> actualGameBoard, int maxIndex, FigureType type) {
        return actualGameBoard.stream()
                .filter(fieldState -> fieldState.getColNumber() == maxIndex - fieldState.getRowNumber())
                .allMatch(f -> f.getType().equals(type)
                );
    }
}

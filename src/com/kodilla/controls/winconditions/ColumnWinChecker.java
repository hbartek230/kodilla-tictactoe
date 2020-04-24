package com.kodilla.controls.winconditions;

import com.kodilla.controls.FieldState;

import java.util.List;

public class ColumnWinChecker implements WinConditionChecker {

    @Override
    public boolean checkIfWin(List<FieldState> actualGameBoard, FieldState changedField) {
        return actualGameBoard.stream()
                .filter(fieldState -> fieldState.getColNumber() == changedField.getColNumber())
                .allMatch(fieldState -> fieldState.getType().equals(changedField.getType())
                );
    }
}

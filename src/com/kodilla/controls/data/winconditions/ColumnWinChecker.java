package com.kodilla.controls.data.winconditions;

import com.kodilla.controls.FieldState;
import com.kodilla.controls.domain.WinConditionChecker;

import java.util.List;

public class ColumnWinChecker implements WinConditionChecker {

    @Override
    public boolean checkIfWin(List<FieldState> actualGameBoard, FieldState changedField) {
        if(actualGameBoard.isEmpty()) {
            throw new IllegalArgumentException("actualGameBoard can`t be empty");
        }
        return actualGameBoard.stream()
                .filter(fieldState -> fieldState.getColNumber() == changedField.getColNumber())
                .allMatch(fieldState -> fieldState.getType().equals(changedField.getType())
                );
    }
}

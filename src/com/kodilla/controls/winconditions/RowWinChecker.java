package com.kodilla.controls.winconditions;

import com.kodilla.controls.FieldState;

import java.util.List;

public class RowWinChecker implements WinConditionChecker{

    @Override
    public boolean checkIfWin(List<FieldState> actualGameBoard, FieldState changedField) {
        return actualGameBoard.stream()
                .filter(fieldState -> fieldState.getRowNumber() == changedField.getRowNumber())
                .allMatch(fieldState -> fieldState.getType().equals(changedField.getType())
                );
    }
}

package com.kodilla.controls.winConditions;

import com.kodilla.controls.FieldState;
import com.kodilla.controls.FigureType;

import java.util.List;

public class RowWinChecker implements WinConditionChecker{

    @Override
    public boolean checkIfWin(List<FieldState> actualGameBoard, int rowClicked, int columnClicked, String gameMark) {
        return actualGameBoard.stream()
                .filter(f -> f.getRowNumber() == rowClicked)
                .allMatch(f -> f.getType().equals(FigureType.valueOf(gameMark)));
    }
}

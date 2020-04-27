package com.kodilla.controls.data.winconditions;

import com.kodilla.controls.FieldState;
import com.kodilla.controls.domain.WinConditionChecker;

import java.util.List;
import java.util.stream.Stream;

public class AdvancedWinChecker implements WinConditionChecker {

    private final WinConditionChecker columnChecker;
    private final WinConditionChecker rowChecker;
    private final WinConditionChecker diagonalChecker;

    public AdvancedWinChecker(WinConditionChecker columnChecker, WinConditionChecker rowChecker,
                              WinConditionChecker diagonalChecker) {
        this.columnChecker = columnChecker;
        this.rowChecker = rowChecker;
        this.diagonalChecker = diagonalChecker;
    }

    @Override
    public boolean checkIfWin(List<FieldState> actualGameBoard, FieldState changedField) {
        return Stream.of(columnChecker, rowChecker, diagonalChecker)
                .anyMatch(checker -> checker.checkIfWin(actualGameBoard, changedField)
                );
    }
}

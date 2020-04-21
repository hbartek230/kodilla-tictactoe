package com.kodilla.controls.winconditions;

import com.kodilla.controls.FieldState;

import java.util.List;

public interface WinConditionChecker {

    boolean checkIfWin(List<FieldState> actualGameBoard, FieldState changedField);

}

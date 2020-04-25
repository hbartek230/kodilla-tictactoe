package com.kodilla.controls.domain;

import com.kodilla.controls.FieldState;

import java.util.List;

public interface WinConditionChecker {

    boolean checkIfWin(List<FieldState> actualGameBoard, FieldState changedField);

}
